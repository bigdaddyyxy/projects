package com.baizhi.travels.controller;

import com.baizhi.travels.entity.Place;
import com.baizhi.travels.entity.Result;
import com.baizhi.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Value("${upload.dir}")
    private String realPath;

    /**
     * 保存景点
     * @param pic
     * @param place
     * @return
     * @throws IOException
     */
    @PostMapping("/save")
    public Result save(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();

        try{
            //文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;

            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            File file = new File(realPath);
            pic.transferTo(new File(file,newFileName));
            //保存place对象
            placeService.save(place);
            result.setMsg("保存景点信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 根据省份id查询景点
     * @param page
     * @param rows
     * @param provinceId
     * @return
     */
    @GetMapping("/findAllPlace")
    public Map<String,Object> findAllPlace(Integer page, Integer rows, String provinceId){
        HashMap<String, Object> result = new HashMap<>();
        page= page==null? 1:page;
        rows= rows==null? 4:rows;
        //景点集合
        List<Place> places = placeService.findByProvinceIdPage(page, rows, provinceId);
        //处理分页
        Integer counts = placeService.findByProvinceIdCounts(provinceId);
        //总页数
        Integer totalPage = counts%rows==0 ? counts/rows:counts/rows +1;
        result.put("places",places);
        result.put("page",page);
        result.put("totalPage",totalPage);
        return result;
    }

    /**
     * 修改景点信息
     * @param pic
     * @param place
     * @return
     */
    @PostMapping("/update")
    public Result update(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();

        try {
            String picpath = Base64Utils.encodeToString(pic.getBytes());
            place.setPicpath(picpath);
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            pic.transferTo(new File(realPath,newFileName));
            placeService.update(place);
            result.setMsg("修改景点成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }


        return result;
    }

    /**
     * 查询景点信息
     * @param id
     * @return
     */
    @GetMapping("/findOne")
    public Place findOne(String id){
        return placeService.findOne(id);
    }



    /**
     * 删除景点
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(String id){
        Result result = new Result();
        try {
            placeService.delete(id);
            result.setMsg("删除景点成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }


}

