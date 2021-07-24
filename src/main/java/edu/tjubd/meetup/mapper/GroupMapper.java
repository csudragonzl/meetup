package edu.tjubd.meetup.mapper;

import java.util.List;
import java.util.Map;

public interface GroupMapper {
    List<Map> getEventNumByYear();
    int getUSEventNum(String startTimestamp, String endTimestamp);
    int getAllEventNum(String startTimestamp,String endTimestamp);
}
