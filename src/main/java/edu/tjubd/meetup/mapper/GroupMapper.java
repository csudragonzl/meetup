package edu.tjubd.meetup.mapper;

import java.util.List;
import java.util.Map;

public interface GroupMapper {
    // 生物黑客组织创建年度数量
    int countGroupByYear(String startTimestamp, String endTimestamp);

    // 生物黑客组织每个国家创建年度数量
    List<Map> countGroupByCountry(String startTimestamp, String endTimestamp);

    // 生物黑客组织创建年度数量（美国当年新创建数量）
    int countGroupByYearInAmerica(String startTimestamp, String endTimestamp);
}
