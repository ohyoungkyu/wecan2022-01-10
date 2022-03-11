package com.oyk.product.community.service;

import com.oyk.product.community.config.Role;
import com.oyk.product.community.dao.BoardRepository;
import com.oyk.product.community.dao.MemberRepository;
import com.oyk.product.community.domain.Board;
import com.oyk.product.community.dto.adm.AdmBoardCountDto;
import com.oyk.product.community.dto.adm.AdmBoardNameDto;
import com.oyk.product.community.dto.adm.BoardStateDto;
import com.oyk.product.community.dto.adm.MemberStateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdmService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public MemberStateDto getMemberStateDto(){

        return new MemberStateDto(
                memberRepository.count(),
                memberRepository.countTodayMember(),
                memberRepository.countByAuthorityLike(Role.ADMIN),
                memberRepository.countByAuthorityLike(Role.MEMBER)
        );

    }

    public BoardStateDto getBoardStateDto() {

        List<Board> findLatestBoards = boardRepository.find3LatestBoard();

        List<AdmBoardNameDto> latestBoardList = new ArrayList<>();
        List<AdmBoardCountDto> boardCountList = new ArrayList<>();

        for( Board findLatestBoard : findLatestBoards ){

            AdmBoardNameDto admBoardNameDto = new AdmBoardNameDto(findLatestBoard);
            latestBoardList.add(admBoardNameDto);

        }

        List<Board> findAll = boardRepository.findAll();

        for( Board board : findAll ) {

            AdmBoardCountDto admBoardCountDto = new AdmBoardCountDto(board);
            boardCountList.add(admBoardCountDto);

        }

        return new BoardStateDto(
                boardRepository.count(),
                latestBoardList,
                boardCountList
        );
    }

}
