package edu.tjubd.meetup.mapper;

public interface GroupMapper {
    int countJoinedGroups(String Timestamp);

    int countJoinedGroupsByYear(String startTimestamp, String endTimestamp, String countryName);
}
