package com.example.demo.exception;


public class AudioFileNotFoundException extends RuntimeException{
   public AudioFileNotFoundException(String message) {
        super(message);
    }
}
