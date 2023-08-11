package com.hantorr.member.controller;

import com.hantorr.member.dto.MemberDTO;
import com.hantorr.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor // 주입받는 생성자의 메서드 생략가능
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            //로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            //로그인 실패
            return "login";
        }
    }


    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "list";

    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        //@PathVariable => 경로상의 값을 받아오는 어노테이션
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }


    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember",memberDTO);
        return "update";
    }


    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId(); //findById() 거쳐서 detail.html로
    }


    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/"; //findAll() 거쳐서 list.html로
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

    @PostMapping("/member/emailCheck")
    @ResponseBody
    public String emailCheck(@RequestParam("memberEmail") String memberEmail){
        String result = memberService.findByMemberEmail(memberEmail);
        return result; // null or "ok" 넘어옴
//        if (result != null){
//            return "사용할 수 있는 이메일입니다.";
//        }else return "사용할 수 없는 이메일입니다.";
    }

}
