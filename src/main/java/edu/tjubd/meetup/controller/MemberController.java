package edu.tjubd.meetup.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.tjubd.meetup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String membersJoinedAnalyze(@RequestParam(value = "topicName", required = false)String topicName) throws FileNotFoundException {
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
}
