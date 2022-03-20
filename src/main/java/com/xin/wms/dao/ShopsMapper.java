package com.xin.wms.dao;

import com.xin.wms.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShopsMapper {

    /**
     * 选择所有的 Shops
     * @return 返回所有的 Shops
     */
    List<Shop> selectAll();

    /**
     * 选择指定 id 的 Shops
     * @param id 货物的ID
     * @return 返回执行ID对应的Shops
     */
    Shop selectById(Integer id);

    /**
     * 选择指定 Shops name 的 Shops
     * @param shopName 货物的名称
     * @return 返回指定ShopsName对应的货物
     */
    Shop selectByName(String shopName);

    /**
     * 选择制定 shops name 的 shop
     * 模糊匹配
     * @param shopName 货物德名称
     * @return 返回模糊匹配指定shopName的货物
     */
    List<Shop> selectApproximateByName(String shopName);

    /**
     * 插入一条新的记录到数据库
     * @param shops 货物信息
     */
    void insert(Shop shops);

    /**
     * 批量插入新的记录到数据库中
     * @param shops 存放 shops 信息的 List
     */
    void insertBatch(List<Shop> shops);

    /**
     * 更新 shop 到数据库中
     * 该 shop 必须已经存在于数据库中，即已经分配主键，否则将更新失败
     * @param shops 货物信息
     */
    void update(Shop shops);

    /**
     * 删除指定 id 的 shop
     * @param id 店铺id
     */
    void deleteById(Integer id);

    /**
     * 删除指定 shops name 的 shop
     * @param shopName 店铺名称
     */
    void deleteByName(String shopName);
}
