package com.elior.imagesmanager.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int albumId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String url;

	@Column(nullable = false)
	private String thumbnailUrl;

	@Column(nullable = false)
	private Date timestamp;

	@Column(nullable = false)
	private String bucketName;
	
	@Column(nullable = false)
	private long fileSize;
}
