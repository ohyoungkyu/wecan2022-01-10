package com.oyk.product.community.service;

import com.oyk.product.community.config.Role;
import com.oyk.product.community.dao.MemberRepository;
import com.oyk.product.community.dto.adm.MemberStateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdmService {

    private final MemberRepository memberRepository;

    public MemberStateDto getMemberStateDto(){

        return new MemberStateDto(
                memberRepository.count(),
                memberRepository.countTodayMember(),
                memberRepository.countByAuthorityLike(Role.ADMIN),
                memberRepository.countByAuthorityLike(Role.MEMBER)
        );

    }

}
