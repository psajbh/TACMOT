package com.jhart.bootstrap;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jhart.domain.document.Todo;
import com.jhart.repo.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final TodoRepository todoRepository;
    
    public ApplicationStartup(TodoRepository todoRepository) {
    	this.todoRepository = todoRepository;
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
    	
    	//Todo todo1 = new Todo("Pay car insurance");
    	Todo todo1 = new Todo("  ");
    	todo1.setCompleted(true);
    	todoRepository.save(todo1);
    	log.debug("seedData: added todo1: " + todo1.getName());
    	
    	Todo todo2 = new Todo("Mow the lawn");
    	todo2.setCompleted(false);
    	todoRepository.save(todo2);
    	log.debug("seedData: added todo2: " + todo2.getName());
    	
    	Todo todo3 = new Todo("Pay car insurance");
    	todo3.setCompleted(true);
    	todoRepository.save(todo3);
    	log.debug("seedData: added todo3: " + todo3.getName());

    }
    	
}
