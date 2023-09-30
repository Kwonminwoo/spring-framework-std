package study.spring.dmaker.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import study.spring.dmaker.entity.Developer;
import study.spring.dmaker.type.DeveloperLevel;
import study.spring.dmaker.type.DeveloperSkillType;

public class EditDeveloper {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotNull
        private DeveloperLevel developerLevel;
        @NotNull
        private DeveloperSkillType developerSkillType;
        @NotNull
        @Min(0) @Max(20)
        private Integer experienceYears;
    }
}
