package com.zzq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.zzq.mapper")
@SpringBootApplication
public class FlowableSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowableSampleApplication.class, args);
	}

}
