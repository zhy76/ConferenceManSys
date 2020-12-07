package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.dao.HotelDao;
import com.conference.entity.Driver;
import com.conference.entity.Hotel;
import com.conference.service.HotelService;
import com.conference.service.TokenService;
import com.conference.service.impl.TokenServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Hotel")
public class HotelController {
    @Autowired
    private TokenServiceImpl tokenServiceImpl;
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private HotelService hotelService;

    @GetMapping("/deleteHotel/{id}")
    public void deleteHotel(@PathVariable int id){
        hotelDao.deleteHotelById(id);
    }

    @PostMapping("/register")
    public Object register(@Valid @RequestBody Hotel hotel) {
        JSONObject jsonObject=new JSONObject();
        if(hotel.getHotelName() == null || hotel.getHotelPass() == null){
            jsonObject.put("message","表单错误");
            return jsonObject;
        }
        int addNumber = hotelService.addHotel(hotel);
        if(addNumber>0){
            String token= tokenServiceImpl.getToken(addNumber);
            jsonObject.put("token",token);
            return jsonObject;
        }else{
            jsonObject.put("message","注册失败");
            return jsonObject;
        }
//        return jsonObject;
    }

    @PostMapping("/login")
    public Object login(@RequestBody Hotel hotel) {
        JSONObject jsonObject = new JSONObject();
        if (hotel.getHotelPhone() == null || hotel.getHotelPass() == null) {
            System.out.println("null");
            jsonObject.put("message","表单错误");
            return jsonObject;
        }
        Hotel hotelForBase = hotelDao.getHotelByPhone(hotel.getHotelPhone());
        System.out.println(hotelForBase);
//        User userForBase =userDao.findByUsername(loginUser.getUserName());
        if(hotelForBase == null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!hotelForBase.getHotelPass().equals(hotel.getHotelPass())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenServiceImpl.getToken(hotelForBase);
                jsonObject.put("token", token);
                return jsonObject;
            }
        }
    }

    @GetMapping("/updateHotel/{hotelName}/{hotelLocation}/{hotelPhone}/{hotelPass}/{hotelInfo}/{hotelId}")
    public void updateDriver(@PathVariable String hotelName, @PathVariable String hotelLocation,
                             @PathVariable String hotelPhone, @PathVariable String hotelPass,
                             @PathVariable String hotelInfo, @PathVariable int hotelId) {
        hotelDao.updateHotel(hotelName,hotelLocation,hotelPhone,hotelPass,hotelInfo,hotelId);
    }
}
