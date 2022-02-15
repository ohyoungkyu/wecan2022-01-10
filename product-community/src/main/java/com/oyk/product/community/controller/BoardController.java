package com.oyk.product.community.controller;

import com.oyk.product.community.domain.Board;
import com.oyk.product.community.domain.Member;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
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
    public  String showBoardDetail(@PathVariable(name = "id")Long id, Model model){

        try {

            BoardDTO boardDetail = boardService.getBoardDetail(id);
            model.addAttribute("board", boardDetail);
        } catch (Exception e){
            return "redirect:/";
        }

        return "adm/board/detail";

    }


}
