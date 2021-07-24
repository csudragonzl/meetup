package edu.tjubd.meetup.service;

import com.google.gson.JsonObject;

import java.util.List;

public interface MemberService {
    List<Integer> countAllMembers(List<String> timestampList);

    List<Integer> countMembersByTopic(List<String> timestampList, String topicName);

    JsonObject countMembersByMonth2021(Integer month);
}
