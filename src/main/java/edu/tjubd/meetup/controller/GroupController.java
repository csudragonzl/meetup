package edu.tjubd.meetup.controller;

import com.google.gson.Gson;
import edu.tjubd.meetup.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping("/biogroupsactiv")
    public String biogroupsActive(@RequestParam(value = "isUS", required = false)Boolean isUS,@RequestParam(value = "near", required = false)Boolean near)throws FileNotFoundException {
        List<String> timestampList = List.of("1609430400000", "1612108800000", "1614528000000", "1617206400000",
                "1619798400000", "1622476800000", "1625068800000", "1627747200000",
                "1630425600000", "1633017600000", "1635696000000", "1638288000000", "1640966400000");
        List<String> x = List.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        Map result = new HashMap();
        List<Integer> y;
        if(isUS== null || isUS == false) {
            if(near == null ||near==false) {
                List<Map> tmp = groupService.countEventNum();
                result.clear();
                result.put("data",tmp);
                Map rs1 = new HashMap();
                rs1.put("msg","获取成功");
                rs1.put("status",200);
                result.put("meta",rs1);
                return new Gson().toJson(result);
            }
            else{
                y = groupService.countEventsNum(timestampList,false);
            }
        }
        else {
            y=groupService.countEventsNum(timestampList,true);;
        }
        List<Map> res = new ArrayList<>();


        for(int i =0;i<x.size();i++){
            res.add(Map.of("time",x.get(i),"num",y.get(i)));
        }
        result.put("data",res);
        Map rs1 = new HashMap();
        rs1.put("msg","获取成功");
        rs1.put("status",200);
        result.put("meta",rs1);
        return new Gson().toJson(result);
    }
}
