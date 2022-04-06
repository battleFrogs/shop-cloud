package org.example.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.goods.model.Goods;
import org.example.goods.mapper.GoodsMapper;
import org.example.goods.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * (Goods)
 *
 * @author gjc
 * @since 2022-04-06 20:24:05
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {

}