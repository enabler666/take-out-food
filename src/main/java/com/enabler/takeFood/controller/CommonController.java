package com.enabler.takeFood.controller;

import com.enabler.takeFood.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 *文件的上传和下载
 * @author Enabler
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {


    @Value("${upload.path}")
    private String basePath;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String s = UUID.randomUUID().toString();
        File path = new File(basePath);

        if(! path.exists()){
            path.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + s + suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }

;        return R.success(s + suffix);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("image/jpeg");
        log.debug("{}", name);
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();

            int length = 0;
            byte[] bytes = new byte[1024];

            while((length = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, length);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
