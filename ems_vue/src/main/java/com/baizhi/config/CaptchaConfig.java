package com.baizhi.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class CaptchaConfig {

    @Bean
    public DefaultKaptcha defaultCaptcha() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "10");
        properties.put("kaptcha.textproducer.char.length","4");
        properties.put("kaptcha.image.height","34");
        properties.put("kaptcha.textproducer.font.size","25");

        properties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }


//    @Bean
//    public DefaultKaptcha defaultCaptcha(){
//
//        // 验证码生成器
//        DefaultKaptcha defaultCaptcha = new DefaultKaptcha();
//        // 配置
//        Properties properties = new Properties();
//        // 是否有边框
//        properties.setProperty("kaptcha.border", "yes");
//        // 设置冰块颜色
//        properties.setProperty("kaptcha.border.color", "105,179,90");
//        // 边框粗细度，默认为1
//        //properties.setProperty("kaptcha.border.thickness", "1");
//        // 验证码
//        properties.setProperty("kaptcha.session.key", "code");
//        // 验证码文本字符颜色，默认黑色
//        properties.setProperty("kaptcha.textproducer.font.color", "blue");
//        // 设置字体样式
//        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
//        // 字体大小，默认为40
//        properties.setProperty("kaptcha.textproducer.font.size", "30");
//        // 验证码文本字符内容范围 默认为abcd2345678gfynmnpwx
//        // properties.setProperty("kaptcha.textproducer.char.string", "");
//        // 字符长度 默认为5
//        properties.setProperty("kaptcha.textproducer.char.length", "4");
//        // 字符间距 默认为2
//        properties.setProperty("kaptcha.textproducer.char.space", "4");
//        // 验证码图片宽度 默认为200
//        properties.setProperty("kaptcha.image.width", "100");
//        // 验证码图片高度 默认为40
//        properties.setProperty("kaptcha.image.height", "40");
//        Config config = new Config(properties);
//        defaultCaptcha.setConfig(config);
//        return defaultCaptcha;
//    }
}