package com.oppo.oppo.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public Map upload(MultipartFile file)  {
        try{
            Map data = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            String url = (String) data.get("url");

            return Map.of("url", url);
        }catch (IOException io){
            throw new RuntimeException("Image upload fail");
        }
    }
}