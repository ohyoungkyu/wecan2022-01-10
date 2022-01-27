package com.oyk.product.community.service;

import com.oyk.product.community.dao.ArticleRepository;
import com.oyk.product.community.domain.Article;
import com.oyk.product.community.domain.Board;
import com.oyk.product.community.domain.Member;
import com.oyk.product.community.dto.artcle.ArticleDTO;
import com.oyk.product.community.dto.artcle.ArticleModifyForm;
import com.oyk.product.community.dto.artcle.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Member member, Board board){

        Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );

        article.setMember(member);
        article.setBoard(board);

        articleRepository.save(article);
    }

    public Optional<Article> findById(Long id){
        return articleRepository.findById(id);
    }

    public Article getById(Long id) throws NoSuchElementException{

        Optional<Article> articleOptional = findById(id);

        articleOptional.orElseThrow(
                () -> new NoSuchElementException("해당 게시물은 존재하지 않습니다.")
        );

        return articleOptional.get();

    }

    public ArticleDTO getArticle(Long id){
        Article findArticle = getById(id);

        ArticleDTO articleDTO = new ArticleDTO(findArticle);

        return articleDTO;

    }


    @Transactional
    public void modifyArticle(ArticleModifyForm articleModifyForm, Long id){

        Article findArticle = getById(id);

        findArticle.modifyArticle(
                articleModifyForm.getTitle(),
                articleModifyForm.getBody()
        );
    }

    public List<ArticleDTO> getArticleList() {

        List<Article> articleList = articleRepository.findAll();

        List<ArticleDTO> articleDTOList = new ArrayList<>();

        for (Article article: articleList) {

            ArticleDTO articleDTO = new ArticleDTO(article);
            articleDTOList.add(articleDTO);

        }

        return articleDTOList;

    }

    @Transactional
    public void delete(Long id){
        Article findArticle = getById(id);

        articleRepository.delete(findArticle);
    }

}
