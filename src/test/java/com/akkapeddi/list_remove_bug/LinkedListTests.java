package com.akkapeddi.list_remove_bug;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTests {
    private final List<String> linkedList = new LinkedList<>();

    @Before
    public void setup() {
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
    }

    // Passes
    @Test(expected = ConcurrentModificationException.class)
    public void remove_element_in_middle_of_list_throws_concurrent_mod_exception() {
        for(String each: linkedList) {
            System.out.println(each);
            if(each.equals("d")) {
                System.out.println("Removing " + each);
                linkedList.remove(each);
            }
        }
    }

    // Fails
    @Test(expected = ConcurrentModificationException.class)
    public void remove_element_at_end_of_list_incorrectly_allowed() {
        for(String each: linkedList) {
            System.out.println(each);
            if(each.equals("e")) {
                System.out.println("Removing " + each);
                linkedList.remove(each);
            }
        }

        System.out.println("\nState after:");
        linkedList.stream().forEach(System.out::println);
    }
}
