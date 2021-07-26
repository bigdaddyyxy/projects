package com.baizhi.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultCaptcha;

 @GetMapping(value = "/captcha")
 public void Kaptcha(HttpServletRequest request, HttpServletResponse response){
       // 生成验证码图片必要的设置
       // 定义response输出类型为image/jpeg类型
       response.setDateHeader("Expires", 0);
       // Set standard HTTP/1.1 no-cache headers.
       response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
       // Set IE extended HTTP/1.1 no-cache headers (use addHeader)
       response.addHeader("Cache-Control", "post-check=0, pre-check=0");
       // Set standard HTTP/1.0 no-cache header.
       response.setHeader("Pragma", "no-cache");
       // return a jpeg
       response.setContentType("image/jpeg");
       //---------------生成验证码 begin --------------
       // 获取验证码文本内容
       String text = defaultCaptcha.createText();
       // 将验证码文本内容放入session
       request.getSession().setAttribute("captcha", text);
       // 根据文本验证码内容创建图形验证码
       BufferedImage image = defaultCaptcha.createImage(text);
       ServletOutputStream os = null;
       try {
           os = response.getOutputStream();
           // 输出流输出图片，格式为jpg
           ImageIO.write(image, "jpg", os);
           os.flush();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (null != os) {
               try {
                   os.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
 }

}

