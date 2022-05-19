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
        return dormMapper.getDormId(dormNumber, buildingNumber);
    }

    @Override
    public SysDorm getDormByDormId(Long dormId) {
        return dormMapper.getDormByDormId(dormId);
    }

    @Override
    public int insertDorm(String dormNumber, String buildingNumber) {
        return dormMapper.insertDorm(dormNumber,buildingNumber);
    }


}
