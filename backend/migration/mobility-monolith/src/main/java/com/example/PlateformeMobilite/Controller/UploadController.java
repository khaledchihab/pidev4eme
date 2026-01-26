package com.example.PlateformeMobilite.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Value("${upload.directory}")
    private String uploadDirectory;

    @PostMapping("/pdf")
    public ResponseEntity<String> uploadPdf(@RequestParam("pdfFile") MultipartFile pdfFile) {
        if (pdfFile.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a PDF file to upload.");
        }

        try {
            // Create the upload directory if it doesn't exist
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the uploaded file to the server
            String fileName = pdfFile.getOriginalFilename();
            File destFile = new File(uploadDirectory + fileName);
            pdfFile.transferTo(destFile);

            // Handle the uploaded file (e.g., save to a database)
            // Implement your logic here

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }
    }
}
