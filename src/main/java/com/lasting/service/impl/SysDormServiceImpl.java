package com.lasting.service.impl;

import com.lasting.entity.SysDorm;
import com.lasting.mapper.SysDormMapper;
import com.lasting.service.ISysDormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDormServiceImpl implements ISysDormService {
    @Autowired
    SysDormMapper dormMapper;

    @Override
    public List<SysDorm> selectDormAll() {
        return dormMapper.selectDormAll();
    }

    @Override
    public Long getDormId(String dormNumber, String buildingNumber) {
        if(dormNumber!=null&&buildingNumber!=null) return dormMapper.getDormId(dormNumber, buildingNumber);
        return null;
    }

    @Override
    public SysDorm getDormByDormId(Long dormId) {
        if(dormId ==null) return null;
        return dormMapper.getDormByDormId(dormId);
    }

    @Override
    public int insertDorm(String dormNumber, String buildingNumber) {
        if(dormNumber!=null&&buildingNumber!=null) return dormMapper.insertDorm(dormNumber,buildingNumber);
        return 0;
    }


}
