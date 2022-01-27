package com.oyk.product.community.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardModifyForm {

    private Long id;

    private String name;

    private String detail;

}
