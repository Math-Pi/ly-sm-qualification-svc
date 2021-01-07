package com.ly.cloud.common.flyway;

import javax.annotation.Resource;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ly.zhxg.flyway.NHFlyway;

@Component
public class MyFlywayServletContextListener implements ApplicationListener<SpringApplicationEvent> {

	@Resource
	private Environment environment;
	
    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
    	if(event instanceof ApplicationReadyEvent ){//启动成功之后
    		ApplicationContext content = ((ApplicationReadyEvent)event).getApplicationContext();
        	String url=environment.getProperty("spring.datasource.url");
        	String username=environment.getProperty("spring.datasource.username");
        	String password=environment.getProperty("spring.datasource.password");
        	NHFlyway flyway = new NHFlyway(content,url, username, password,"VERSION_SM_QUALIFICATION",this.getClass().getClassLoader());
        	flyway.migrate();
        }
    }
}