package com.hustar.edu.vote.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ThumbnailUploadHandler {
    @Autowired
    S3Uploader s3Uploader;

    @PostMapping("/thumbnailUpload")
    public UploadFile handleFileUpload(@RequestParam MultipartFile upload, String dir) {
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
//            String callback = request.getParameter("CKEditorFuncNum");

            uploadedFile = s3Uploader.upload(upload, dir);

            System.out.println("uri1 : " + uploadedFile.getFile_dir());
            System.out.println("file_nm1 : " + uploadedFile.getFile_nm());

//            printWriter = response.getWriter();
//            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
//                    + callback
//                    + ",'"
//                    + uploadedFile.getFile_dir()
//                    + "','이미지를 업로드 하였습니다.'"
//                    + ")</script>");
//            printWriter.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
        return uploadedFile;
    }
}
