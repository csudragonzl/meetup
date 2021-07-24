package edu.tjubd.meetup.mapper;

public interface MemberMapper {
    int countJoinedMembers(String startTimestamp, String endTimestamp);

    int countJoinedMembersByTopic(String startTimestamp, String endTimestamp, String groupId);

    int countJoinedMembersByMonth2021(String startTimestamp, String endTimestamp, String countryName);


}
