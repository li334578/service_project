create database if not exists sec_kill;


create table if not exists tbl_goods
(
    id             bigint primary key auto_increment comment '自增id',
    goods_name     varchar(255) comment '商品名称',
    goods_title    varchar(100) comment '商品标题',
    goods_price    decimal(10, 2) comment '商品价格',
    sec_kill_price decimal(10, 2) comment '秒杀价格',
    stock_count    int comment '商品库存'
) comment '商品表';

create table if not exists tbl_order
(
    id          bigint primary key auto_increment comment '自增id',
    user_id     bigint comment '订单所属用户id',
    order_num   varchar(100) comment '订单号',
    create_time datetime comment '订单创建时间',
    update_time datetime comment '订单更新时间',
    status      int comment '订单状态 1 待付款 2 待发货 3 待收货 4 待评价 5 已完成',
    total_money decimal(10, 2) comment '订单实付金额'
) comment '订单表';

insert into tbl_goods (goods_name, goods_title, goods_price, sec_kill_price, stock_count)
values ('iPhone 14 Pro Max', 'iPhone手机', 8848.00, 8000.00, 100);
