package com.jhart.func;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class OneClass implements Function<Long, Long>, Predicate<Object>, Supplier<Integer> {

    public static void main(String args[]) {}

    @Override
    public Integer get() {
        return new Integer((int) (Math.random() * 1000D));
    }

    @Override // Function
    public Long apply(Long aLong) {
        return aLong + 3;
    }

    @Override // Predicate
    public boolean test(Object o) {

        if (null == o) {
            return false;
        }

        if (o instanceof List<?>) {
            return analayizeList((List<String>) o);
        }
        
        if (o instanceof Integer) {
            return true;
        }

        return false;

    }

    public boolean analayizeList(List<String> names) {
        boolean x = false;
        Predicate<String> p = (s) -> s.startsWith("G");

        for (String s : names) {
            if (this.test(s))
                System.out.println(s);
            x = true;
        }
        return x;
    }

}
