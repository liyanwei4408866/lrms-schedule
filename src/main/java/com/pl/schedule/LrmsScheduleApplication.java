package com.pl.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import tk.mybatis.spring.annotation.MapperScan;

@Component
@Configuration
@EnableScheduling
@EnableAsync
@ComponentScan
@EnableAutoConfiguration
@MapperScan("com.pl.schedule.mapper")
public class LrmsScheduleApplication
{

	public static void main(String[] args) {
		SpringApplication.run(LrmsScheduleApplication.class, args);
	}
}
