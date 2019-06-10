package com.jhart.newfeatures;

import org.junit.Test;

public class Java8Test {
    
    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }
    
    // with type declaration
    MathOperation addition = (int a, int b) -> a + b;

    // with out type declaration
    MathOperation subtraction = (a, b) -> a - b;

    // with return statement along with curly braces
    MathOperation multiplication = (int a, int b) -> {
        return a * b;
    };

    // without return statement and without curly braces
    MathOperation division = (int a, int b) -> a / b;
    
    public Java8Test() {}
    
    @Test
    public void testJava8Test() {
        Java8Test tester = new Java8Test();
        
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
        
        // without parenthesis
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // with parenthesis
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");
        
    }


    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
