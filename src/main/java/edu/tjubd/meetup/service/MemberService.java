package edu.tjubd.meetup.service;

import java.util.List;

public interface MemberService {
    List<Integer> countAllMembers(List<String> timestampList);

    List<Integer> countMembersByTopic(List<String> timestampList, String topicName);
}
