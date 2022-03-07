package com.oyk.product.community.dto.member;

import com.oyk.product.community.domain.Member;
import com.oyk.product.community.dto.artcle.ArticleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MyPageDTO {

    private String loginId;

    private String nickname;

    private List<ArticleDTO> articles;

    public MyPageDTO(Member member, List<ArticleDTO> articles) {

        this.loginId = member.getLoginId();
        this.nickname = member.getNickname();
        this.articles = articles;

    }

}
