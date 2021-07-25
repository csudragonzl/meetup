package edu.tjubd.meetup.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.tjubd.meetup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/statistics")
    public String membersJoinedAnalyze(@RequestParam(value = "topicName", required = false)String topicName){
        List<String> timestampList = List.of("1609430400000", "1612108800000", "1614528000000", "1617206400000",
                "1619798400000", "1622476800000", "1625068800000", "1627747200000",
                "1630425600000", "1633017600000", "1635696000000", "1638288000000", "1640966400000");
        List<String> x = List.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        Map result = new HashMap();
        result.put("x", x);
        List<Integer> y;
        if(topicName == null) {
            result.put("title", "2021年度总人数增长趋势");
            y = memberService.countAllMembers(timestampList);
        } else {
            result.put("title", "2021年度" + topicName + "主题总人数增长趋势");
            y = memberService.countMembersByTopic(timestampList, topicName);
        }
        result.put("y", y);
        return new Gson().toJson(result);
    }

    @RequestMapping("/biogroupsMembersNum")
    public String bioGroupsMembersNum(){
        List<String> timestampList = List.of("1262275200000", "1293811200000", "1325347200000", "1356969600000",
                "1388505600000", "1420041600000", "1451577600000", "1483200000000",
                "1514736000000", "1546272000000", "1577808000000", "1609430400000", "1640966400000");
        List<String> x = List.of("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021");
        JsonObject result = new JsonObject();
        JsonArray list = new JsonArray();
        List<Integer> y = memberService.countAllMembers(timestampList);
        for(int i=0;i<y.size();i++){
            JsonObject temp = new JsonObject();
            temp.addProperty("time", x.get(i));
            temp.addProperty("num", y.get(i));
            list.add(temp);
        }
        JsonObject msg =new JsonObject();
        msg.addProperty("msg","获取成功");
        msg.addProperty("status",200);
        result.add("data",list);
        result.add("meta",msg);
        return new Gson().toJson(result);
    }

    @RequestMapping("/biohackMemberByMonth2021")
//    @RequestMapping("/biohackMemberByMonth2021/{month}")
    public String memberJoinedByMonth(@RequestParam(value="month", required = true) Integer month)throws FileNotFoundException{
//    public String memberJoinedByMonth(@PathVariable("month") String month)throws FileNotFoundException{
        String result;
        JsonObject data;
        data=memberService.countMembersByMonth2021(month);
        result=data.toString();
//        return new Gson().toJson(result);
            return result;
    }
}
