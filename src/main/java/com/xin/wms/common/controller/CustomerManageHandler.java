package com.xin.wms.common.controller;

import com.xin.wms.common.service.Interface.CustomerManageService;
import com.xin.wms.common.util.Response;
import com.xin.wms.common.util.ResponseFactory;
import com.xin.wms.exception.CustomerManageServiceException;
import com.xin.wms.pojo.Customer;
import com.xin.wms.pojo.Supplier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 客户信息管理请求 Handler
 *
 * @author Xin
 */
@RestController
@RequestMapping(value = "/**/CustomerManage")
public class CustomerManageHandler {

    @Autowired
    private CustomerManageService customerManageService;

    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_NAME = "searchByName";
    private static final String SEARCH_ALL = "searchAll";

    /**
     * Customer 模块基本测试
     */
    @GetMapping("/test")
    public String CustomerManagerTest(){
        return "Here is the Access to CustomerManage Fuctions";
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
    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) throws CustomerManageServiceException {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_ID:
                if (StringUtils.isNumeric(keyWord))
                    queryResult = customerManageService.selectById(Integer.valueOf(keyWord));
                break;
            case SEARCH_BY_NAME:
                queryResult = customerManageService.selectByName(offset, limit, keyWord);
                break;
            case SEARCH_ALL:
                queryResult = customerManageService.selectAll(offset, limit);
                break;
            default:
                // do other thing
                break;
        }
        return queryResult;
    }

    /**
     * 搜索客户信息
     *
     * @param searchType 搜索类型
     * @param offset     如有多条记录时分页的偏移值
     * @param limit      如有多条记录时分页的大小
     * @param keyWord    搜索的关键字
     * @return 返回查询的结果，其中键值为 rows 的代表查询到的每一记录，若有分页则为分页大小的记录；键值为 total 代表查询到的符合要求的记录总条数
     */
    @SuppressWarnings("unchecked")
//    @RequestMapping(value = "getCustomerList", method = RequestMethod.GET)
    @PostMapping("/getCustomerList")
    public
    @ResponseBody
    Map<String, Object> getCustomerList(@RequestParam("searchType") String searchType,
                                        @RequestParam("offset") int offset,
                                        @RequestParam("limit") int limit,
                                        @RequestParam("keyWord") String keyWord) throws CustomerManageServiceException {
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
     * 添加一条客户信息
     *
     * @param customer 客户信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @PutMapping("/addCustomer")
    public
    @ResponseBody
    Map<String, Object> addCustomer(@ModelAttribute Customer customer) throws CustomerManageServiceException {

        System.out.println(">>>> Method addCustomer is triggered ");
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 当前用户信息
        System.out.println(customer.toString());

        // 添加记录
        String result = customerManageService.addCustomer(customer) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

    /**
     * 查询指定 customer ID 客户的信息
     *
     * @param customerID 客户ID
     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
     * 的值为客户信息
     */
    @GetMapping("/getCustomerInfo")
    public
    @ResponseBody
    Map<String, Object> getCustomerInfo(@RequestParam("customerID") String customerID)
            throws CustomerManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;

        // 获取客户信息
        Customer customer = null;
        System.out.println(">>> Customer ID is " + customerID);

        // 检查返回的结果
        Map<String, Object> queryResult = query(SEARCH_BY_ID, customerID, -1, -1);
        ArrayList<Customer> rows = null;
        long total = 0;
        if (queryResult != null) {
            rows = (ArrayList<Customer>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }
        for (Customer cmr : rows) {
            System.out.println(total + " >>>> " + cmr.toString());
            customer = cmr;
        }

        // 设置 Response
        responseContent.setResponseResult(result);
        responseContent.setResponseData(customer);

        return responseContent.generateResponse();
    }

    /**
     * 更新客户信息
     *
     * @param customer 客户信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @PatchMapping("/updateCustomer")
    public
    @ResponseBody
    Map<String, Object> updateCustomer(@ModelAttribute Customer customer) throws CustomerManageServiceException {

        System.out.println(customer.getName());
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 更新
        String result = customerManageService.updateCustomer(customer) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

    /**
     * 删除客户记录
     *
     * @param customerIDStr 客户ID
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @DeleteMapping("/delCustomer")
    public
    @ResponseBody
    Map<String, Object> deleteCustomer(@RequestParam("customerID") String customerIDStr) throws CustomerManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 参数检查
        if (StringUtils.isNumeric(customerIDStr)) {
            // 转换为 Integer
            Integer customerID = Integer.valueOf(customerIDStr);

            // 刪除
            String result = customerManageService.deleteCustomer(customerID) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;
            responseContent.setResponseResult(result);
        } else
            responseContent.setResponseResult(Response.RESPONSE_RESULT_ERROR);

        return responseContent.generateResponse();
    }

    /**
     * TODO V2功能
     * 导入客户信息
     *
     * @param file 保存有客户信息的文件
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与
     * error；key为total表示导入的总条数；key为available表示有效的条数
     */


    /**
     * TODO V2功能
     * 导出客户信息
     *
     * @param searchType 查找类型
     * @param keyWord    查找关键字
     * @param response   HttpServletResponse
     */

}

