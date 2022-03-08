create database wms_db
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

use wms_db;

# 创建数据表

# 客户管理  客户基本信息
create table wmss_customer
(
    CUSTOMER_ID int not null auto_increment,
    CUSTOMER_NAME varchar(30) not null,
    CUSTOMER_PASSWORD varchar(10) not null,
    CUSTOMER_TEL varchar(20) not null,
    CUSTOMER_EMAIL varchar(20) not null,
    CUSTOMER_ADDRESS varchar(30) not null,
    CUSTOMER_CREDIT int not null,
    primary key(CUSTOMER_ID)
)engine=innodb;

# shop信息 shop基本信息
create table wmss_shop
(
    SHOP_ID int not null auto_increment,
    SHOP_NAME varchar(50) not null,
    SHOP_PLATFORM varchar(50) not null,
    CUSTOMER_URI varchar(2000) not null,
    primary key(SHOP_ID)
)engine=innodb;

# 商品信息 goods基本信息
create table wms_goods
(
    GOOD_ID int not null auto_increment,
    GOOD_NAME varChar(30) not null,
    GOOD_RYPE varchar(20),
    GOOD_SIZE varchar(20),
    GOOD_VALUE double not null,
    primary key(GOOD_ID)
)engine=innodb;