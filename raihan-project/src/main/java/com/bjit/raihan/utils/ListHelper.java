package com.bjit.raihan.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListHelper {

    public static <T> List<T> subList(List<T> list, Integer... indexes) throws IndexOutOfBoundsException {
        return Arrays.stream(indexes)
                .map(list::get)
                .collect(Collectors.toList());
    }
}
