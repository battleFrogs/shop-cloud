package org.example.goods.web;

import cn.hutool.core.util.RandomUtil;
import org.example.common.core.constants.ResultData;
import org.example.goods.vo.GetGoodsVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        System.out.println("GoodsTest");
        return "GoodsTest";
    }

    @RequestMapping("/getGoods")
    public ResultData<GetGoodsVO> getGoods() {
        GetGoodsVO getGoodsVO = new GetGoodsVO();
        List<GetGoodsVO.GoodsInfo> goodsInfoList = new ArrayList<>();
        IntStream.rangeClosed(1, 4).forEach(res -> {
            GetGoodsVO.GoodsInfo goodsInfo = new GetGoodsVO.GoodsInfo();
            goodsInfo.setGoodsName("商品" + res);
            goodsInfo.setGoodsPrice(BigDecimal.valueOf(ThreadLocalRandom.current().nextLong(12, 100)));
            goodsInfo.setGoodsDescription("商品描述" + RandomUtil.randomString(RandomUtil.BASE_CHAR_NUMBER, 20));
            goodsInfo.setOnSelf(ThreadLocalRandom.current().nextLong(12, 100) % 2 == 0 ? "ON" : "OFF");
            goodsInfoList.add(goodsInfo);

        });


        getGoodsVO.setTotal(goodsInfoList.size());
        getGoodsVO.setGoodsInfoList(goodsInfoList);
        return ResultData.ok(getGoodsVO);
    }


}
