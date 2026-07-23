package com.zzyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling   // 启用 Spring 定时任务：OrderJob/ContractJob 等仅依赖 @Scheduled，此前因缺此注解从未执行
public class ZzylApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzylApplication.class, args);
	}
}
