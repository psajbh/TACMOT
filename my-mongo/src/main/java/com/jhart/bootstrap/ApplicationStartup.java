package com.jhart.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jhart.domain.Todo;
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
        seedData();
    }

    private void seedData() {
    	
    	Todo todo1 = todoRepository.save(new Todo("Pay car insurance"));
    	log.info("Created Todo:  " + todo1);
    	
    	Todo todo2 = todoRepository.save(new Todo("Quit job"));
    	log.info("Created Todo:  " + todo2);
    }
    	
}
