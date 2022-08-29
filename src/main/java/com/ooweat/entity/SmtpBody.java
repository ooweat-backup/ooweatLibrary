package com.ooweat.entity;

import lombok.Data;

@Data
public class SmtpBody {
	String sender;		//발신자
	String nickName;	//별명
	String password;	//Gmail에서 2차 인증으로 암호화된 비밀번호
	String receiver;	//수신자
	String title;		//제목
	String content;		//내용
}
