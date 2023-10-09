package study.spring.dmaker.dto;

import lombok.*;
import study.spring.dmaker.entity.Developer;
import study.spring.dmaker.exception.DMakerErrorCode;
import study.spring.dmaker.type.DeveloperLevel;
import study.spring.dmaker.type.DeveloperSkillType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperValidationDto {
    private DMakerErrorCode errorCode;
    private String errorMessage;
}
