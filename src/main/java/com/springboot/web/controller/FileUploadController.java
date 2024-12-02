package com.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.web.helper.FileUploadHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadHelper helper;

	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		/*
		 * System.out.println(file.getOriginalFilename());
		 * System.out.println(file.getSize());
		 * System.out.println(file.getContentType());
		 * System.out.println(file.getName());
		 */
		try {

			// validation

			if (file.isEmpty()) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contain file");
			}

			if (!file.getContentType().equals("image/jpeg")){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("only jpeg content type are allowed");
			}
			// file upload code

			boolean uploadFile = helper.uploadFile(file);
			if (uploadFile) {
//				return ResponseEntity.ok("file is uploaded successfully");
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image").path(file.getOriginalFilename()).toUriString());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ResponseEntity.ok("working..");
	}
}
