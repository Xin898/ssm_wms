package com.xin.wms.common.controller;

import com.xin.wms.common.service.Interface.SupplierManageService;
import com.xin.wms.common.util.Response;
import com.xin.wms.common.util.ResponseFactory;
import com.xin.wms.exception.SupplierManageServiceException;
import com.xin.wms.pojo.Supplier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 供应商信息管理请求 Handler
 *
 * @author Xin
 */
@RequestMapping(value = "/**/supplierManage")
@Controller
public class SupplierManageHandler {

    @Autowired
    private SupplierManageService supplierManageService;

    private static final String SEARCH_BY_ID = "searchByID";
    private static final String SEARCH_BY_NAME = "searchByName";
    private static final String SEARCH_ALL = "searchAll";

    /**
     * 通用的记录查询
     *
     * @param searchType 查询类型
     * @param keyWord    查询关键字
     * @param offset     分页偏移值
     * @param limit      分页大小
     * @return 返回所有符合条件的记录
     */
    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) throws SupplierManageServiceException {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_ID:
                if (StringUtils.isNumeric(keyWord)) {
                    queryResult = supplierManageService.selectById(Integer.valueOf(keyWord));
                }
                break;
            case SEARCH_BY_NAME:
                queryResult = supplierManageService.selectByName(offset, limit, keyWord);
                break;
            case SEARCH_ALL:
                queryResult = supplierManageService.selectAll(offset, limit);
                break;
            default:
                // do other thing
                break;
        }

        return queryResult;
    }

    /**
     * 搜索供应商信息
     *
     * @param searchType 搜索类型
     * @param offset     如有多条记录时分页的偏移值
     * @param limit      如有多条记录时分页的大小
     * @param keyWord    搜索的关键字
     * @return
     */
    @GetMapping("/getSupplierList")
    @ResponseBody
    public Map<String, Object> getSupplierList(@RequestParam("searchType") String searchType,
                                               @RequestParam("offset") int offset, @RequestParam("limit") int limit,
                                               @RequestParam("keyWord") String keyWord) throws SupplierManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        List<Supplier> rows = null;
        long total = 0;

        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);

        // 结果转换
        if (queryResult != null) {
            rows = (List<Supplier>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }

        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        return responseContent.generateResponse();
    }

    /**
     * 添加一条供应商信息
     *
     * @param supplier 供应商信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @PostMapping("/addSupplier")
    public
    @ResponseBody
    Map<String, Object> addSupplier(@ModelAttribute Supplier supplier) throws SupplierManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 添加记录
        String result = supplierManageService.addSupplier(supplier) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

        // 设置 Response
        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

    /**
     * 查询指定 supplierID 供应商的信息
     *
     * @param supplierID 供应商ID
     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
     * 的值为供应商信息
     */
    @GetMapping(value = "/getSupplierInfo")
    public
    @ResponseBody
    Map<String, Object> getSupplierInfo(@RequestParam("supplierID") int supplierID) throws SupplierManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;

        // 获取供应点信息
        Supplier supplier = null;
        Map<String, Object> queryResult = supplierManageService.selectById(supplierID);
        if (queryResult != null) {
            supplier = (Supplier) queryResult.get("data");
            if (supplier != null)
                result = Response.RESPONSE_RESULT_SUCCESS;
        }

        // 设置 Response
        responseContent.setResponseResult(result);
        responseContent.setResponseData(supplier);
        return responseContent.generateResponse();
    }

    /**
     * 更新供应商信息
     *
     * @param supplier 供应商信息
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @PatchMapping("/updateSupplier")
    public
    @ResponseBody
    Map<String, Object> updateSupplier(@ModelAttribute Supplier supplier) throws SupplierManageServiceException {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 更新
        String result = supplierManageService.updateSupplier(supplier) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

        // 设置 Response
        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

    /**
     * 删除供应商记录
     *
     * @param supplierID 供应商ID
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与 error
     */
    @DeleteMapping("/deleteSupplier")
    public
    @ResponseBody
    Map<String, Object> deleteSupplier(@RequestParam("supplierID") Integer supplierID) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        // 刪除
        String result = supplierManageService.deleteSupplier(supplierID) ? Response.RESPONSE_RESULT_SUCCESS : Response.RESPONSE_RESULT_ERROR;

        // 设置 Response
        responseContent.setResponseResult(result);
        return responseContent.generateResponse();
    }

}

