package edu.tjubd.meetup.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.tjubd.meetup.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping("/2021")
    public String groupsJoinedAnalyze() throws FileNotFoundException {
        List<String> timestampList = List.of("1612022400000", "1614441600000", "1617120000000", "1619712000000",
                "1622390400000", "1624982400000", "1627660800000", "1630339200000",
                "1632931200000", "1635609600000", "1638201600000", "1640880000000");
        List<Integer> x = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<String> y;
        y = groupService.countAllGroups(timestampList);
        List<Map<String,Object>> result = new ArrayList<>();
        for(int i = 0; i < timestampList.size(); i++){
            Map<String,Object> ans = new HashMap<String,Object>();
            ans.put("time", x.get(i));
            ans.put("num", y.get(i));
            result.add(ans);
        }
        Map<String,Object> meta = new HashMap<String,Object>();
        meta.put("msg","获取成功");
        meta.put("status",200);
        Map<String,Object> re = new HashMap<String,Object>();
        re.put("data",result);
        re.put("meta",meta);
        return new Gson().toJson(re);
    }

    @RequestMapping("/biohackGroupsNum")
    public String biohackGroupsNumAnalyze(@RequestParam(value = "year", required = false)String year) throws FileNotFoundException {
        List<String> timestampList = List.of("1041264000000", "1072800000000", "1104422400000", "1135958400000",
                "1167494400000", "1199030400000", "1230652800000", "1262188800000","1293724800000", "1325260800000",
                "1356883200000", "1388419200000","1419955200000","1451491200000","1483113600000","1514649600000","1546185600000","1577721600000","1609344000000","1640880000000");
        List<Integer> x = List.of(2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021);
        List<String> y;
        y = groupService.countAllGroups(timestampList);
        List<Map<String,Object>> result = new ArrayList<>();
        for(int i = 0; i < timestampList.size(); i++){
            Map<String,Object> ans = new HashMap<String,Object>();
            ans.put("time", x.get(i));
            ans.put("num", y.get(i));
            result.add(ans);
        }
        Map<String,Object> meta = new HashMap<String,Object>();
        meta.put("msg","获取成功");
        meta.put("status",200);
        Map<String,Object> re = new HashMap<String,Object>();
        re.put("data",result);
        re.put("meta",meta);
        return new Gson().toJson(re);
    }

    @RequestMapping("/biohackCountryAdd")
//    @RequestMapping("/biohackMemberByMonth2021/{month}")
    public String memberJoinedByMonth(@RequestParam(value="year", required = false) Integer year)throws FileNotFoundException, ParseException {
//    public String memberJoinedByMonth(@PathVariable("month") String month)throws FileNotFoundException{
        String result;
        JsonObject data;
        data=groupService.countGroupByYear(year);
        result=data.toString();
//        return new Gson().toJson(result);
        return result;
    }

}
