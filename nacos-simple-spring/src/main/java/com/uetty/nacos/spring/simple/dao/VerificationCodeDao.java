package com.uetty.nacos.spring.simple.dao;

import com.uetty.nacos.spring.simple.entity.VerificationCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VerificationCodeDao {

    VerificationCode getById(Long id);

    boolean insert(VerificationCode verificationCode);

    boolean update(VerificationCode verificationCode);

    boolean delete(Long id);
}