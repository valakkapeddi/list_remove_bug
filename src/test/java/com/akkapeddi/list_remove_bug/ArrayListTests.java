package com.akkapeddi.list_remove_bug;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ArrayListTests {
    private final List<String> arrayList = new ArrayList<>();

    @Before
    public void setup() {
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        arrayList.add("f");
    }

    // Passes
    @Test(expected = ConcurrentModificationException.class)
    public void remove_element_in_middle_of_list_throws_concurrent_mod_exception() {
        for(String each: arrayList) {
            System.out.println(each);
            if(each.equals("d")) {
                System.out.println("Removing " + each);
                arrayList.remove(each);
            }
        }
    }

    // Fails
    @Test(expected = ConcurrentModificationException.class)
    public void remove_element_at_end_of_list_incorrectly_allowed() {
        for(String each: arrayList) {
            System.out.println(each);
            if(each.equals("e")) {
                System.out.println("Removing " + each);
                arrayList.remove(each);
            }
        }

        System.out.println("\nState after:");
        arrayList.stream().forEach(System.out::println);
    }
}
