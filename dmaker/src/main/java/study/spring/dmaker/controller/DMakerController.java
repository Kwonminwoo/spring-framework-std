package study.spring.dmaker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.spring.dmaker.dto.CreateDeveloper;
import study.spring.dmaker.dto.DeveloperDto;
import study.spring.dmaker.dto.EditDeveloper;
import study.spring.dmaker.service.DMakerService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DMakerController {
    private final DMakerService dMakerService;

    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDeveloper(@Valid @RequestBody CreateDeveloper.Request request) {
        log.info("request : {}", request);
        return dMakerService.createDeveloper(request);
    }

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        return dMakerService.getAllDevelopers();
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDto getDeveloper(@PathVariable String memberId) {
        return dMakerService.getDeveloper(memberId);
    }

    @PutMapping("/developer/{memberId}")
    public DeveloperDto editDeveloper(@PathVariable String memberId, @RequestBody EditDeveloper.Request request){
        return dMakerService.editDeveloper(memberId, request);
    }
}
