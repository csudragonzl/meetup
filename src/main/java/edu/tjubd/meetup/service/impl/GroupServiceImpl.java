package edu.tjubd.meetup.service.impl;

import edu.tjubd.meetup.mapper.GroupMapper;
import edu.tjubd.meetup.mapper.MemberMapper;
import edu.tjubd.meetup.mapper.TopicMapper;
import edu.tjubd.meetup.service.GroupService;
import edu.tjubd.meetup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<String> countAllGroups(List<String> timestampList){
        List<String> result = new ArrayList<>();
        for(int i = 0; i < timestampList.size() - 1; i++){
            int counts = groupMapper.countGroupByYear(timestampList.get(i), timestampList.get(i + 1));
            result.add(String.valueOf(counts));
        }
        return result;
    }

    @Override
    public List<Map> countAllGroupsByCountry(List<String> timestampList) {
        List<Map> result = new ArrayList<>();
        for(int i = 0; i < timestampList.size() - 1; i++){
            List<Map> counts = groupMapper.countGroupByCountry(timestampList.get(i), timestampList.get(i + 1));
            Map tmp = new HashMap();
            //遍历每个结果

            for(int j= 0; j < counts.size();j++){
                tmp.put(counts.get(j).get("country"), counts.get(j).get("num"));
            }
            result.add(tmp);
        }
        return result;
    }

    @Override
    public Map countAllGroupsByCountryByAdd(String starttimestamp, String endtimestamp) {
        Map result = new HashMap();
        List<Map> counts = groupMapper.countGroupByCountry(starttimestamp, endtimestamp);
        //Map tmp = new HashMap();
        for(int j= 0; j < counts.size();j++) {
            result.put(counts.get(j).get("country"), counts.get(j).get("num"));
        }
        return result;
    }

    @Override
    public List<String> countAllGroupsInAmerica(List<String> timestampList) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < timestampList.size() - 1; i++){
            System.out.println(timestampList.get(i));System.out.println(timestampList.get(i + 1));
            groupMapper.countGroupByYearInAmerica(timestampList.get(i), timestampList.get(i + 1));
            int counts = groupMapper.countGroupByYearInAmerica(timestampList.get(i), timestampList.get(i + 1));
            //System.out.println(timestampList.get(i));
            result.add(String.valueOf(counts));
        }
        return result;
    }

    @Override
    public List<String> countAllGroupsInAmericaAdd(List<String> timestampList) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < timestampList.size() - 1; i++){
            System.out.println(timestampList.get(i));System.out.println(timestampList.get(i + 1));
            groupMapper.countGroupByYearInAmerica(timestampList.get(i), timestampList.get(i + 1));
            int counts = groupMapper.countGroupByYearInAmerica(timestampList.get(0), timestampList.get(i + 1));
            //System.out.println(timestampList.get(i));
            result.add(String.valueOf(counts));
        }
        return result;
    }
}
