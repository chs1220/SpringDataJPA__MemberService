package com.hantorr.member.service;

import com.hantorr.member.dto.MemberDTO;
import com.hantorr.member.entity.MemberEntity;
import com.hantorr.member.repository.MemberRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  // 스프링 빈 등록
@RequiredArgsConstructor //주입받는 생성자 메서드 생략
public class MemberService {

    private final MemberRepository memberRepository;


    private static MemberEntity toMemberEntity(MemberDTO memberDTO) {

        MemberEntity memberEntity = new MemberEntity.MemberEntityBuilder(memberDTO.getMemberEmail())
                .memberPw(memberDTO.getMemberPw())
                .memberName(memberDTO.getMemberName())
                .memberId(memberDTO.getId())
                .build();
        System.out.println("memberDTO = " + memberDTO);

        return memberEntity;
    }

    public void save(MemberDTO memberDTO) {
        // repository는 entity객체로 db와 데이터를 주고 받음. 따라서
        // 1. service단에서 DTO객체 데이터로 entity객체를 생성해주어야 함 ==> 빌더패턴 짠 후 toMemberEntity로 메서드 추출함
        // 2. 그 이후 repository 를 통해서 객체 저장

        memberRepository.save(toMemberEntity(memberDTO));
        // member.id 값에 0이 들어감

    }

    public MemberDTO login(MemberDTO memberDTO) {
        // 1. 이메일 DB에 있는지 조회
        // 2. 비밀번호 검증

        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        if (byMemberEmail.isPresent()) {
            // 회원 조회 성공
            MemberEntity memberEntity = byMemberEmail.get(); //옵셔널 벗기기

            if (memberEntity.getMemberPw().equals(memberEntity.getMemberPw())) {
                //비밀번호 일치
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);  // entity -> dto로 변환 후 리턴
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            // 회원조회 실패(없는 회원)
            return null;
        }

    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>(); //Entity를 DTO로 변화후 담아줄 리스트 선언

        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }

        return memberDTOList;

    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        if (memberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(memberEntity.get());
        } else return null;

    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> memberEntity = memberRepository.findByMemberEmail(myEmail);
        if (memberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(memberEntity.get());
        } else return null;

    }

    public void update(MemberDTO memberDTO) {
        // Repository.save() => pk값이 같은 데이터가 있으면 자동으로 insert가 아닌 update문을 날려줌
        memberRepository.save(toMemberEntity(memberDTO));

    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public String findByMemberEmail(String memberEmail) {
        Optional<MemberEntity> memberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (memberEntity.isPresent()){
            return null;
        }else return "ok";
    }
}
