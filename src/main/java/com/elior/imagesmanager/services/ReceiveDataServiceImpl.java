package com.elior.imagesmanager.services;

import static com.elior.imagesmanager.exceptions.ErrorMessages.NOT_FOUND_BY_ID;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.elior.imagesmanager.entities.Image;
import com.elior.imagesmanager.exceptions.DataNotFoundException;
import com.elior.imagesmanager.repositories.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceiveDataServiceImpl implements ReceiveDataService {

	private final ImageRepository imageRepository;
	private final AmazonS3 s3Client;

	@Override
	public List<Image> getAllImages() {
		return imageRepository.findAll();
	}

	@Override
	public List<Image> getImagesByAlbum(int albumId) {
		return imageRepository.findByAlbumId(albumId);
	}

	@Override
	public byte[] getImage(int id) throws IOException, DataNotFoundException {
		String bucketName = s3Client.listBuckets().get(0).getName();
		Image image = imageRepository.findById(id).orElseThrow(() -> new DataNotFoundException(NOT_FOUND_BY_ID, id));
		S3Object object = s3Client.getObject(bucketName, image.getTitle());
		return IOUtils.toByteArray(object.getObjectContent());
	}
}
