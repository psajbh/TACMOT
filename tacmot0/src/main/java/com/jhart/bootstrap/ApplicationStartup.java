package com.jhart.bootstrap;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

//import org.apache.maven.model.Model;
//import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
//import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jhart.domain.Todo;
import com.jhart.domain.User;
//import com.jhart.util.BuildModel;
import com.jhart.repo.task.TodoRepository;
import com.jhart.repo.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    
    public ApplicationStartup(TodoRepository todoRepository, UserRepository userRepository) {
    	this.todoRepository = todoRepository;
    	this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    	log.info("onApplicationEvent: start" );
    	Long userCount = userRepository.count();
    	if (userCount == 0 ) {
    		seedData();
    	}
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
    	user1.setLdapId("nancy001");
    	userRepository.save(user1);
    	
    	User user2 = new User();
    	user2.setName("John");
    	user2.setFirstName("John");
    	user2.setLastName("Hart");
    	user2.setPhone("239-322-7329");
    	user2.setEmail("jhart.naples@gmail.com");
    	user2.setLdapId("john001");
    	userRepository.save(user2);
    	
    	Todo todo1 = new Todo();
    	todo1.setTaskName("Go to work");
    	todo1.setUser(user1);
    	todoRepository.save(todo1);
    	log.debug("seedData: added todo1: " + todo1.getTaskName());
    	
    	Todo todo2 = new Todo();
    	todo2.setTaskName("Mow the lawn");
    	todo2.setUser(user2);
    	todo2.setComplete(false);
    	todoRepository.save(todo2);
    	log.debug("seedData: added todo2: " + todo2.getTaskName());
    	
    	Todo todo3 = new Todo();
    	todo3.setTaskName("Pay car insurance");
    	todo3.setComplete(true);
    	todo3.setCompleteDate(new Date());
    	todo3.setUser(user1);
    	todoRepository.save(todo3);
    	log.debug("seedData: added todo3: " + todo3.getTaskName());

    }
    	
}
