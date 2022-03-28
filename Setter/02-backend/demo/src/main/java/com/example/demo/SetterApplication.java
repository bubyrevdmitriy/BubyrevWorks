package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SetterApplication.class, args);
	}

	/*@StoreRestResource(path="video")
	public interface VideoStore extends Store<String> {}*/

}
