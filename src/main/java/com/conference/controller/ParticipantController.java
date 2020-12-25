package com.conference.controller;

import com.alibaba.fastjson.JSONObject;
import com.conference.dao.JoinConferenceDao;
import com.conference.dao.ParticipantDao;
import com.conference.entity.JoinConference;
import com.conference.entity.Organizer;
import com.conference.entity.Participant;
import com.conference.service.JoinConferenceService;
import com.conference.service.ParticipantService;
import com.conference.service.TokenService;
import com.conference.util.result.Result;
import com.conference.util.result.ResultCode;
import io.jsonwebtoken.Claims;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author 谢 娇
 * @Date 2020/12/3 18:45
 * @sno 6109118015
 */

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ParticipantDao participantDao;

    @Autowired
    private ParticipantService participantService;



    //参加者注册
    @PostMapping("/register")
    public Result register(@Valid @RequestBody Participant participant) {
        int addNumber = participantService.addAParticipant(participant);

        //System.out.println(addNumber);
        if (addNumber > 0) {
            Participant newParticipant = participantService.queryParticipantByParticipantPhone(participant.getParticipantPhone());
            //String token = tokenService.getToken(addNumber);
            System.out.println(newParticipant);
            String token = tokenService.getToken(newParticipant);
            // System.out.println(token);
            return Result.success("token", token);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }

    //参加者登录
    @PostMapping("/login")
    public Result login(@RequestBody Participant participant) {
        Participant participantForBase = participantDao.queryParticipantByParticipantPhone(participant.getParticipantPhone());
        System.out.println(participantForBase);
        if (participantForBase == null) {
            return new Result(ResultCode.UnknownAccountException);
        } else {
            if (!participantForBase.getParticipantPass().equals(participant.getParticipantPass())) {
                return new Result(ResultCode.IncorrectCredentialsException);
            } else {
                String token = tokenService.getToken(participantForBase);
                return Result.success("token", token);
            }
        }
    }

    //参加者修改自己信息
    //@ResponseBody
    @PostMapping("/updateParticipant")
    //@GetMapping("/updateParticipant")
    public Result updateParticipant(@Valid @RequestBody  Participant participant, HttpServletRequest request) {
//        JSONObject result=new JSONObject();
        System.out.println(request.getHeader("token"));
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        participant.setParticipantId((Integer) claims.get("participantId"));
        System.out.println(participant.getParticipantId());
        int updateParticipant = participantService.updateParticipant(participant);
        System.out.println(updateParticipant);
        if(updateParticipant > 0)
            return Result.success("updateParticipant",updateParticipant);
        else
            return  new Result(ResultCode.FAIL);
    }

    /*
     * @Description 返回参会者已有的所有信息
     * @return
     **/

    @PostMapping("/getParticipantInfo")
    public Result getParticipantInfo(HttpServletRequest request){
        Claims claims = tokenService.parseToken(request.getHeader("token"));
        System.out.println("getParticipantInfo");
        Participant getParticipantInfo = participantService.queryParticipantByParticipantId((Integer) claims.get("participantId"));
        return Result.success("getParticipantInfo",getParticipantInfo);
    }
    @GetMapping("/queryParticipantByParticipantId")
    public Result queryParticipantByParticipantId(@RequestParam int participantId){

        //System.out.println("getParticipantInfo");
        Participant queryParticipantByParticipantId = participantService.queryParticipantByParticipantId(participantId);
        System.out.println(queryParticipantByParticipantId);
        return Result.success("queryParticipantByParticipantId",queryParticipantByParticipantId);
    }
    @GetMapping("/queryParticipantByParticipantPhone")
    public Result queryParticipantByParticipantPhone(@RequestParam String participantPhone){

        //System.out.println("getParticipantInfo");
        Participant queryParticipantByParticipantPhone = participantService.queryParticipantByParticipantPhone(participantPhone);
        System.out.println(queryParticipantByParticipantPhone);
        return Result.success("queryParticipantByParticipantPhone",queryParticipantByParticipantPhone);
    }
    //管理员修改参加者信息页面
    //修改参加者信息
    @PostMapping("/updateParticipantByAdmin")
    public Result postUpdateParticipant(Participant participant){
//        System.out.println(participant);
        int status = participantService.updateParticipant(participant);
//        System.out.println(status);
        return Result.success();
    }

    //管理员删除某参加者
    @PostMapping("/deleteParticipantByAdmin/{participantId}")
    @ResponseBody
    public Result getDeleteParticipant(@PathVariable("participantId") Integer participantId){
        participantService.deleteParticipant(participantId);
        return Result.success();
    }


//    //管理员查看所有的参加者
//    @RequestMapping("/showParticipants")
//    @ResponseBody
//    public Result showParticipants(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
//        PageHelper.startPage(pageNum,pageSize);
//        List<Participant> ParticipantList = participantService.queryParticipants();
//        PageInfo<Participant> pi = new PageInfo<>(ParticipantList);
//        return  Result.success(pi.getList());
//    }

    //管理员根据姓名查找所有的参加者
    @PostMapping("/queryParticipantsByParticipantName")
    @ResponseBody
    public Result queryParticipantsByParticipantName(@RequestParam("participantName") String participantName){
        List<Participant> participantList = participantService.fuzzyQueryParticipantByParticipantName(participantName);
        return  Result.success(participantList);
    }

    //管理员根据联系电话查找所有的参加者
    @PostMapping("/queryParticipantsByParticipantPhone")
    @ResponseBody
    public Result queryParticipantsByParticipantPhone(@RequestParam("participantPhone") String participantPhone){
        List<Participant> participantList = participantService.fuzzyQueryParticipantByParticipantPhone(participantPhone);
        return  Result.success(participantList);
    }


}
