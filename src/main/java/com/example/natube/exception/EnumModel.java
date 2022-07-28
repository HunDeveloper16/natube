package com.example.natube.exception;

public interface EnumModel { // 에러코드뿐 아니라 공통적인 interface를 가지고 Enum을 관리하기 위해서 생성

    String getKey();
    String getValue();

}
