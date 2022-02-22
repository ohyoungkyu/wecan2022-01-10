package com.oyk.product.community.controller;

import com.oyk.product.community.domain.Board;
import com.oyk.product.community.domain.Member;
import com.oyk.product.community.dto.artcle.ArticleListDTO;
import com.oyk.product.community.dto.board.BoardDTO;
import com.oyk.product.community.dto.board.BoardModifyForm;
import com.oyk.product.community.dto.board.BoardSaveForm;
import com.oyk.product.community.service.BoardService;
import com.oyk.product.community.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    // List
    @GetMapping("/boards")
    public String showBoardList(Model model){

        List<Board> boardList = boardService.findAll();

        model.addAttribute("boardList", boardList);

        return "adm/board/list";

    }

    //디테일
    @GetMapping("/boards/{id}")
    public  String showBoardDetail(@PathVariable(name = "id")Long id, Model model, @RequestParam(name="page", defaultValue = "1") int page, @RequestParam(name="searchKeyword", defaultValue = "") String searchKeyword){

        int size = 10;

        try {

            BoardDTO boardDetail = boardService.getBoardDetail(id);

            List<ArticleListDTO> articleListDTO = boardDetail.getArticleListDTO();

            List<ArticleListDTO> store = new ArrayList<>();

            for( ArticleListDTO listDTO : articleListDTO) {

                if( listDTO.getTitle().contains(searchKeyword) ){
                    store.add(listDTO);
                }

            }

            if( store.size() != 0 ) {
                articleListDTO = store;
            }

            Collections.reverse(articleListDTO);

            // 0, 10, 20 ...
            int startIndex = (page - 1) * size;
            // 9, 19, 29 ...
            int lastIndex = ((page - 1) * size) + 9;

            int lastPage = (int)Math.ceil(articleListDTO.size()/(double)size);

            if( page == lastPage) {
                lastIndex = articleListDTO.size();
            }else if( page > lastPage){
                return "redirect:/";
            }else {
                lastIndex += 1;
            }

            // 페이지 자르기
            List<ArticleListDTO> articlePage = articleListDTO.subList(startIndex, lastIndex);

            if( !searchKeyword.equals("") && store.size() == 0 ) {

                articlePage = store;

            }

            model.addAttribute("board", boardDetail);
            model.addAttribute("articles", articlePage);
            model.addAttribute("maxPage", lastPage);
            model.addAttribute("currentPage", page);

        } catch (Exception e){
            return "redirect:/";
        }

        return "adm/board/detail";

    }


}
