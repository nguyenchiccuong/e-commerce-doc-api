package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.UploadService;

@CrossOrigin(origins = "*")
@RestController
public class uploadController {

	private final UploadService uploadService;

	@Autowired
	public uploadController(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	@PostMapping(value = "upload/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam(name = "file", required = true) MultipartFile file,
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "dir", required = true) String dir) {
		return this.uploadService.uploadFile(file, name, dir);
	}
}
