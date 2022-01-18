package com.oyk.product.community.service;

import com.oyk.product.community.dao.ArticleRepository;
import com.oyk.product.community.domain.Article;
import com.oyk.product.community.domain.Member;
import com.oyk.product.community.dto.artcle.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void save(ArticleSaveForm articleSaveForm, Member member){

        Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );

    }

}
