package org.example.goods.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GetGoodsVO {


    private Integer total;
    private List<GoodsInfo> goodsInfoList;

    @Data
    public static class GoodsInfo {

        private String goodsName;
        private BigDecimal goodsPrice;
        private String goodsDescription;
        private String onSelf;

    }



}
