package com.hustar.edu.vote.FileUpload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hustar.edu.vote.auth.PrincipalDetail;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    @Autowired
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    public UploadFile upload(MultipartFile multipartFile) throws IOException {
        System.out.println("오십니까1");
        File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));

        return upload(uploadFile);
    }

    // S3로 파일 업로드하기
    private UploadFile upload(File uploadFile) {
        System.out.println("오십니까2");
        String fileName = "BOARD/IMAGES/"+UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름   // S3에 저장된 파일 이름
        System.out.println("fileName : " + fileName);
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        System.out.println("uploadImageUrl : " + uploadImageUrl);
        removeNewFile(uploadFile);
        UploadFile upload = new UploadFile();
        upload.setFile_nm(fileName);
        upload.setFile_dir(uploadImageUrl);
        System.out.println("uploadImageUrl1 : " + uploadImageUrl);
        return upload;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        System.out.println("안 오시나요 1 ");
        System.out.println("bucket :" + bucket);
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        System.out.println("안 오시나요 2");
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
}