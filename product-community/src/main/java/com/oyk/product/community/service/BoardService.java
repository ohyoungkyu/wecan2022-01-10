package com.oyk.product.community.service;

import com.oyk.product.community.dao.BoardRepository;
import com.oyk.product.community.domain.Board;
import com.oyk.product.community.dto.board.BoardSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardSaveForm boardSaveForm) {

        Board board = Board.createBoard(
                boardSaveForm.getName(),
                boardSaveForm.getDetail()
        );

        boardRepository.save(board);

    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
