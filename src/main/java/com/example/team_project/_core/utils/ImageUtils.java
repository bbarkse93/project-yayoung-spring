package com.example.team_project._core.utils;

import com.example.team_project._core.errors.exception.Exception400;
import com.example.team_project._core.errors.exception.Exception404;
import com.example.team_project._core.errors.exception.Exception500;
import com.example.team_project._core.vo.MyPath;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class ImageUtils {

    // 사진 파일 1개 디코딩
    public static String decodeImage(String pic, String startFileName){
        try {
            byte[] image = Base64.getDecoder().decode(pic);
            UUID uuid = UUID.randomUUID();
            String fileName = startFileName + "_" + uuid + ".png";
            Path filePath = Paths.get(MyPath.USER_IMG_PATH, fileName);
            Files.write(filePath, image);
            return fileName;
        }catch (Exception e){
            throw new Exception400("파일 디코딩에 실패했습니다.");
        }
    }

    // 사진 파일 List 디코딩
    public static List<String> decodeImageList(List<String> picList, String startFileName){

        List<String> decodeImageList = new ArrayList<>();

        for (String pic : picList ) {
            try {
                byte[] image = Base64.getDecoder().decode(pic);
                UUID uuid = UUID.randomUUID();
                String fileName = startFileName + "_" + uuid + ".png";
                Path filePath = Paths.get(MyPath.USER_IMG_PATH, fileName);
                Files.write(filePath, image);
                decodeImageList.add(filePath.toString());
            }catch (Exception e){
                throw new Exception400("파일 디코딩에 실패했습니다.");
            }
        }
        return decodeImageList;
    }

    // web 사진 파일 등록
    public static String formImage(MultipartFile pic) {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + pic.getOriginalFilename();
        System.out.println("fileName : " + fileName);

        Path filePath = Paths.get(MyPath.IMG_PATH + fileName);
        try {
            Files.write(filePath, pic.getBytes());
        } catch (Exception e) {
            // 폴더, 경로, 파일 등의 오류 처리
            throw new Exception404(e.getMessage());
        }

        return fileName;
    }
}
