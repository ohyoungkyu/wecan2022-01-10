package com.oyk.product.community.service;

import com.oyk.product.community.dao.MemberRepository;
import com.oyk.product.community.domain.Member;
import com.oyk.product.community.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmMemberService {

    private final MemberRepository memberRepository;

    public List<MemberDTO> getMemberList(){

        List<MemberDTO> memberDTOList = new ArrayList<>();

        List<Member> memberList = memberRepository.findAll();

        for( Member member : memberList) {
            MemberDTO memberDTO = new MemberDTO(member);
            memberDTOList.add(memberDTO);
        }

        return memberDTOList;

    }

}
