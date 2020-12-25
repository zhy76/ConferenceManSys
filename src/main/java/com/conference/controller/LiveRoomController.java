package com.conference.controller;

import com.conference.entity.LiveRoom;
import com.conference.service.HotelService;
import com.conference.service.LiveRoomService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/liveRoom")
public class LiveRoomController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private LiveRoomService liveRoomService;

    //得到全部住宿
    @GetMapping("/getAllLiveRoom")
    public Result getAllLiveRoom() {
        List<LiveRoom> getAllLiveRoom= liveRoomService.findAllLiveRoom();
        return Result.success("getAllHotel", getAllLiveRoom);
    }
    //通过会议找住宿
    @GetMapping("/findAllLiveRoomByConferenceId")
    public Result findAllLiveRoomByConferenceId(@RequestParam("conferenceId") Integer conferenceId) {
        List<LiveRoom> findAllLiveRoomByConferenceId = liveRoomService.findAllLiveRoomByConferenceId(conferenceId);
        if (conferenceId == null)
            return new Result(ResultCode.IllegalArgumentException);
        return Result.success("findAllLiveRoomByConferenceId", findAllLiveRoomByConferenceId);
    }
    //通过参会者查找住宿
    @GetMapping("/findAllLiveRoomByParticipantId")
    public Result findAllLiveRoomByParticipantId(@RequestParam("participantId") Integer participantId) {
        List<LiveRoom> findAllLiveRoomByParticipantId = liveRoomService.findAllLiveRoomByParticipantId(participantId);
        if (participantId == null)
            return new Result(ResultCode.IllegalArgumentException);
        return Result.success("findAllLiveRoomByParticipantId", findAllLiveRoomByParticipantId);
    }
    //通过酒店查找住宿
    @GetMapping("/findAllLiveRoomByHotelId")
    public Result findAllLiveRoomByHotelId(@RequestParam("hotelId") Integer hotelId) {
        List<LiveRoom> findAllLiveRoomByHotelId = liveRoomService.findAllLiveRoomByHotelId(hotelId);
        if (hotelId == null)
            return new Result(ResultCode.IllegalArgumentException);
        return Result.success("findAllLiveRoomByHotelId", findAllLiveRoomByHotelId);
    }
    //精确查询住宿
    @GetMapping("/findAllLiveRoomByAll")
    public Result findAllLiveRoomByAll(@RequestParam("participantId") Integer participantId,
                                       @RequestParam("conferenceId") Integer conferenceId,
                                       @RequestParam("hotelId") Integer hotelId) {
        List<LiveRoom> findAllLiveRoomByAll = liveRoomService.findAllLiveRoomByAll(participantId,conferenceId,hotelId);
        if (hotelId == null)
            return new Result(ResultCode.IllegalArgumentException);
        return Result.success("findAllLiveRoomByAll", findAllLiveRoomByAll);
    }
    //增加住宿记录!
    @PostMapping("/addLiveRoom")
    public Result addLiveRoom(@Valid @RequestBody LiveRoom liveRoom) {
        //JSONObject result=new JSONObject();
        int addLiveRoom = liveRoomService.addLiveRoom(liveRoom);
        return Result.success("addLiveRoom", addLiveRoom);
    }
    //根据会议和参会者删除记录
    @GetMapping("/deleteLiveRoom")
    public Result deleteLiveRoom(@RequestParam("participantId") Integer participantId,
                                 @RequestParam("conferenceId") Integer conferenceId) {
        int deleteLiveRoom = liveRoomService.deleteLiveRoom(participantId,conferenceId);
        if (participantId == null||conferenceId == null)
            return new Result(ResultCode.IllegalArgumentException);
        return Result.success("deleteLiveRoom", deleteLiveRoom);
    }
    //更新房间号!
    @PostMapping("/updateLiveRoom")
    public Result updateLiveRoom(@Valid @RequestBody LiveRoom liveRoom) {
        //JSONObject result=new JSONObject();
        int updateLiveRoom = liveRoomService.updateLiveRoom(liveRoom);
        return Result.success("updateLiveRoom", updateLiveRoom);
    }
    //精确删除
    @GetMapping("/deleteLiveRoomByAll")
    public Result deleteLiveRoomByAll(@RequestParam("participantId") Integer participantId,
                                      @RequestParam("conferenceId") Integer conferenceId,
                                      @RequestParam("hotelId") Integer hotelId) {
        int deleteLiveRoomByAll = liveRoomService.deleteLiveRoomByAll(participantId,conferenceId,hotelId);
        if (participantId == null||conferenceId == null||hotelId == null)
            return new Result(ResultCode.IllegalArgumentException);
        return Result.success("deleteLiveRoomByAll", deleteLiveRoomByAll);
    }
}
