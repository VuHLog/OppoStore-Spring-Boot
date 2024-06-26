package com.oppo.oppo.Configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloundinaryConfig {
    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "iflixlong");
        config.put("api_key", "988472275693512");
        config.put("api_secret", "DzVrojgtCpKJF7clYSEMfsHgiHo");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
