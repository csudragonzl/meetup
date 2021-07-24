package edu.tjubd.meetup.service.impl;

import edu.tjubd.meetup.mapper.GroupMapper;
import edu.tjubd.meetup.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Override
    public List<Integer> countEventsNum(List<String> timestampList,Boolean isUS){
        List<Integer> result = new ArrayList<>();
        if(!isUS){
                for(int i=0;i<timestampList.size()-1;i++){
                    int counts = groupMapper.getAllEventNum(timestampList.get(i),timestampList.get(i+1));
                    result.add((counts));
                }
        }
        else{

            for(int i=0;i<timestampList.size()-1;i++){
                int counts = groupMapper.getUSEventNum(timestampList.get(i),timestampList.get(i+1));
                result.add((counts));
            }
        }
        return result;
    }
    @Override
    public List<Map> countEventNum(){
        List<Map> result = new ArrayList<>();
        result = groupMapper.getEventNumByYear();
        return result;
    }


}
