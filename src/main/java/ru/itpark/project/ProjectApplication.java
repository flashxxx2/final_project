package ru.itpark.project;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

//    @PostConstruct
//    public void init() throws SQLException {
//        Server.createTcpServer().start();
//    }
        @PostConstruct
    public void init(){
            TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.ofOffset("", ZoneOffset.ofHours(3))));
        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
    }
}

