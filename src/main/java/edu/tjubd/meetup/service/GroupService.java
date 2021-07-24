package edu.tjubd.meetup.service;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.List;

public interface GroupService {
    List<String> countAllGroups(List<String> timestampList);

    JsonObject countGroupByYear(Integer year) throws ParseException;
}
