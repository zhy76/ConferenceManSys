package com.conference.service;

import com.conference.mapper.DriverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DriverService
 * @Description: TODO
 * @Author: Lance
 * @Date: 2020/12/2 21:11
 */
@Service("DriverService")
public class DriverService {

    @Autowired
    DriverMapper driverMapper;




}
