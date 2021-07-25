package com.elior.imagesmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elior.imagesmanager.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
	List<Image> findByAlbumId(int albumId);
}
