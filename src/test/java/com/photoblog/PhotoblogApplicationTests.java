package com.photoblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.UUID;

//@SpringBootTest
class PhotoblogApplicationTests {

	@Test
	void contextLoads() {
		StringBuilder path = new StringBuilder().append(System.getProperty("user.home"));
		path.append(File.separator);
		path.append("photoblog");
		path.append(File.separator);
		path.append("album");

		System.out.println(path);

		File file = new File(path.toString());
		if(!file.exists()){
			file.mkdirs();
		}

		System.out.println( UUID.randomUUID().toString());
		System.out.println( UUID.randomUUID().toString());
		System.out.println( UUID.randomUUID().toString());
	}

}
