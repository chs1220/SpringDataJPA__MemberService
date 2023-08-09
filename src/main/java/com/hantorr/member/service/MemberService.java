package com.hantorr.member.service;

import com.hantorr.member.dto.MemberDTO;
import com.hantorr.member.entity.MemberEntity;
import com.hantorr.member.repository.MemberRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service  // 스프링 빈 등록
@RequiredArgsConstructor //주입받는 생성자 메서드 생략
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // repository는 entity객체로 db와 데이터를 주고 받음
        // 따라서
        // 1. service단에서 DTO객체 데이터로 entity객체를 생성해주어야 함
        // 2. 그 이후 repository 를 통해서 객체 저장

        String pw = memberDTO.getMemberPw();
        String email = memberDTO.getMemberEmail();
        String name = memberDTO.getMemberName();

        //MemberEntity memberEntity = new Builder(email)
       // jpa에서 제공하는 save 메서드 ()
        //memberRepository.save(memberEntity);



    }
}
