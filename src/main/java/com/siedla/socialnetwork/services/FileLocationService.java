package com.siedla.socialnetwork.services;

import com.siedla.socialnetwork.repositories.FileSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileLocationService {

    @Autowired
    FileSystemRepository fileSystemRepository;

    public String save(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);
        return location;
    }


}
