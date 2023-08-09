package com.hantorr.member.repository;

import com.hantorr.member.dto.MemberDTO;
import com.hantorr.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // DB와 가장 밀접하게 연결하는 인터페이스 => 테이블 객체인 Entity로 보내주어야 함


}
