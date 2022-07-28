package com.example.natube.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel{
    // COMMON
    ID_CHECK_CODE(400, "C001", "아이디는 3글자이상, 알파벳 대소문자와 숫자로만 구성해주세요"),
    PASSWORD_CHECK_CODE(400, "C002", "비밀번호는 최소4자 이상, 닉네임과 같은 값이 포함될수 없습니다"),
    PASSWORD_EQUAL_CODE(400, "C003", "패스워드가 다릅니다."),
    USERNAME_DUPLICATE_CODE(400, "C004", "중복된 회원ID가 존재합니다."),
    NICKNAME_DUPLICATE_CODE(400, "C005", "중복된 닉네임이 존재합니다."),

    FILE_UPLOAD_IO_EXCEPTION_CODE(400, "C006", "파일 업로드에 실패하였습니다."),
    USER_NOT_FOUND_CODE(400,"C007", "사용자를 찾을 수 없습니다."),
    POST_NOT_FOUND_CODE(400,"C008", "포스트를 찾을 수 없습니다.");


    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
    @Override
    public String getKey() {
        return this.code;
    }
    @Override
    public String getValue() {
        return this.message;
    }

}
