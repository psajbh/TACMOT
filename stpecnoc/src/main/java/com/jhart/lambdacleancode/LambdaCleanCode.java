package com.jhart.lambdacleancode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

//https://dzone.com/articles/lambdas-and-clean-code?edition=327501&utm_source=Daily%20Digest&utm_medium=email&utm_campaign=Daily%20Digest%202017-09-28
public class LambdaCleanCode {
    
    private List<Person> persons;
    
    public static void main(String[] args) {
        LambdaCleanCode lcd = new LambdaCleanCode();
        lcd.execute();
    }
    
    public void execute() {
        persons = getPersons();
        List<String> maleNames = persons.stream().filter(p -> {
            if (p.getGender() == Gender.MALE) {
                return true;
            }
            return false;
        }).map(p -> p.getName())
        .collect(Collectors.toList());

        
        System.out.println(maleNames);
        
        
    }
    
    private List<Person> getPersons(){
        List<Person> persons = new ArrayList<>();
        Person p = new Person("Joe", Gender.MALE, LocalDate.now());
        persons.add(p);
        p = new Person("John", Gender.MALE, LocalDate.now());
        persons.add(p);
        p = new Person("Julie", Gender.FEMALE, LocalDate.now());
        persons.add(p);
        return persons;
    }
    
}


@Data
class Person {
    
    private String name;
    private String gender;
    private LocalDate birthdate;

    public Person(String name, String gender, LocalDate birthdate) {
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
    }
}

class Gender{
    public static String MALE = "M";
    public static String FEMALE = "F";
}