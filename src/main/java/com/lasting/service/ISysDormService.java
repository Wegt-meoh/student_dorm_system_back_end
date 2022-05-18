package com.lasting.service;

import com.lasting.entity.SysDorm;

import java.util.List;

public interface ISysDormService {
    public List<SysDorm> selectDormAll();
    public Long getDormId(String dormNumber,String buildingNumber);
    public SysDorm getDormByDormId(Long dormId);
}
