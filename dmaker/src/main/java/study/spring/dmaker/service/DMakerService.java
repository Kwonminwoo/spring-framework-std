package study.spring.dmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.dmaker.entity.Developer;
import study.spring.dmaker.repository.DeveloperRepository;
import study.spring.dmaker.type.DeveloperLevel;
import study.spring.dmaker.type.DeveloperSkillType;

@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;

    @Transactional
    public void createDeveloper(){
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYears(2)
                .name("Olaf")
                .age(5)
                .build();

        developerRepository.save(developer);
    }
}
