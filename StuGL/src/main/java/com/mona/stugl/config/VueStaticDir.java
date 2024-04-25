package com.mona.stugl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @ClassName VueStaticDir
 * @Author mona
 * @Date 2024/4/24 16:51
 * @Version 1.0
 * @Description TODO
 **/
@Component
@ConfigurationProperties(prefix = "vue.static")
@Data
public class VueStaticDir {
    private  String staticDir = "E:\\dev_work_space\\java\\StuGL\\StuGL\\src\\";
}
