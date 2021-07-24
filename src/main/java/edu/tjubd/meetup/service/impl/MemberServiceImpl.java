package edu.tjubd.meetup.service.impl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.tjubd.meetup.mapper.MemberMapper;
import edu.tjubd.meetup.mapper.TopicMapper;
import edu.tjubd.meetup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Integer> countAllMembers(List<String> timestampList) {
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<timestampList.size() - 1;i++){
            int counts = memberMapper.countJoinedMembers(timestampList.get(i), timestampList.get(i + 1));
            result.add(counts);
        }
        return result;
    }

    @Override
    public List<Integer> countMembersByTopic(List<String> timestampList, String topicName) {
        List<Integer> result = new ArrayList<>();
        String groupIdString = topicMapper.getGroupIdList(topicName);
        groupIdString = groupIdString.substring(1, groupIdString.length() - 1);
        String[] groupIdList = groupIdString.split(",");
        for(int i=0;i<timestampList.size() - 1;i++) {
            int temp = 0;
            for(int j=0;j< groupIdList.length;j++) {
                int counts = memberMapper.countJoinedMembersByTopic(timestampList.get(i), timestampList.get(i + 1), groupIdList[j]);
                temp += counts;
            }
            result.add(temp);
        }
        return result;
    }

    @Override
    public JsonObject countMembersByMonth2021(Integer month){
        JsonObject result =new JsonObject();
        List<String> timestampList = List.of("1609430400000", "1612108800000", "1614528000000", "1617206400000",
                "1619798400000", "1622476800000", "1625068800000", "1627747200000",
                "1630425600000", "1633017600000", "1635696000000", "1638288000000", "1640966400000");
        List<String> countryList=List.of("us", "sg", "ca", "gb", "nl", "au", "jp", "dk", "fr", "in", "ch", "il", "es", "de", "br", "za", "ie", "hk", "kr", "ee", "ar", "", "mx", "tw", "ae", "th", "se", "my", "at", "vn", "pa", "hu", "nz", "tr", "ph", "eg", "cn", "ro", "kh", "id", "ru", "lu", "be", "gr", "pt", "bs", "mu", "it", "cl", "gt", "sa", "no", "lt", "vu", "ua", "pl", "fi", "pk", "si", "aq", "bd", "lk", "ke", "ec", "bh", "co", "cr", "sk", "do", "uz", "jo", "bm", "mt", "bg", "cy", "cz", "kw", "uy", "ug", "pg", "tt", "bb", "ni", "pe", "wf", "tz", "tj", "np", "rs", "lv", "qa", "ve", "ng", "hr", "ba", "iq", "kz", "gp", "ky", "et", "is", "lb", "bf", "mq", "ge", "ps", "om", "am", "sl", "mk", "tn", "py", "by", "sy", "mm", "gu", "re", "bo", "to", "lc", "jm", "ir", "ad", "gh", "az", "mo", "la", "fj", "hn", "nc", "cw", "dz", "cd", "mn", "an", "sd", "ma", "ci", "al", "im", "ht", "ye", "md", "mg", "mc", "bz", "mv", "sv", "gm", "ne", "zw", "ly", "ao", "af", "li", "tg", "vg", "va", "rw", "gn", "ga", "sn", "gi", "so", "bj", "me", "bw", "tv", "pf", "cu", "mz", "gy", "cm", "xk", "kg", "zm", "mw", "sc", "lr", "tc", "pr", "eu", "tm");
        JsonArray list=new JsonArray();
        JsonObject map =new JsonObject();
        for(int i=0;i<countryList.size()-1;i++){
            int count = memberMapper.countJoinedMembersByMonth2021(timestampList.get(month), timestampList.get(month+1),countryList.get(i) );
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
