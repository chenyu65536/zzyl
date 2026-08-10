package com.zzyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 智慧养老项目启动类
 * @author zzyl
 */
@SpringBootApplication
@EnableScheduling
public class ZzylApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzylApplication.class, args);
    }

    /**
     * Activiti 的 springAsyncExecutor 注入 TaskExecutor 时，
     * 容器中存在 taskExecutor 和 taskScheduler 两个候选，导致 NoUniqueBeanDefinitionException。
     * 显式定义一个 @Primary TaskExecutor 消除歧义。
     */
    @Bean
    @Primary
    public TaskExecutor applicationTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("app-exec-");
        executor.initialize();
        return executor;
    }
}
