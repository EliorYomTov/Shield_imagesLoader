package com.elior.imagesmanager.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elior.imagesmanager.exceptions.DataNotFoundException;
import com.elior.imagesmanager.services.ReceiveDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
	private final ReceiveDataService receiveDataService;

	@GetMapping()
	public ResponseEntity<?> getAllImages() {
		return new ResponseEntity<>(receiveDataService.getAllImages(), HttpStatus.OK);
	}

	@GetMapping("/albums/{albumId}")
	public ResponseEntity<?> getAlbumImages(@PathVariable int albumId) {
		return new ResponseEntity<>(receiveDataService.getImagesByAlbum(albumId), HttpStatus.OK);
	}

	@GetMapping("/{imageId}")
	public ResponseEntity<byte[]> getImage(@PathVariable int imageId) throws IOException, DataNotFoundException {
		byte[] media = receiveDataService.getImage(imageId);
		return new ResponseEntity<>(media, HttpStatus.OK);
	}
}
