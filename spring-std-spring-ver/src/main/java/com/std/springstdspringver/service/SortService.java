package com.std.springstdspringver.service;

import com.std.springstdspringver.logic.Sort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortService {
    private final Sort<String> sort;

    public SortService(@Qualifier(value = "bubbleSort") Sort<String> sort) {
        this.sort = sort;
    }

    public List<String> doSort(List<String> list){
        return sort.sort(list);
    }
}
