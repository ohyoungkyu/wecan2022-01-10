package com.oyk.product.community.service;

import com.oyk.product.community.config.Role;
import com.oyk.product.community.dao.MemberRepository;
import com.oyk.product.community.domain.Member;
import com.oyk.product.community.dto.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(username).get();
    }

    public void isDuplicateMember(String loginId, String nickname, String email){

        if(memberRepository.existsByLoginId(loginId)){

            throw new IllegalStateException("이미 존재하는 아이디 입니다");

        }else if(memberRepository.existsByNickname(nickname)){

            throw new IllegalStateException("이미 존재하는 아이디 입니다");

        }else if(memberRepository.existsByEmail(email)){

            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    @Transactional
    public void save(MemberSaveForm memberSaveForm) throws IllegalStateException{
        isDuplicateMember(
                memberSaveForm.getLoginId(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail()
        );

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Member member = Member.createMember(
                memberSaveForm.getLoginId(),
                bCryptPasswordEncoder.encode(memberSaveForm.getLoginPw()),
                memberSaveForm.getName(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail(),
                Role.MEMBER
        );

        memberRepository.save(member);
    }
}