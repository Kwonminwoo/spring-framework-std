package study.spring.dmaker.dto;

import lombok.*;
import study.spring.dmaker.entity.Developer;
import study.spring.dmaker.type.DeveloperLevel;
import study.spring.dmaker.type.DeveloperSkillType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDto {
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private String memberId;

    public static DeveloperDto fromEntity(Developer developer) {
        return DeveloperDto.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .memberId(developer.getMemberId())
                .build();
    }
}
