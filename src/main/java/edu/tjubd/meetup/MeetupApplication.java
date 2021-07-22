package edu.tjubd.meetup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "edu.tjubd.meetup.mapper")
public class MeetupApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetupApplication.class, args);
    }

}
