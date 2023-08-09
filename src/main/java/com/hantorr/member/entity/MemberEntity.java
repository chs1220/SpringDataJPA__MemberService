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

    private MemberEntity(Builder builder){
        this.id = null;
        this.memberEmail = builder.memberEmail;
        this.memberName = builder.memberName;
        this.memberPw = builder.memberPw;
        System.out.println("MemberEntity() : "+this.toString());
    }


    public static class Builder{

        private Long id;

        private String memberEmail;

        private String memberName;

        private String memberPw;

        //Builder생성자

        public Builder(String memberEmail) {
            this.id = null;
            this.memberEmail = memberEmail;
            System.out.println("Builder : "+ this.memberEmail);
        }

        public Builder memberName(String memberEmail) {
            this.memberEmail = memberName;
            return this;
        }

        public Builder memberPw(String memberPw) {
            this.memberEmail = memberPw;
            return this;
        }



        public MemberEntity build(){
            System.out.println("build() : "+ this.memberEmail);
            return new MemberEntity(this);
        }

    }


}
