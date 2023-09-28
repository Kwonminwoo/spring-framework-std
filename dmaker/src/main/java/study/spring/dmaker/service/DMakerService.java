package study.spring.dmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.dmaker.dto.CreateDeveloper;
import study.spring.dmaker.entity.Developer;
import study.spring.dmaker.exception.DMakerErrorCode;
import study.spring.dmaker.exception.DMakerException;
import study.spring.dmaker.repository.DeveloperRepository;
import study.spring.dmaker.type.DeveloperLevel;
import study.spring.dmaker.type.DeveloperSkillType;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request){
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .name(request.getName())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request){
        Integer requestExperienceYears = request.getExperienceYears();
        DeveloperLevel developerLevel = request.getDeveloperLevel();
        if(developerLevel == DeveloperLevel.SENIOR && requestExperienceYears < 10){
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.JUNGNIOR
                && (requestExperienceYears < 4 || requestExperienceYears > 10)) {
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if(developerLevel == DeveloperLevel.JUNIOR && requestExperienceYears > 4){
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        Optional<Developer> developer = developerRepository.findByMemberId(request.getMemberId());
        if(developer.isPresent()){
            throw new DMakerException(DMakerErrorCode.DUPLICATED_MEMBER_ID);
        }
    }
}
