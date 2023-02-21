package com.example.demo.controller;

import com.example.demo.core.RGBAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WebController {
    @Value("${store.file.path}")
    private String storeFilePath;

    @Autowired
    RGBAnalyzer rgbAnalyzer;

    @Autowired
    SimpleDateFormat simpleDateFormat;

    @RequestMapping("/getHello")
    public String getResponse(){
        return "Hello World";
    }

    @GetMapping("/rgb")
    public String image(@RequestParam(value = "image") String image) throws IOException {
        return rgbAnalyzer.core(image);
    }

    @PostMapping("/dev-api/vue-admin-template/uploadImg")
    // @RequestParam中的file名应与前端的值保持一致
    public String uploadImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        // replaceAll 用来替换windows中的\\ 为 /
        System.out.println("访问");
        String filename = upload(multipartFile);
        return rgbAnalyzer.core(filename);
        //return upload(multipartFile).replaceAll("\\\\", "/");
    }
    public String upload(MultipartFile multipartFile) {
        String filePath = storeFilePath+"/" +multipartFile.getOriginalFilename();
        //String filePath="D:/Users/Administrator/IdeaProjects/RGBAnalyzer/"+multipartFile.getOriginalFilename();;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }


}
