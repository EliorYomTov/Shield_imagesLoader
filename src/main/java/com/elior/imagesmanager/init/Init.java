package com.elior.imagesmanager.init;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.elior.imagesmanager.entities.Image;
import com.elior.imagesmanager.services.RemoteServerService;

import lombok.RequiredArgsConstructor;

@Component
@Order(1)
@RequiredArgsConstructor
public class Init implements CommandLineRunner {

	private final RemoteServerService remoteServerService;

	@Override
	public void run(String... args) throws Exception {
		List<Image> images = remoteServerService.getImages();
		remoteServerService.saveToAWS(images);
		remoteServerService.saveDataToDB(images);
	}
}
