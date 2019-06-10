package com.jhart.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TwoClass {
    
    private static Stream<String> stream = null;

    public static void main(String[] args) {
        List<String> items = new ArrayList<String>();
        items.add("one");
        items.add("two");
        items.add("three");
        stream = items.stream();
    }
    

}
