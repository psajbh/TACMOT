package com.jhart.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class RemoveElementsFromArrayList {
	
	//https://www.youtube.com/watch?v=K2_3rrcZVgg&feature=youtu.be

	@Test(expected= ConcurrentModificationException.class)
	public void removeElements() throws Exception{
		List<String> colors = new ArrayList<>(Arrays.asList("red","yellow", "blue", "black","grey"));
		
		for(String color : colors) {
			if (color.contains("ll")){
				colors.remove(color);
			}
		}
		
	}
	
	@Ignore
	public void testRemoveElementsTwo() throws Exception{
		List<String> colors = new ArrayList<>(Arrays.asList("red","yellow", "blue", "black","grey"));
		
		Iterator<String> iterator  = colors.iterator();
		while(iterator.hasNext()) {
			String color = iterator.next();
			
			if (color.contains("l")){
				colors.remove(color);
			}
		}
		
		
		
		
		
	}

}
