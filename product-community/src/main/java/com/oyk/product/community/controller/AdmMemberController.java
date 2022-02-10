package com.oyk.product.community.controller;

import com.oyk.product.community.dto.adm.AdmMemberDetailDTO;
import com.oyk.product.community.dto.member.MemberDTO;
import com.oyk.product.community.service.AdmMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdmMemberController {

    private final AdmMemberService admMemberService;

    @GetMapping("/members")
    public String showManageMember(Model model){

        List<MemberDTO> members = admMemberService.getMemberList();

        model.addAttribute("members", members);

        return "adm/member/main";
    }

    @GetMapping("/members/detail/{id}")
    public String showMemberDetail(@PathVariable(name= "id") Long id , Model model){

        AdmMemberDetailDTO memberDetail = admMemberService.getMemberDetail(id);

        model.addAttribute("member", memberDetail);

        return "adm/member/detail";

    }

}
