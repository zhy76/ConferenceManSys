package com.conference.controller;

import com.conference.entity.Hotel;
import com.conference.entity.Room;
import com.conference.service.HotelService;
import com.conference.service.RoomService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/deleteRoomById")
    public Result deleteRoomById(@RequestParam int hotelId,@RequestParam String roomId){
        int deleteRoomById = roomService.deleteRoomById(hotelId,roomId);
        return Result.success();
    }

    @GetMapping("/getRoomByHotelId")
    public Result getRoomByHotelId(@RequestParam int hotelId){
        List<Room> getRoomByHotelId = roomService.getRoomByHotelId(hotelId);
        return Result.success("getRoomByHotelId",getRoomByHotelId);
    }
    @PostMapping("/addRoom")
    public Result addRoom(@RequestBody Room room){
        int addRoom = roomService.addRoom(room);
        return Result.success();
    }
    @PostMapping("/updateRoom")
    public Result updateRoom(@RequestBody Room room) {
        roomService.updateRoom(room);
        return Result.success();
    }
}
