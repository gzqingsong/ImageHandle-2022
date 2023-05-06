package com.example.imagehandle2022;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.imagehandle2022.seal.mapper")//scan the package mapper.
@SpringBootApplication
public class ImageHandle2022Application {

	public static void main(String[] args) {
		SpringApplication.run(ImageHandle2022Application.class, args);
	}

}
