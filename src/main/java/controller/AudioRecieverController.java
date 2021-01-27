package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import service.ConvertToAudio;
import service.StorageService;


@RestController
@RequestMapping("/convert")
public class AudioRecieverController {
    @Autowired
    private ConvertToAudio convertToAudio;


    @CrossOrigin(origins = "*")
    @PostMapping("/video")
    ResponseEntity<Resource> upoadFile(@RequestParam("file") MultipartFile[] videoFile) throws IOException {

        try {
            String fileName = videoFile[0].getOriginalFilename();
            System.out.println("fileName:" + fileName);


            String userDir = Paths.get("").toAbsolutePath().toString();
            Path videoSavingDir = Paths.get(userDir + "/" + videoFile[0].getOriginalFilename());
            System.out.println("userDir:" + userDir);
            Files.createFile(videoSavingDir);
            File source = new File(videoSavingDir.toString());
            FileOutputStream outputStream = new FileOutputStream(source);
            byte[] bytes = videoFile[0].getInputStream().readAllBytes();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();

            this.convertToAudio.getAudioFromVideo(videoSavingDir);

            Files.deleteIfExists(videoSavingDir);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        File file = new File("destin.mp3");
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Type", "audio/mpeg");
        headers.add("Accept", "audio/mpeg");

        ResponseEntity<Resource> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
        Files.deleteIfExists(path);
        return responseEntity;
    }
}
