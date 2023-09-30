package study.spring.dmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.dmaker.dto.CreateDeveloper;
import study.spring.dmaker.dto.DeveloperDto;
import study.spring.dmaker.dto.EditDeveloper;
import study.spring.dmaker.entity.Developer;
import study.spring.dmaker.exception.DMakerErrorCode;
import study.spring.dmaker.exception.DMakerException;
import study.spring.dmaker.repository.DeveloperRepository;
import study.spring.dmaker.type.DeveloperLevel;
import study.spring.dmaker.type.DeveloperSkillType;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
        validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYears());

        Optional<Developer> developer = developerRepository.findByMemberId(request.getMemberId());
        if(developer.isPresent()){
            throw new DMakerException(DMakerErrorCode.DUPLICATED_MEMBER_ID);
        }
    }

    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAll()
                .stream().map(dev -> DeveloperDto.fromEntity(dev))
                .collect(Collectors.toList());
    }

    public DeveloperDto getDeveloper(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDto::fromEntity)
                .orElseThrow(() -> new DMakerException(DMakerErrorCode.NO_DEVELOPER));
    }

    public DeveloperDto editDeveloper(String memberId, EditDeveloper.Request request) {
        validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYears());

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(DMakerErrorCode.NO_DEVELOPER));

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return DeveloperDto.fromEntity(developer)
    }

//    private void validateEditDeveloperRequest(EditDeveloper.Request request,String memberId){
//        validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYears());
//    }

    private void validateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYears){
        if(developerLevel == DeveloperLevel.SENIOR && experienceYears < 10){
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.JUNGNIOR
                && (experienceYears < 4 || experienceYears > 10)) {
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if(developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4){
            throw new DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

    }
}
