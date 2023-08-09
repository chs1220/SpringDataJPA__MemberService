package com.hantorr.member.dto;

import lombok.*;

@Getter   //롬복 라이브러리로 DTO 메서드들을 어노테이션으로 생략 가능
@Setter
@NoArgsConstructor //기본생성자
@AllArgsConstructor // 필드변수 전체를 매개변수로 하는 생성자
@ToString
public class MemberDTO {
    private long id;
    private String memberEmail;
    private String memberName;
    private String memberPw;
}
