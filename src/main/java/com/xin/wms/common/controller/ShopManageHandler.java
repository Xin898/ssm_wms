package com.xin.wms.common.controller;

import com.xin.wms.common.service.Interface.ShopsManageService;
import com.xin.wms.common.util.Response;
import com.xin.wms.common.util.ResponseFactory;
import com.xin.wms.exception.CustomerManageServiceException;
import com.xin.wms.exception.ShopsManageServiceException;
import com.xin.wms.pojo.Customer;
import com.xin.wms.pojo.Shop;
import com.xin.wms.pojo.Supplier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 店铺信息管理请求 Handler
 *
 * @author xin
 */
@RestController
@RequestMapping(value = "/**/ShopManage")
public class ShopManageHandler {

    @Autowired
    private ShopsManageService shopsManageService;

    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_NAME = "searchByName";
    private static final String SEARCH_ALL = "searchAll";

    /**
     * Shop 模块基本测试
     */
    @GetMapping("/shopTest")
    public String ShopManagerTest(){
        return "Here is the Access to ShopManage Fuctions";
    }

    /**
     * 通用的结果查询方法
     *
     * @param searchType 查询方式
     * @param keyWord    查询关键字
     * @param offset     分页偏移值
     * @param limit      分页大小
     * @return 返回指定条件查询的结果
     */
    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) throws ShopsManageServiceException {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_ID:
                if (StringUtils.isNumeric(keyWord))
                    queryResult = shopsManageService.selectById(Integer.valueOf(keyWord));
                break;
            case SEARCH_BY_NAME:
                queryResult = shopsManageService.selectByName(offset, limit, keyWord);
                break;
            case SEARCH_ALL:
                queryResult = shopsManageService.selectAll(offset, limit);
                break;
            default:
                // do other thing
                break;
        }
        return queryResult;
    }

    /**
     * 搜索店铺信息
     *
     * @param searchType 搜索类型
     * @param offset     如有多条记录时分页的偏移值
     * @param limit      如有多条记录时分页的大小
     * @param keyWord    搜索的关键字
     * @return 返回查询的结果，其中键值为 rows 的代表查询到的每一记录，若有分页则为分页大小的记录；键值为 total 代表查询到的符合要求的记录总条数
     */
    @RequestMapping(value = "getShopList", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getShopList(@RequestParam("searchType") String searchType,
                                        @RequestParam("offset") int offset,
                                        @RequestParam("limit") int limit,
                                        @RequestParam("keyWord") String keyWord) throws CustomerManageServiceException, ShopsManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        List<Supplier> rows = null;
        long total = 0;

        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);

        if (queryResult != null) {
            rows = (List<Supplier>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }

        // 设置 Response
        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        responseContent.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        return responseContent.generateResponse();
    }

    /**
     * 添加一条店铺信息
     *
     * @param shop 店铺信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "addShop", method = RequestMethod.PUT)
    public
    @ResponseBody
    Map<String, Object> addCustomer(@ModelAttribute Shop shop) throws CustomerManageServiceException, ShopsManageServiceException {

        System.out.println(">>>> Method addCustomer is triggered ");
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 当前用户信息
        System.out.println(shop.toString());

        // 添加记录
        String result = shopsManageService.addShop(shop) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

    /**
     * 查询指定 shop ID 店铺的信息
     *
     * @param shopID 店铺ID
     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
     * 的值为客户信息
     */
    @RequestMapping(value = "getShopInfo", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getShopInfo(@RequestParam("shopID") String shopID)
            throws ShopsManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;

        // 获取客户信息
        Shop shop = null;

        // 检查返回的结果
        Map<String, Object> queryResult = query(SEARCH_BY_ID, shopID, -1, -1);
        ArrayList<Shop> rows = null;
        long total = 0;
        if (queryResult != null) {
            rows = (ArrayList<Shop>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }
        for (Shop shop1 : rows) {
            shop = shop1;
        }

        // 设置 Response
        responseContent.setResponseResult(result);
        responseContent.setResponseData(shop);

        return responseContent.generateResponse();
    }

    /**
     * 更新店铺信息
     *
     * @param shop 店铺信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "updateShop", method = RequestMethod.PATCH)
    public
    @ResponseBody
    Map<String, Object> updateShop(@ModelAttribute Shop shop) throws ShopsManageServiceException {

        System.out.println(shop.getShopName());
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 更新
        String result = shopsManageService.updateShops(shop) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

    /**
     * 删除店铺记录
     *
     * @param shopIDStr 店铺ID
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @RequestMapping(value = "delCustomer", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Map<String, Object> deleteCustomer(@RequestParam("shopID") String shopIDStr) throws ShopsManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 参数检查
        if (StringUtils.isNumeric(shopIDStr)) {
            // 转换为 Integer
            Integer shopID = Integer.valueOf(shopIDStr);

            // 刪除
            String result = shopsManageService.deleteShops(shopID) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;
            responseContent.setResponseResult(result);
        } else
            responseContent.setResponseResult(Response.RESPONSE_RESULT_ERROR);

        return responseContent.generateResponse();
    }

    /**
     * TODO V2功能
     * 导入店铺信息
     *
     * @param file 保存有店铺信息的文件
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与
     * error；key为total表示导入的总条数；key为available表示有效的条数
     */


    /**
     * TODO V2功能
     * 导出店铺信息
     *
     * @param searchType 查找类型
     * @param keyWord    查找关键字
     * @param response   HttpServletResponse
     */
}
