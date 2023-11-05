package com.example.moduleapi.excption;


import com.example.modulecommon.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private String returnCode;
    private String returnMessage;

    public CustomException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        returnCode = codeEnum.getCode();
        returnMessage = codeEnum.getMessage();
    }
}
