package com.example.team_project._core.utils;

import com.example.team_project._core.errors.exception.Exception400;
import com.example.team_project._core.vo.MyPath;

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
    public static List<String> encodeImageList(List<String> picList, String startFileName){

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
}
