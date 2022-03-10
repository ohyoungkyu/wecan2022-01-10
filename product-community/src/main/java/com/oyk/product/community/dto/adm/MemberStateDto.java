package com.oyk.product.community.dto.adm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberStateDto {

    private Long totalMemberCount;
    private Long todaySignMemberCount;

    private Long adminMemberCount;
    private Long userMemberCount;

}
