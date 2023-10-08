package com.std.springbootstd.logic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    @Test
    void given_List_whenExecuting_ThenReturnSortedList(){
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();

        List<Integer> actual = bubbleSort.sort(List.of(3, 2, 4, 5, 1));

        Assertions.assertThat(List.of(1, 2, 3, 4, 5)).isEqualTo(actual);
    }


}