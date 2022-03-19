package com.xin.wms.common.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.wms.common.service.Interface.ShopsManageService;
import com.xin.wms.dao.ShopsMapper;
import com.xin.wms.exception.CustomerManageServiceException;
import com.xin.wms.exception.ShopsManageServiceException;
import com.xin.wms.pojo.Customer;
import com.xin.wms.pojo.Shop;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopsManageServiceImp implements ShopsManageService {

    @Autowired
    private ShopsMapper shopsMapper;


    /**
     * 返回指定shopId 的店铺记录
     *
     * @param shopId 店铺ID
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectById(Integer shopId) throws ShopsManageServiceException {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Shop> shops = new ArrayList<>();
        long total = 0;

        // 查询
        Shop shop;
        try {
            shop = shopsMapper.selectById(shopId);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
            throw new ShopsManageServiceException(e);
        }

        if (shops != null) {
            shops.add(shop);
            total = 1;
        }

        resultSet.put("data", shops);
        resultSet.put("total", total);
        return resultSet;
    }

    /**
     * 返回指定 shop name 的客户记录 支持查询分页以及模糊查询
     *
     * @param offset       分页的偏移值
     * @param limit        分页的大小
     * @param shopName 客户的名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectByName(int offset, int limit, String shopName) throws ShopsManageServiceException {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Shop> shops;
        long total = 0;
        boolean isPagination = true;

        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;

        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                shops = shopsMapper.selectApproximateByName(shopName);
                if (shops != null) {
                    PageInfo<Shop> pageInfo = new PageInfo<>(shops);
                    total = pageInfo.getTotal();
                } else
                    shops = new ArrayList<>();
            } else {
                shops = shopsMapper.selectApproximateByName(shopName);
                if (shops != null)
                    total = shops.size();
                else
                    shops = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            throw new ShopsManageServiceException(e);
        }

        resultSet.put("data", shops);
        resultSet.put("total", total);
        return resultSet;
    }

    /**
     * 返回指定 shop Name 的店铺记录 支持模糊查询
     *
     * @param shopName 店铺名称
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectByName(String shopName) throws ShopsManageServiceException {
        return selectByName(-1, -1, shopName);
    }

    /**
     * 分页查询店铺的记录
     *
     * @param offset 分页的偏移值
     * @param limit  分页的大小
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectAll(int offset, int limit) throws ShopsManageServiceException {

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<Shop> shops;
        long total = 0;
        boolean isPagination = true;

        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;

        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                shops = shopsMapper.selectAll();
                if (shops != null) {
                    PageInfo<Shop> pageInfo = new PageInfo<>(shops);
                    total = pageInfo.getTotal();
                } else
                    shops = new ArrayList<>();
            } else {
                shops = shopsMapper.selectAll();
                if (shops != null)
                    total = shops.size();
                else
                    shops = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            throw new ShopsManageServiceException(e);
        }

        resultSet.put("data", shops);
        resultSet.put("total", total);
        return resultSet;
    }

    /**
     * 查询所有客户的记录
     *
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    @Override
    public Map<String, Object> selectAll() throws ShopsManageServiceException {
        return selectAll(-1, -1);
    }

    @Override
    public boolean addShops(Shop shops) throws ShopsManageServiceException {
        return false;
    }

    @Override
    public boolean updateShops(Shop shops) throws ShopsManageServiceException {
        return false;
    }

    @Override
    public boolean deleteShops(Integer Id) throws ShopsManageServiceException {
        return false;
    }
}
