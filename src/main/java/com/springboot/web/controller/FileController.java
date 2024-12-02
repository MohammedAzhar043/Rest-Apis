package com.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.web.helper.Helper;

@RestController
public class FileController {

	@Autowired
	public Helper help;

	@PostMapping("/upload")
	public ResponseEntity<String> uploading(@RequestParam("files") MultipartFile file) {

		/*
		 * System.out.println(file.getContentType());
		 * System.out.println(file.getOriginalFilename());
		 * System.out.println(file.getName());
		 */

		try {

			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg files are allowed");
			}

			boolean uploadtheimage = help.uploadtheimage(file);

			if (uploadtheimage) {
//				return ResponseEntity.ok("uploaded thank you");
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("image").path(file.getOriginalFilename()).toUriString());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing went wrong");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.ok("working file");
	}
}
