package edu.tjubd.meetup.mapper;

public interface MemberMapper {
    int countJoinedMembers(String startTimestamp, String endTimestamp);

    int countJoinedMembersByTopic(String startTimestamp, String endTimestamp, String groupId);
}
