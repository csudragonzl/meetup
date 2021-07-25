package edu.tjubd.meetup.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.tjubd.meetup.service.GroupService;
import edu.tjubd.meetup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    //生物黑客组织创建年度数量 无参数需求
    @RequestMapping("/biohackNum")
    public String biohackCreatedByYear() throws FileNotFoundException{
        List<String> timestampList = List.of("1009814400000", "1041350400000", "1072886400000", "1104508800000",
                                    "1136044800000", "1167580800000", "1199116800000", "1230739200000", "1262275200000",
                                    "1293811200000", "1325347200000", "1356969600000", "1388505600000", "1420041600000", "1451577600000",
                                    "1483200000000", "1514736000000", "1546272000000", "1577808000000", "1609430400000", "1640966400000");
        List<Integer> x = List.of(2002, 2003, 2004, 2005, 2006, 2007, 2008,2009,
                                2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018,
                                2019, 2020, 2021);
        Map result = new HashMap();
        List<String> countByYear = groupService.countAllGroups(timestampList);
        List<Map> time_num = new ArrayList<>();
        for(int i = 0; i < x.size(); i++){
            Map tmp = new HashMap();
            tmp.put("time", x.get(i));
            tmp.put("num", countByYear.get(i));
            time_num.add(tmp);
        }
        Map meta = new HashMap();
        meta.put("msg","获取成功");
        meta.put("status", 200);

        result.put("data",time_num);
        result.put("meta", meta);
        return new Gson().toJson(result);
    }
    // 生物黑客组织每个国家创建年度数量（当年新创建）
    @RequestMapping("/biohackCountry")
    public String biohackCreatedByCountry(@RequestParam(value = "year", defaultValue = "2021")int year) throws FileNotFoundException{
        List<String> timestampList = List.of("1009814400000", "1041350400000", "1072886400000", "1104508800000",
                "1136044800000", "1167580800000", "1199116800000", "1230739200000", "1262275200000",
                "1293811200000", "1325347200000", "1356969600000", "1388505600000", "1420041600000", "1451577600000",
                "1483200000000", "1514736000000", "1546272000000", "1577808000000", "1609430400000", "1640966400000");
        List<Integer> x = List.of(2002, 2003, 2004, 2005, 2006, 2007, 2008,2009,
                2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018,
                2019, 2020, 2021);
        int index = x.indexOf(year);
        List<Map> countByCountry = groupService.countAllGroupsByCountry(timestampList);
        Map result = new HashMap();
        List<Map> time_num = new ArrayList<>();
        time_num.add(countByCountry.get(index));
        result.put("data", time_num);
        Map meta = new HashMap();
        meta.put("msg","获取成功");
        meta.put("status", 200);
        result.put("meta", meta);
        return new Gson().toJson(result);
    }
    // 生物黑客组织每个国家创建年度总数量（累加包括之前创建的）
    @RequestMapping("/biohackCountryAdd")
    public String biohackCountryAdd(@RequestParam(value = "year", defaultValue = "2021")int year) throws FileNotFoundException{
        List<String> timestampList = List.of("1009814400000", "1041350400000", "1072886400000", "1104508800000",
                "1136044800000", "1167580800000", "1199116800000", "1230739200000", "1262275200000",
                "1293811200000", "1325347200000", "1356969600000", "1388505600000", "1420041600000", "1451577600000",
                "1483200000000", "1514736000000", "1546272000000", "1577808000000", "1609430400000", "1640966400000");
        List<Integer> x = List.of(2002, 2003, 2004, 2005, 2006, 2007, 2008,2009,
                2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018,
                2019, 2020, 2021);
        int index = x.indexOf(year);
        Map countByAdd = groupService.countAllGroupsByCountryByAdd(timestampList.get(0), timestampList.get(index + 1));
        System.out.println(timestampList.get(0));
        Map result = new HashMap();
        List<Map> time_num = new ArrayList<>();
        time_num.add(countByAdd);
        result.put("data", time_num);
        Map meta = new HashMap();
        meta.put("msg","获取成功");
        meta.put("status", 200);
        result.put("meta", meta);
        return new Gson().toJson(result);
    }
    // 生物黑客组织创建年度数量（美国当年新创建数量）
    @RequestMapping("/biohackGroupsUSA")
    public String biohackGroupsUSA() throws FileNotFoundException{
        List<String> timestampList = List.of("1009814400000", "1041350400000", "1072886400000", "1104508800000",
                "1136044800000", "1167580800000", "1199116800000", "1230739200000", "1262275200000",
                "1293811200000", "1325347200000", "1356969600000", "1388505600000", "1420041600000", "1451577600000",
                "1483200000000", "1514736000000", "1546272000000", "1577808000000", "1609430400000", "1640966400000");
        List<Integer> x = List.of(2002, 2003, 2004, 2005, 2006, 2007, 2008,2009,
                2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018,
                2019, 2020, 2021);
        Map result = new HashMap();
        List<String> countByYear = groupService.countAllGroupsInAmerica(timestampList);
        List<Map> time_num = new ArrayList<>();
        for(int i = 0; i < x.size(); i++){
            Map tmp = new HashMap();
            tmp.put("time", x.get(i));
            tmp.put("num", countByYear.get(i));
            time_num.add(tmp);
        }
        Map meta = new HashMap();
        meta.put("msg","获取成功");
        meta.put("status", 200);

        result.put("data",time_num);
        result.put("meta", meta);
        return new Gson().toJson(result);
    }

    // 生物黑客组织创建年度数量（美国累加）
    @RequestMapping("/biohackGroupsAddUSA")
    public String biohackGroupsUSAAdd() throws FileNotFoundException{
        List<String> timestampList = List.of("1009814400000", "1041350400000", "1072886400000", "1104508800000",
                "1136044800000", "1167580800000", "1199116800000", "1230739200000", "1262275200000",
                "1293811200000", "1325347200000", "1356969600000", "1388505600000", "1420041600000", "1451577600000",
                "1483200000000", "1514736000000", "1546272000000", "1577808000000", "1609430400000", "1640966400000");
        List<Integer> x = List.of(2002, 2003, 2004, 2005, 2006, 2007, 2008,2009,
                2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018,
                2019, 2020, 2021);
        Map result = new HashMap();
        List<String> countByYear = groupService.countAllGroupsInAmericaAdd(timestampList);
        List<Map> time_num = new ArrayList<>();
        for(int i = 0; i < x.size(); i++){
            Map tmp = new HashMap();
            tmp.put("time", x.get(i));
            tmp.put("num", countByYear.get(i));
            time_num.add(tmp);
        }
        Map meta = new HashMap();
        meta.put("msg","获取成功");
        meta.put("status", 200);

        result.put("data",time_num);
        result.put("meta", meta);
        return new Gson().toJson(result);
    }
}
