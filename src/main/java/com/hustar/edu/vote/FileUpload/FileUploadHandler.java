package com.hustar.edu.vote.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

@RestController
public class FileUploadHandler {

    @Autowired
    S3Uploader s3Uploader;

    @RequestMapping(value = "/image/drag", produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
    @ResponseBody
    public Object handleFileUpload(@RequestParam("upload") MultipartFile uploadfile) {
        
        System.out.println("오십니까");

        System.out.println("uploadName : "+uploadfile.getOriginalFilename());

        HashMap<String, Object> map = new HashMap<>();

        try {
            System.out.println("오십니까3");
            UploadFile uploadedFile = s3Uploader.upload(uploadfile);
            System.out.println("오십니까4");
            map.put("uploaded", 1);
            map.put("url", uploadedFile.getFile_dir());
            map.put("fileName", uploadedFile.getFile_nm());

            System.out.println("uri : " + uploadedFile.getFile_dir());
            System.out.println("file_nm : " + uploadedFile.getFile_nm());

            return map;
        } catch (Exception e) {
            map.put("uploaded", 0);
            map.put("error", "{'message': '" + e.getMessage() + "'}");
            return map;
        }
    }

    @PostMapping("/image")
    public void handleFileUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {
        OutputStream out = null;
        PrintWriter printWriter = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        System.out.println("uploadName : "+upload.getOriginalFilename());

        try {
            String callback = request.getParameter("CKEditorFuncNum");

            System.out.println("오십니까5");
            UploadFile uploadedFile = s3Uploader.upload(upload);
            System.out.println("오십니까6");

            System.out.println("uri1 : " + uploadedFile.getFile_dir());
            System.out.println("file_nm1 : " + uploadedFile.getFile_nm());

            printWriter = response.getWriter();
            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                    + callback
                    + ",'"
                    + uploadedFile.getFile_dir()
                    + "','이미지를 업로드 하였습니다.'"
                    + ")</script>");
            printWriter.flush();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
