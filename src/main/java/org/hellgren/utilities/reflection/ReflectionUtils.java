package org.hellgren.utilities.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reflection in Java is a powerful feature that allows a program to inspect and manipulate the structure and behavior of
 * classes, interfaces, fields, and methods at runtime. Through reflection, a Java program can perform operations like:
 *
 * Inspecting Classes and Interfaces: Retrieve information about classes, such as their constructors, methods, fields,
 * and annotations.
 * Instantiating Objects: Create instances of classes dynamically.
 * Accessing and Modifying Fields: Get and set the values of fields, even if they are private.
 */

public class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static <T,V> List<V> getListOfField(List<T> list, String fieldName) throws NoSuchFieldException {

        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        Field field = list.get(0).getClass().getDeclaredField(fieldName);
        return list.stream().map(e -> {
            try {
                return (V) field.get(e);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            return null;

        }).collect(Collectors.toList());
    }



}
