package study.spring.dmaker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.dmaker.dto.CreateDeveloper;
import study.spring.dmaker.service.DMakerService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService;

    @PostMapping("/create-developer")
    public List<String> createDeveloper(@Valid @RequestBody CreateDeveloper.Request request){
        log.info("request : {}", request);
        dMakerService.createDeveloper(request);
        return List.of("Olaf");
    }
}
