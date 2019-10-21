package com.jhart.bootstrap;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.repo.TodoRepository;
import com.jhart.repo.UserRepository;
import com.jhart.util.BuildModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final BuildModel buildModel;
    
    public ApplicationStartup(TodoRepository todoRepository, UserRepository userRepository, BuildModel buildModel) {
    	this.todoRepository = todoRepository;
    	this.userRepository = userRepository;
    	this.buildModel = buildModel;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    	log.info("onApplicationEvent: start" );
        seedData();
        log.info("onApplicationEvent: complete" );
    }

    private void seedData() {
    	log.debug("seedData: start");
    	
    	User user1 = new User();
    	user1.setName("Nancy");
    	user1.setFirstName("Nancy");
    	user1.setLastName("Hart");
    	user1.setPhone("123-123-1234");
    	user1.setEmail("nbd52@yahoo.com");
    	userRepository.save(user1);
    	
    	User user2 = new User();
    	user1.setName("John");
    	user1.setFirstName("John");
    	user1.setLastName("Hart");
    	user1.setPhone("239-322-7329");
    	user1.setEmail("jhart.naples@gmail.com");
    	userRepository.save(user2);
    	
    	Todo todo1 = new Todo("  ");
    	todo1.setUser(user1);
    	todoRepository.save(todo1);
    	log.debug("seedData: added todo1: " + todo1.getTaskName());
    	
    	Todo todo2 = new Todo("Mow the lawn");
    	todo1.setUser(user2);
    	todo2.setComplete(false);
    	todoRepository.save(todo2);
    	log.debug("seedData: added todo2: " + todo2.getTaskName());
    	
    	Todo todo3 = new Todo("Pay car insurance");
    	todo3.setComplete(true);
    	todo3.setCompleteDate(new Date());
    	todo1.setUser(user1);
    	todoRepository.save(todo3);
    	log.debug("seedData: added todo3: " + todo3.getTaskName());

    }
    	
}
