package com.eviro.assessment.grad001.sihlendlovu.enviroassessment.controllers;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eviro.assessment.grad001.sihlendlovu.enviroassessment.services.AccountProfileService;


@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    private final AccountProfileService accountProfileService; // inject account profile service
    public ImageController(AccountProfileService accountProfileService) {
        this.accountProfileService = accountProfileService;
    }

    @GetMapping("/{name}/{surname}")
    public ResponseEntity<FileSystemResource> getHttpImageLink(@PathVariable String name, @PathVariable String surname) throws URISyntaxException {
        var profile = accountProfileService.getProfileByNameAndSurname(name, surname);
        if(profile == null) return ResponseEntity.notFound().build();
      
        var resource = new FileSystemResource(new File(new URI(profile.getHttpImageLink()))); //create resource from link
        var imageType = profile.getHttpImageLink().substring(profile.getHttpImageLink().lastIndexOf(".")); //get image type from link
        switch (imageType) {//return correct content type based on image type from link. Default to jpeg.
            case ".png":
                return ResponseEntity.ok().contentType(org.springframework.http.MediaType.IMAGE_PNG).body(resource);
            default:
                return ResponseEntity.ok().contentType(org.springframework.http.MediaType.IMAGE_JPEG).body(resource);
        }
    }
}
