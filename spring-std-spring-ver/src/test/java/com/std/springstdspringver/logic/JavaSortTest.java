package com.std.springstdspringver.logic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class JavaSortTest {
    @Test
    void given_List_whenExecuting_ThenReturnSortedList(){
        JavaSort<Integer> javaSort = new JavaSort<>();
        List<Integer> actual = javaSort.sort(List.of(3, 2, 4, 5, 1));

        Assertions.assertThat(actual).isEqualTo(List.of(1, 2, 3, 4, 5));
    }

}