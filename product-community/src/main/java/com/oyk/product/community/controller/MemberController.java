package com.oyk.product.community.controller;

import com.oyk.product.community.domain.Member;
import com.oyk.product.community.dto.member.CheckStatus;
import com.oyk.product.community.dto.member.MemberLoginForm;
import com.oyk.product.community.dto.member.MemberModifyForm;
import com.oyk.product.community.dto.member.MemberSaveForm;
import com.oyk.product.community.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/members/check/id")
    @ResponseBody
    public CheckStatus checkLoginId(@RequestParam String loginId){

        boolean isExists = memberService.isDupleLoginId(loginId);

        CheckStatus checkStatus = new CheckStatus(isExists);

        return checkStatus;

    }

    @RequestMapping("/members/check/nickname")
    @ResponseBody
    public CheckStatus checkNickname(@RequestParam String nickname){

        boolean isExists = memberService.isDupleNickname(nickname);

        CheckStatus checkStatus = new CheckStatus(isExists);

        return checkStatus;

    }

    @RequestMapping("/members/check/email")
    @ResponseBody
    public CheckStatus checkEmail(@RequestParam String email){

        boolean isExists = memberService.isDupleEmail(email);

        CheckStatus checkStatus = new CheckStatus(isExists);

        return checkStatus;

    }

    @DeleteMapping("/members")
    @RequestBody
    public boolean doDelete(@RequestBody String loginId, Principal principal){

        if( !loginId.equals(principal.getName()) ){
            return false;
        }

        memberService.deleteMember(loginId);

        return true;
    }

    @GetMapping("/members/join")
    public String showJoin(Model model) {

        model.addAttribute("memberSaveForm", new MemberSaveForm());
        return "usr/member/join";

    }

    @PostMapping("/members/join")
    public String doJoin(@Validated MemberSaveForm memberSaveForm, BindingResult bindingResult, Model model) {

        if ( bindingResult.hasErrors() ) {
            return "usr/member/join";
        }

        try {

            memberService.save(memberSaveForm);

        } catch ( Exception e ) {

            model.addAttribute("err_msg", e.getMessage());

            return "usr/member/join";

        }

        return "redirect:/";

    }

    @GetMapping("members/login")
    public String showLogin(Model model){

        model.addAttribute("memberLoginForm", new MemberLoginForm());

        return "usr/member/login";

    }

    @GetMapping("/members/modify/{id}")
    public String showModify(@PathVariable(name="id") Long id, Model model, Principal principal){

        Member findMember = memberService.findById(id);

        if( !findMember.getLoginId().equals(principal.getName()) ) {
            return "redirect:/";
        }

        model.addAttribute("memberModifyForm", new MemberModifyForm(
                findMember
        ));

        return "usr/member/modify";
    }

    @PostMapping("/members/modify/{id}")
    public String doModify(@PathVariable(name="id")Long id, @Validated MemberModifyForm memberModifyForm, BindingResult bindingResult , Principal principal, Model model){

        if(bindingResult.hasErrors()){
            return "usr/member/modify";
        }

        Member findMember = memberService.findById(id);

        if( !findMember.getLoginId().equals(principal.getName()) ){
            return "redirect:/";
        }

        try {

            memberService.modifyMember(memberModifyForm, principal.getName());
        }catch (Exception e){

            model.addAttribute("err_msg", e.getMessage());
            model.addAttribute("memberModifyForm", new MemberModifyForm(findMember));

            return "usr/member/modify";
        }
        return "redirect:/";
    }

    @GetMapping("/members/find/pw")
    public String showFindPw(){
        return "usr/member/findpw";
    }

}
