package com.elior.imagesmanager.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.elior.imagesmanager.entities.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface RemoteServerService {

	List<Image> getImages() throws JsonMappingException, JsonProcessingException;

	void saveToAWS(List<Image> imagesList) throws MalformedURLException, IOException;

	void saveDataToDB(List<Image> imagesList);
}
