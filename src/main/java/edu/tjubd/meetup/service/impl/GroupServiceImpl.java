package edu.tjubd.meetup.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.tjubd.meetup.mapper.GroupMapper;
import edu.tjubd.meetup.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<String> countAllGroups(List<String> timestampList) {
        List<String> result = new ArrayList<>();
        for(int i=0;i<timestampList.size();i++){
            int counts = groupMapper.countJoinedGroups(timestampList.get(i));
            result.add(counts+"");
        }
        return result;
    }

    @Override
    public JsonObject countGroupByYear(Integer year) throws ParseException {
        String cur_year=year.toString()+"-01-01";
        String year1,year2;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(cur_year);
        long ts1 = date1.getTime();
        year1 = String.valueOf(ts1);

        Integer y2=year+1;
        String next_year=y2.toString()+"-01-01";
        Date date2 = simpleDateFormat.parse(next_year);
        long ts2 = date2.getTime();
        year2 = String.valueOf(ts2);

        JsonObject result =new JsonObject();
        List<String> countryList=List.of("us", "sg", "ca", "gb", "nl", "au", "jp", "dk", "fr", "in", "ch", "il", "es", "de", "br", "za", "ie", "hk", "kr", "ee", "ar", "", "mx", "tw", "ae", "th", "se", "my", "at", "vn", "pa", "hu", "nz", "tr", "ph", "eg", "cn", "ro", "kh", "id", "ru", "lu", "be", "gr", "pt", "bs", "mu", "it", "cl", "gt", "sa", "no", "lt", "vu", "ua", "pl", "fi", "pk", "si", "aq", "bd", "lk", "ke", "ec", "bh", "co", "cr", "sk", "do", "uz", "jo", "bm", "mt", "bg", "cy", "cz", "kw", "uy", "ug", "pg", "tt", "bb", "ni", "pe", "wf", "tz", "tj", "np", "rs", "lv", "qa", "ve", "ng", "hr", "ba", "iq", "kz", "gp", "ky", "et", "is", "lb", "bf", "mq", "ge", "ps", "om", "am", "sl", "mk", "tn", "py", "by", "sy", "mm", "gu", "re", "bo", "to", "lc", "jm", "ir", "ad", "gh", "az", "mo", "la", "fj", "hn", "nc", "cw", "dz", "cd", "mn", "an", "sd", "ma", "ci", "al", "im", "ht", "ye", "md", "mg", "mc", "bz", "mv", "sv", "gm", "ne", "zw", "ly", "ao", "af", "li", "tg", "vg", "va", "rw", "gn", "ga", "sn", "gi", "so", "bj", "me", "bw", "tv", "pf", "cu", "mz", "gy", "cm", "xk", "kg", "zm", "mw", "sc", "lr", "tc", "pr", "eu", "tm");
        //List<String> countryList=List.of("India","Canada","USA","Spain","Japan","Peru","U.A.E.","Ireland","Portugal","Romania");
        JsonArray list=new JsonArray();
        JsonObject map =new JsonObject();
        for(int i=0;i<countryList.size()-1;i++){
            int count = groupMapper.countJoinedGroupsByYear((year1+"000"), (year2+"000"),countryList.get(i) );
            map.addProperty(countryList.get(i),count);
        }
        list.add(map);
        JsonObject msg =new JsonObject();
        msg.addProperty("msg","获取成功");
        msg.addProperty("status",200);
        result.add("data",list);
        result.add("meta",msg);

        return result;
    }

}
