package com.elior.imagesmanager.beans;

import java.util.ArrayList;
import java.util.List;
import com.elior.imagesmanager.entities.Image;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ImageList {
	@JsonProperty("images")
	private List<Image> images = new ArrayList<>();
}
