package com.elior.imagesmanager.services;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.elior.imagesmanager.beans.ImageList;
import com.elior.imagesmanager.entities.Image;
import com.elior.imagesmanager.repositories.ImageRepository;
import com.elior.imagesmanager.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemoteServerServiceImpl implements RemoteServerService {

	private final RestTemplate restTemplate;

	private final ObjectMapper mapper;

	private final ImageRepository imageRepository;
	private final AmazonS3 s3Client;

	private static final String URL = "https://shield-j-test.s3.amazonaws.com/photo.txt";

	@Override
	public List<Image> getImages() throws JsonMappingException, JsonProcessingException {
		String res = restTemplate.getForObject(URL, String.class);
		String resAsJson = Utils.StringtoJsonString("images", res);
		ImageList imageList = mapper.readValue(resAsJson, ImageList.class);
		return imageList.getImages();
	}

	@Override
	public void saveToAWS(List<Image> imagesList) throws IOException {
		String bucketName = s3Client.listBuckets().get(0).getName();
		InputStream inputStream = null;
		DataOutputStream out = null;

		for (int i = 0; i < imagesList.size(); i++) {
			Image image = imagesList.get(i);
			String type = image.getUrl().substring(image.getUrl().lastIndexOf('.'));
			String fileName = image.getTitle() + type;
			URL imageUrl = new URL(image.getUrl());
			inputStream = imageUrl.openStream();

			// Set the pre-signed URL to expire after one hour.
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 60 * 60;
			expiration.setTime(expTimeMillis);

			// Generate the pre-signed URL.
			System.out.println("Generating pre-signed URL.");
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,
					fileName).withMethod(HttpMethod.PUT).withExpiration(expiration);
			URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

			// Create the connection and use it to upload the new object using the
			// pre-signed URL.
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			out = new DataOutputStream(connection.getOutputStream());

			byte[] byteArray = new byte[2048];
			int length;
			long size = 0;

			while ((length = inputStream.read(byteArray)) != -1) {
				out.write(byteArray, 0, length);
				size += length;
			}
			connection.getResponseCode();
			System.out.println("HTTP response code: " + connection.getResponseCode());

			image.setTimestamp(Date.valueOf(LocalDate.now()));
			image.setBucketName(bucketName);
			image.setFileSize(size);
			image.setTitle(fileName);

			// Check to make sure that the object was uploaded successfully.
			// S3Object object = s3Client.getObject(bucketName, fileName);
			// System.out.println("Object " + object.getKey() + " created in bucket " +
			// object.getBucketName());
		}
		inputStream.close();
		out.close();
	}

	@Override
	public void saveDataToDB(List<Image> imagesList) {
		imageRepository.saveAll(imagesList);
	}
}
