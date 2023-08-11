package com.hantorr.member.entity;

import com.hantorr.member.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;
import javax.swing.*;

import static lombok.AccessLevel.PROTECTED;


// @Setter Entity에서 setter는 지양해야 함
@Entity
@Getter
@AllArgsConstructor
@ToString
@Table(name = "member_table")
public class MemberEntity {
    // Entity == DB 테이블 객체라고 생각하면 됨

    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true) //unique 제약조건 추가
    private String memberEmail;

    @Column
    private String memberName;

    @Column
    private String memberPw;
    // 별도의 크기 지정 없다면 255 지정

    private MemberEntity() {
    }

    public MemberEntity(MemberEntityBuilder builder) {
        this.id = builder.id;
        this.memberEmail = builder.memberEmail;
        this.memberName = builder.memberName;
        this.memberPw = builder.memberPw;
    }


    public static class MemberEntityBuilder {

        private Long id;
        private String memberEmail;
        private String memberName;
        private String memberPw;

        //Builder생성자

        public MemberEntityBuilder(String memberEmail) {
            this.memberEmail = memberEmail;
        }

        public MemberEntityBuilder memberName(String memberName) {
            this.memberName = memberName;
            return this;
        }

        public MemberEntityBuilder memberPw(String memberPw) {
            this.memberPw = memberPw;
            return this;
        }

        public MemberEntityBuilder memberId(Long id) {
            this.id = id;
            return this;
        }


        public MemberEntity build() {
            return new MemberEntity(this);
        }

    }


}
