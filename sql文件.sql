drop table if exists user;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
    user_id              bigint not null auto_increment comment '用户Id',
    user_name            varchar(100) comment '用户名',
    password             varchar(100) comment '密码',
    phone                varchar(20) comment '手机号',
    primary key (user_id)
);

drop table if exists goods;

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
    goods_id             bigint not null auto_increment comment '主键Id',
    goods_name           varchar(100) comment '商品名称',
    goods_img            varchar(200) comment '商品图片',
    goods_description    varchar(500) comment '商品描述',
    on_self              tinyint comment '是否上架',
    primary key (goods_id)
);

alter table goods comment '商品';

