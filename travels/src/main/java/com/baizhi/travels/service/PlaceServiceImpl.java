package com.baizhi.travels.service;

import com.baizhi.travels.dao.PlaceDAO;
import com.baizhi.travels.entity.Place;
import com.baizhi.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDAO placeDAO;
    @Autowired
    private ProvinceService provinceService;

    @Override
    public void save(Place place) {
        placeDAO.save(place);
        //查询省份信息
        Province province = provinceService.findOne(place.getProvinceid());
        //更新省份景点个数
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.update(province);
    }

    @Override
    public void delete(String id) {
        Place place = placeDAO.findOne(id);
        Province province = provinceService.findOne(place.getProvinceid());
        province.setPlacecounts(province.getPlacecounts()-1);
        provinceService.update(province);
        placeDAO.delete(id);
    }

    @Override
    public Place findOne(String id) {
        return placeDAO.findOne(id);
    }

    @Override
    public void update(Place place) {
        placeDAO.update(place);
    }

    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = (page-1)*rows;
        return placeDAO.findByProvinceIdPage(start,rows,provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDAO.findByProvinceIdCounts(provinceId);
    }
}

