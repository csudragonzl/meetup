package edu.tjubd.meetup.service;
import java.util.List;
import java.util.Map;

public interface GroupService {
    // 生物黑客组织创建年度数量
    List<String> countAllGroups(List<String> timestampList);
    // 生物黑客组织每个国家创建年度数量
    List<Map> countAllGroupsByCountry(List<String> timestampList);
    // 生物黑客组织创建年度数量（累加）
    Map countAllGroupsByCountryByAdd(String starttimestamp, String endtimestamp);

    // 生物黑客组织创建年度数量（美国当年新创建数量）
    List<String> countAllGroupsInAmerica(List<String> timestampList);

    // 生物黑客组织创建年度数量（美国当年新创建数量）
    List<String> countAllGroupsInAmericaAdd(List<String> timestampList);
}
