package org.example.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


/**
 * (Goods)实体类
 *
 * @author gjc
 * @since 2022-04-06 20:40:35
 */
@Data
@TableName("goods")
public class Goods implements Serializable {
    private static final long serialVersionUID = -52816716037837392L;
    /**
     * 商品Id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Long goodsId;
    /**
     * 商品名称
     */

    @TableField(value = "goods_name")
    private String goodsName;
    /**
     * 商品数目
     */

    @TableField(value = "goods_num")
    private Long goodsNum;
    /**
     * 商品描述
     */

    @TableField(value = "goods_description")
    private String goodsDescription;
    /**
     * 商品价格
     */

    @TableField(value = "goods_price")
    private Long goodsPrice;

}