package com.jhart.func;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jhart.func.OneClass;

public class OneClassTest {
    
    
    Function<Long, Long> f;
    Predicate<Object> p;
    Supplier<Integer> s;
    
    private static List<String> names = null;
    
    @BeforeClass
    public static void setupNames() {
        names = Arrays.asList("Geek", "GeeksQuiz", "g1", "QA", "Geek2");
    }

    @Before
    public void setUp() throws Exception {
        f = new OneClass();
        p = new OneClass();
        s = new OneClass();
    }
    
    @After
    public void testCleanup() {
        f = null;
        p = null;
        s = null;
    }

    @Test
    public void testApply() throws Exception{
        Long result = f.apply(4L);
        assertEquals((long)result, (long)7L);
    }
    
    //create anonymous class with lambda expression
    @Test
    public void testApplyWithLamba() throws Exception{
        Function<Long, Long> adderLambda = (value) -> value + 3;
        Long resultLambda = adderLambda.apply((long) 8);
        assertEquals((long)resultLambda, (long)11L);
    }
    
    @Test
    public void testPredicateTrue() throws Exception{
        p = new OneClass();
        assertTrue(p.test(0));
    }
    
    @Test
    public void testPredicateFalse() throws Exception{
        Predicate<Object> adder = new OneClass();
        Assert.assertFalse(adder.test(null));
    }
    
    @Test
    public void testPredicateWithLambdaTrue() throws Exception{
//        Predicate<Object> adder = new OneClass();
//        Object o = (Object) new String("abc");
//        assertTrue(adder.test(o));
        
        Predicate<Boolean> predicate = (a) -> a != null;
//        System.out.println();
    }
    
    @Test
    public void testPredicateWithList() throws Exception{
        List<String> names = Arrays.asList("Geek", "GeeksQuiz", "g1", "QA", "Geek2");
        Predicate<Object> adder = new OneClass();
        Object o = (Object)names;
        assertTrue(adder.test(o));
    }

    @Test
    public void testPredicateWithList2() throws Exception{
        List<String> names = Arrays.asList("Test", "Errors");
        Predicate<Object> adder = new OneClass();
        Object o = (Object)names;
        assertTrue(adder.test(o));
    }

    @Test
    public void testSupplier() throws Exception{
        s = new OneClass();
        Integer i = s.get();
        assertTrue(i > 0);
    }
    
    @Test
    public void testFunctionComposition() throws Exception{
        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX   = (text) -> text.endsWith("x");

        Predicate<String> startsWithAAndEndsWithX =
                (text) -> startsWithA.test(text) && endsWithX.test(text);

        String  input  = "A hardworking person must relax";
        boolean result = startsWithAAndEndsWithX.test(input);
        System.out.println(result);   
    }

}
