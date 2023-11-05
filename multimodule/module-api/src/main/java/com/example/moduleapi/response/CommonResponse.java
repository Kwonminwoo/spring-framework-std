package com.example.moduleapi.response;


import com.example.modulecommon.enums.CodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {
    private String returnCode;
    private String returnMessage;
    private T info;


    public CommonResponse(CodeEnum codeEnum) {
        this.returnCode = codeEnum.getCode();
        this.returnMessage = codeEnum.getMessage();
    }

    public CommonResponse(T info) {
        this.info = info;
    }

    public CommonResponse(CodeEnum codeEnum, T info) {
        this.returnCode = codeEnum.getCode();
        this.returnMessage = codeEnum.getMessage();
        this.info = info;
    }
}
