package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.CreateImageCode;
import com.baizhi.util.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user){
        HashMap<String,Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            map.put("state",true);
            map.put("msg","登录成功");
            map.put("user",userDB);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","登录失败"+e.getMessage());
        }
        return map;
    }

    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody User user, String code, HttpServletRequest request){
        log.info("用户信息:[{}]",user.toString());
        log.info("输入信息:[{}]",code);
        Map<String,Object> map = new HashMap<>();
        try{
            String key = (String) request.getServletContext().getAttribute("code");
            if(key.equalsIgnoreCase(code)){
                userService.register(user);
                map.put("state", true);
                map.put("msg","注册成功");
            }else {
                throw new RuntimeException("验证码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("state", false);
            map.put("msg","注册失败:"+e.getMessage());
        }
        return map;
    }

    @GetMapping("/getImage")
    public Map<String,String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        // 获取验证码
        String securityCode = createImageCode.getCode();
        //验证码存入session
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        request.getServletContext().setAttribute("code",securityCode);
        //生成图片
        BufferedImage image = createImageCode.getBuffImg();
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image,"png",bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key",key);
        result.put("image",string);
        return result;
    }
//    @GetMapping("/getImage")
//    public String getImageCode(HttpServletRequest request) throws IOException {
//        String code = VerifyCodeUtils.generateVerifyCode(4);
//        //放入ServletContext作用域
//        request.getServletContext().setAttribute("code",code);
//        //图片转为base64
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        VerifyCodeUtils.outputImage(130,50,byteArrayOutputStream,code);
//        return  "data:image/png;base64" + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
//    }
//




}
/*
//使用工具类生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //将验证码放入servletContext作用域
        request.getServletContext().setAttribute("code",code);//得到最大作用域
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(130,50,byteArrayOutputStream,code);
        return "data:image/png;base64,"+Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
 */