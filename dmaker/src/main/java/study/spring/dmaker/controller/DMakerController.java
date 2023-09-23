package study.spring.dmaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.dmaker.service.DMakerService;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService;

    @GetMapping("/create-developer")
    public List<String> createDeveloper(){
        dMakerService.createDeveloper();
        return List.of("Olaf");
    }
}
