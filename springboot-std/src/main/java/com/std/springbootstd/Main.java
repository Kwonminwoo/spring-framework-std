package com.std.springbootstd;

import com.std.springbootstd.logic.BubbleSort;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BubbleSort<String> sort = new BubbleSort<>();

        System.out.println(sort.sort(Arrays.asList(args)));
    }
}
