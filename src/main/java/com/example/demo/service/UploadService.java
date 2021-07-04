package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    public ResponseEntity<Object> uploadFile(MultipartFile file, String name, String dir);

    public boolean checkNameFile(String name);

    public boolean checkNameDir(String name);
}
