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



