package com.std.springstdspringver;

import com.std.springstdspringver.config.Config;
import com.std.springstdspringver.service.SortService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        SortService sortService = context.getBean(SortService.class);

        System.out.println(sortService.doSort(Arrays.asList(args)));
    }
}
