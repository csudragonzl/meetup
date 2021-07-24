package edu.tjubd.meetup.service;

import java.util.Map;
import java.util.List;

public interface GroupService {
    List<Integer> countEventsNum(List<String> timestampList,Boolean isUS);
    List<Map> countEventNum();
}
