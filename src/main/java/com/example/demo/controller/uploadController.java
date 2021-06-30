package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;

import com.example.demo.processing.PatternCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class uploadController {

	@Autowired
	ServletContext context;

	@PostMapping(value = "upload/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name, @RequestParam("dir") String dir) {

		HashMap<String, String> message = new HashMap<String, String>();

		if (file.isEmpty()) {
			message.put("message", "File is empty");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
		if (name.trim().isEmpty()) {
			message.put("message", "Name is empty");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
		if (dir.trim().isEmpty()) {
			message.put("message", "Dir is empty");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
		if (!PatternCheck.checkNameFile(name)) {
			message.put("message", "Name is not suitable");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
		if (!PatternCheck.checkNameDir(dir)) {
			message.put("message", "Dir is not suitable");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}

		File convertFile = new File(".\\src\\main\\resources\\static\\img\\" + dir + "\\" + name + ".png");
		if (!convertFile.getParentFile().exists()) {
			convertFile.getParentFile().mkdirs();
		}

		FileOutputStream fout = null;
		try {
			convertFile.createNewFile();
			fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			message.put("message", "File is uploaded unsuccessfully");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		} finally {
			try {
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		message.put("message", "File is uploaded successfully");
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	}
}
