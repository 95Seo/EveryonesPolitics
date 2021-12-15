package com.hustar.edu.vote.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ThumbnailUploadHandler {
    @Autowired
    S3Uploader s3Uploader;

    public UploadFile thumbnailFileUpload(@RequestParam MultipartFile upload, String dir) {
        OutputStream out = null;
        UploadFile uploadedFile = null;

        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // 포맷 적용
        String formatedNow = now.format(formatter);

        dir += "/IMAGES/" + formatedNow;

        System.out.println("dir_nm : " + dir + "/IMAGES/" + formatedNow + "/");

        System.out.println("uploadName : "+upload.getOriginalFilename());

        try {
            uploadedFile = s3Uploader.upload(upload, dir);

            System.out.println("uri1 : " + uploadedFile.getFile_dir());
            System.out.println("file_nm1 : " + uploadedFile.getFile_nm());

        }catch(Exception e){
            e.printStackTrace();
        }
        return uploadedFile;
    }

    public UploadFile profileFileUpload(@RequestParam MultipartFile upload, String dir, int user_idx) {
        OutputStream out = null;
        UploadFile uploadedFile = null;

        System.out.println("dir_nm : " + dir + "/IMAGES/" + user_idx + "/");

        System.out.println("uploadName : "+upload.getOriginalFilename());

        try {
            uploadedFile = s3Uploader.upload(upload, dir);

            System.out.println("uri1 : " + uploadedFile.getFile_dir());
            System.out.println("file_nm1 : " + uploadedFile.getFile_nm());

        }catch(Exception e){
            e.printStackTrace();
        }
        return uploadedFile;
    }
}
