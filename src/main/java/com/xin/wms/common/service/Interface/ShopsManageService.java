package com.xin.wms.common.service.Interface;

import com.xin.wms.exception.ShopsManageServiceException;
import com.xin.wms.pojo.Shop;

import java.util.Map;

public interface ShopsManageService {

    /**
     * 返回指定shop ID 的店铺记录
     *
     * @param  shopId
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectById(Integer shopId) throws ShopsManageServiceException;

    /**
     * 返回指定 shop name 的店铺记录
     * 支持查询分页以及模糊查询
     *
     * @param offset    分页的偏移值
     * @param limit     分页的大小
     * @param shopName 店铺的名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectByName(int offset, int limit, String shopName) throws ShopsManageServiceException;

    /**
     * 返回指定 shop name 的店铺记录
     * 支持模糊查询
     *
     * @param shopName 店铺名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectByName(String shopName) throws ShopsManageServiceException;

    /**
     * 分页查询店铺记录
     *
     * @param offset 分页的偏移值
     * @param limit  分页的大小
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectAll(int offset, int limit) throws ShopsManageServiceException;

    /**
     * 查询所有的店铺记录
     *
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    Map<String, Object> selectAll() throws ShopsManageServiceException;

    /**
     * 添加店铺记录
     *
     * @param shops 店铺信息
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    boolean addShops(Shop shops) throws ShopsManageServiceException;

    /**
     * 更新店铺记录
     *
     * @param shops 店铺
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    boolean updateShops(Shop shops) throws ShopsManageServiceException;

    /**
     * 删除店铺记录
     *
     * @param Id 店铺ID
     * @return 返回一个boolean值，值为true代表更新成功，否则代表失败
     */
    boolean deleteShops(Integer Id) throws ShopsManageServiceException;

}
