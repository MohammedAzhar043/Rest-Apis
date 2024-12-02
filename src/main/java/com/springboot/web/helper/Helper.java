package com.springboot.web.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.MulticastChannel;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Helper {

//	public final String space = "C:\\Users\\HP\\Documents\\workspace-spring-tool-suite-4-4.26.0.RELEASE\\BootRestApiBook\\src\\main\\resources\\static\\image";

	public Helper() throws IOException {

	}

	public final String space = new ClassPathResource("static/image/").getFile().getAbsolutePath();

	public boolean uploadtheimage(MultipartFile multipart) {

		boolean f = false;
		try {

			InputStream inputStream = multipart.getInputStream();
			byte data[] = new byte[inputStream.available()];
			inputStream.read(data);

			FileOutputStream fos = new FileOutputStream(space + File.separator + multipart.getOriginalFilename());

			fos.write(data);
			fos.flush();
			fos.close();
			f = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return f;
	}
}
