package com.oyk.product.community.dto.adm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardStateDto {

    private Long totalBoardCount;
    private List<AdmBoardNameDto> latestBoardList;
    private List<AdmBoardCountDto> boardCountList;

}
