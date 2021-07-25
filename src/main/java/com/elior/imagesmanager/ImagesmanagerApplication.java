package com.elior.imagesmanager;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

@SpringBootApplication
public class ImagesmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagesmanagerApplication.class, args);
		System.out.println("Ioc Container was loaded");
	}
}
