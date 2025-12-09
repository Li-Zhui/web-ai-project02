package com.itheima.controller;


import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(String name , Integer age , MultipartFile file) throws IOException {
        log.info("接收到的参数：{},{},{}" , name , age , file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();//1.jpg 2.png

        //新的文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //截取最后一个.之后的字符串（获取文件后缀）
        String newFileName = UUID.randomUUID().toString() + extension;

        //保存文件
        file.transferTo(new File("D/images/" + newFileName));
        return Result.success();
    }

}
