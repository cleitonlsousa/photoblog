package com.photoblog;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

//@SpringBootTest
class PhotoblogApplicationTests {

	@Test
	void contextLoads() throws IOException {

		String path = "C:\\Users\\Cleiton\\photoblog\\photo\\0720f334-7d66-4bb2-9a8b-b33900981db2\\1\\img03.jpg";

		InputStream in = getClass().getResourceAsStream(path);
		byte[] arquivo = IOUtils.toByteArray(in);
		System.out.println(arquivo.length);

	}

}
