package com.oyk.product.community.controller;

import com.oyk.product.community.dto.board.BoardSaveForm;
import com.oyk.product.community.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards/add")
    public String showAddBoard(Model model){

        model.addAttribute("boardSaveForm", new BoardSaveForm());

        return "usr/board/add";

    }
    @PostMapping("/boards/add")
    public String doAddBoard(BoardSaveForm boardSaveForm){

        boardService.save(boardSaveForm);

        return "redirect:/";

    }
}
