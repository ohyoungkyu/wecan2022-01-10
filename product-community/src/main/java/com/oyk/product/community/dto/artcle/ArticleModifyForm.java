package com.oyk.product.community.dto.artcle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleModifyForm {

    private String title;

    private String body;

}