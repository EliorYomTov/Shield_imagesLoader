package com.elior.imagesmanager.services;

import java.io.IOException;
import java.util.List;

import com.elior.imagesmanager.entities.Image;
import com.elior.imagesmanager.exceptions.DataNotFoundException;

public interface ReceiveDataService {

	List<Image> getAllImages();

	List<Image> getImagesByAlbum(int albumId);

	byte[] getImage(int id) throws IOException, DataNotFoundException;
}
