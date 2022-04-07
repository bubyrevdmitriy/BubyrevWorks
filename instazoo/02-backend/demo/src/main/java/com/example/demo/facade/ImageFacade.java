package com.example.demo.facade;

import com.example.demo.dto.ImageDTO;
import com.example.demo.entity.ImageModel;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class ImageFacade {
    private final ImageService imageService;

    @Autowired
    public ImageFacade(ImageService imageService) {
        this.imageService = imageService;
    }

    public ImageDTO imageToImageDTO(ImageModel imageModel) throws IOException {
        ImageDTO imageDTO = new ImageDTO();
        String address = imageModel.getAddress();
        imageDTO.setId(imageModel.getId());
        imageDTO.setName(imageModel.getName());
        imageDTO.setImageBytes(convertImageAddressToByteArray(address));
        imageDTO.setCreatedDate(imageModel.getCreatedDate());
        return imageDTO;
    }

    public byte[] convertImageAddressToByteArray(String address) throws IOException {
        File file = new File(address);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }
}
