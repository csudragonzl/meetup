package edu.tjubd.meetup.service.impl;

import edu.tjubd.meetup.mapper.MemberMapper;
import edu.tjubd.meetup.mapper.TopicMapper;
import edu.tjubd.meetup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
