package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.dao.HotelDao;
import com.conference.entity.Driver;
import com.conference.entity.Hotel;
import com.conference.service.HotelService;
import com.conference.service.impl.TokenServiceImpl;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
//    @Autowired
//    private TokenServiceImpl tokenServiceImpl;
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/deleteHotel")
    public Result deleteHotel(@RequestParam int id){
        int HotelNum = hotelService.deleteHotelById(id);
//        if (driverNum < 1) return new Result(ResultCode.FAIL);
        return Result.success();
    }


    @PostMapping("/register")
    public Result register(@Valid @RequestBody Hotel hotel) {
        //JSONObject jsonObject=new JSONObject();
        if(hotel.getHotelName() == null || hotel.getHotelPass() == null){
            return new Result(ResultCode.IllegalArgumentException);
        }
        int addNumber = hotelService.addHotel(hotel);
        Hotel hotelForBase = hotelService.getHotelByPhone(hotel.getHotelPhone());
        if(addNumber>0){
            String token = tokenService.getToken(hotelForBase);
            return Result.success("token", token);
        }else{
            return new Result(ResultCode.FAIL);
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody Hotel hotel) {
        if (hotel.getHotelPhone() == null || hotel.getHotelPass() == null) {
            return new Result(ResultCode.IllegalArgumentException);
        }
        Hotel hotelForBase = hotelService.getHotelByPhone(hotel.getHotelPhone());
        System.out.println(hotelForBase);
        if(hotelForBase == null){
            System.out.println("账号不存在");
            return new Result(ResultCode.UnknownAccountException);
        }else {
            if (!hotelForBase.getHotelPass().equals(hotel.getHotelPass())){
                return new Result(ResultCode.IncorrectCredentialsException);
            }else {
                String token = tokenService.getToken(hotelForBase);
                return Result.success("token", token);
            }
        }
    }
    @GetMapping("/getHotelById")
    public Result getHotelById(@RequestParam int hotelId){
        Hotel getHotelById = hotelService.getHotelById(hotelId);
        return Result.success("getHotelById", getHotelById);
    }
    @PostMapping("/getHotelInfo")
    public Result getHotelInfo(HttpServletRequest request) {
       // System.out.println(request.getHeader("token"));
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        Hotel getHotelInfo=hotelService.getHotelById((Integer) claims.get("hotelId"));
        //System.out.println(getHotelInfo);
        return Result.success("getHotelInfo", getHotelInfo);
    }
    @GetMapping("/getAllHotel")
        public Result getAllHotel() {
        List<Hotel> getAllHotel = hotelService.findAllHotel();
        return Result.success("getAllHotel", getAllHotel);
    }
    @PostMapping("/updateHotel")//??
    public Result updateHotel(@Valid @RequestBody Hotel hotel, HttpServletRequest request) {
        System.out.println(request.getHeader("token"));
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        hotel.setHotelId((Integer) claims.get("hotelId"));
        hotelService.updateHotel(hotel);
        //System.out.println("修改成功");
        return Result.success();
    }
//    @GetMapping("/updateHotel/{hotelName}/{hotelLocation}/{hotelPhone}/{hotelPass}/{hotelInfo}/{hotelId}")
//    public void updateHotel(@PathVariable String hotelName, @PathVariable String hotelLocation,
//                             @PathVariable String hotelPhone, @PathVariable String hotelPass,
//                             @PathVariable String hotelInfo, @PathVariable int hotelId) {
//        hotelDao.updateHotel(hotelName,hotelLocation,hotelPhone,hotelPass,hotelInfo,hotelId);
//    }
}
