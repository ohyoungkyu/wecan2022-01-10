package com.oyk.product.community.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Article createArticle(String title, String body){
        Article article = new Article();

        article.title = title;
        article.body = body;

        return article;
    }
    // 연관관계 메소드
    public void setMember(Member member){
        this.member = member;
        member.getArticles().add(this);
    }
}
