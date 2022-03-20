package com.siedla.socialnetwork.repositories;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public class FileSystemRepository {

    String RESOURCES_DIR = FileSystemRepository.class.getResource("/")
            .getPath();

    public String save(byte[] content, String imageName) throws Exception {
        String path = RESOURCES_DIR + new Date().getTime() + "-" + imageName;
        path = URLDecoder.decode(path, "utf-8");
        path = new File(path).getPath();
        System.out.println(path);
        Path newFile = Paths.get(path);
        Files.createDirectories(newFile.getParent());

        Files.write(newFile, content);

        return newFile.toAbsolutePath()
                .toString();
    }

    public FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
