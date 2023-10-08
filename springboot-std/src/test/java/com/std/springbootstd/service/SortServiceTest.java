package com.std.springbootstd.service;

import com.std.springbootstd.logic.JavaSort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortServiceTest {

    private SortService sortService = new SortService(new JavaSort<>());

    @Test
    void test(){
        List<String> sortedList = sortService.doSort(List.of("3", "2", "1"));

        Assertions.assertThat(sortedList).isEqualTo(List.of("1", "2", "3"));
    }

}