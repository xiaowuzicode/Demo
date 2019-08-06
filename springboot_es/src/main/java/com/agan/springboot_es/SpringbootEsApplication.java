package com.agan.springboot_es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;

@Slf4j
@SpringBootApplication
public class SpringbootEsApplication {

	public static void main(String[] args) {

		StopWatch watch = new StopWatch();
		watch.start();
		ApplicationContext context = SpringApplication.run(SpringbootEsApplication.class, args);
		Environment environment = context.getBean(Environment.class);
		String applicationName = environment.getProperty("spring.application.name");
		String port = environment.getProperty("server.port");
		watch.stop();
		log.info("{} 启动完毕，端口号: {},times={}s,访问:{}", applicationName,port, watch.getTotalTimeSeconds(),"http://localhost:"+port);

	}

}
