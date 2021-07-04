package com.example.demo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

    // @Autowired
    public UploadServiceImpl() {
        // ServletContext context;
    }

    @Override
    public ResponseEntity<Object> uploadFile(MultipartFile file, String name, String dir) {

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
        if (!this.checkNameFile(name)) {
            message.put("message", "Name is not suitable");
            return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
        }
        if (!this.checkNameDir(dir)) {
            message.put("message", "Dir is not suitable");
            return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
        }

        // String path = context.getRealPath("/resources/hinhAnh/bacSi/" +
        // bacSiMoi.getMaBS() + ".png");
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

    @Override
    public boolean checkNameFile(String name) {
        String pattern = "^[\\w,\\s-]+";
        return Pattern.matches(pattern, name);
    }

    @Override
    public boolean checkNameDir(String name) {
        String pattern = "(\\\\?([^\\/]*[\\/])*)([^\\/]+)$";
        return Pattern.matches(pattern, name);
    }
}
