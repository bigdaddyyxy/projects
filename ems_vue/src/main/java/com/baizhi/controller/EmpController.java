package com.baizhi.controller;

import com.baizhi.entity.Employee;
import com.baizhi.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/emp")
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    @Value("${photo.dir}")
    private String realPath;



    @GetMapping("/findOne")
    public Employee findOne(String id){
        log.info("id:[{}]",id);

        return empService.findOne(id);
    }

    //修改
    @PostMapping("/update")
    public Map<String,Object> update(Employee employee, MultipartFile photo) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        try {
            if (photo != null && photo.getSize()!=0){
                //头像保存
                String newFileName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(photo.getOriginalFilename());
                photo.transferTo(new File(realPath,newFileName));
                //设置头像地址
                employee.setPath(newFileName);
            }
            empService.update(employee);
            map.put("state",true);
            map.put("msg","员工信息保存成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","员工信息保存失败");
        }
        return map;
    }

    //删除
    @GetMapping("/delete")
    public Map<String,Object> deleteById(String id){
        HashMap<String, Object> map = new HashMap<>();
        log.info("删除员工的id[{}]",id);
        try {
            Employee emp = empService.findOne(id);
            File file = new File(realPath, emp.getPath());
            if(file.exists()) file.delete();

            empService.deleteById(id);
            map.put("state",true);
            map.put("msg","删除员工成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","删除员工失败"+e.getMessage());
        }

        return map;
    }

    @PostMapping("/save")
    public Map<String,Object> save(Employee employee, MultipartFile photo) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        try {
            //头像保存
            String newFileName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(photo.getOriginalFilename());
            photo.transferTo(new File(realPath,newFileName));
            //设置头像地址
            employee.setPath(newFileName);

            empService.save(employee);
            map.put("state",true);
            map.put("msg","员工信息保存成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","员工信息保存失败");
        }

        return map;
    }

    //获取所有员工
    @GetMapping("/findAll")
    public List<Employee> findAll(){
        return empService.findAll();
    }

}
