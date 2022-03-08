package com.xin.wms.common.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.wms.common.service.Interface.SystemLogService;
import com.xin.wms.dao.AccessRecordMapper;
import com.xin.wms.dao.UserOperationRecordMapper;
import com.xin.wms.exception.SystemLogServiceException;
import com.xin.wms.pojo.AccessRecordDO;
import com.xin.wms.pojo.AccessRecordDTO;
import com.xin.wms.pojo.UserOperationRecordDO;
import com.xin.wms.pojo.UserOperationRecordDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private AccessRecordMapper accessRecordMapper;
    @Autowired
    private UserOperationRecordMapper userOperationRecordMapper;

    @Override
    public void insertAccessRecord(Integer userID, String userName, String accessIP, String accessType) throws SystemLogServiceException {
        // 创建 AccessRecordDO 对象
        AccessRecordDO accessRecordDO = new AccessRecordDO();
        accessRecordDO.setUserID(userID);
        accessRecordDO.setUserName(userName);
        accessRecordDO.setAccessTime(new Date());
        accessRecordDO.setAccessIP(accessIP);
        accessRecordDO.setAccessType(accessType);

        // 持久化 AccessRecordDO 对象到数据库
        try {
            accessRecordMapper.insertAccessRecord(accessRecordDO);
        } catch (PersistenceException e) {
            throw new SystemLogServiceException(e, "Fail to persist AccessRecordDO Object");
        }
    }

    /**
     * 选择指定用户ID、记录类型或日期范围的登入登出记录
     *
     * @param userID       用户ID
     * @param accessType   记录类型
     * @param startDateStr 记录起始日期
     * @param endDateStr   记录结束日期
     * @return 返回一个Map， 其中键值为 data 的值为所有符合条件的记录， 而键值为 total 的值为符合条件的记录总条数
     */
    @Override
    public Map<String, Object> selectAccessRecord(Integer userID, String accessType, String startDateStr, String endDateStr) throws SystemLogServiceException {
        return selectAccessRecord(userID, accessType, startDateStr, endDateStr, -1, -1);
    }

    /**
     * 分页查询指定用户ID、记录类型或日期范围的登入登出记录
     *
     * @param userID       用户ID
     * @param accessType   记录类型
     * @param startDateStr 记录起始日期
     * @param endDateStr   记录结束日期
     * @param offset       分页偏移值
     * @param limit        分页大小
     * @return 返回一个Map， 其中键值为 data 的值为所有符合条件的记录， 而键值为 total 的值为符合条件的记录总条数
     */
    @Override
    public Map<String, Object> selectAccessRecord(Integer userID, String accessType, String startDateStr, String endDateStr, int offset, int limit) throws SystemLogServiceException {
        // 准备结果集
        Map<String, Object> resultSet = new HashMap<>();
        List<AccessRecordDTO> accessRecordDTOS = new ArrayList<>();
        long total = 0;
        boolean isPagination = true;

        // 检查是否需要分页查询
        if (offset < 0 || limit < 0)
            isPagination = false;

        // 转换 Date 对象
        Date startDate = null;
        Date endDate = null;
        try {
            if (StringUtils.isNotEmpty(startDateStr))
                startDate = dateFormatSimple.parse(startDateStr);
            if (StringUtils.isNotEmpty(endDateStr)) {
                endDate = dateFormatSimple.parse(endDateStr);
                endDate = DateUtils.addDays(endDate, 1);
            }

        } catch (ParseException e) {
            throw new SystemLogServiceException(e, "Fail to convert string to Date Object");
        }

        // 转换 accessType
        switch (accessType) {
            case "loginOnly":
                accessType = SystemLogService.ACCESS_TYPE_LOGIN;
                break;
            case "logoutOnly":
                accessType = SystemLogService.ACCESS_TYPE_LOGOUT;
                break;
            default:
                accessType = "all";
                break;
        }

        // 执行查询操作
        List<AccessRecordDO> accessRecordDOS;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                accessRecordDOS = accessRecordMapper.selectAccessRecords(userID, accessType, startDate, endDate);
                if (accessRecordDOS != null) {
                    accessRecordDOS.forEach(accessRecordDO -> accessRecordDTOS.add(convertAccessRecordDOToAccessRecordDTO(accessRecordDO)));
                    total = new PageInfo<>(accessRecordDOS).getTotal();
                }
            } else {
                accessRecordDOS = accessRecordMapper.selectAccessRecords(userID, accessType, startDate, endDate);
                if (accessRecordDOS != null) {
                    accessRecordDOS.forEach(accessRecordDO -> accessRecordDTOS.add(convertAccessRecordDOToAccessRecordDTO(accessRecordDO)));
                    total = accessRecordDOS.size();
                }
            }
        } catch (PersistenceException e) {
            throw new SystemLogServiceException(e);
        }

        resultSet.put("data", accessRecordDTOS);
        resultSet.put("total", total);
        return resultSet;
    }

    @Override
    public void insertUserOperationRecord(Integer userID, String userName, String operationName, String operationResult) throws SystemLogServiceException {

    }

    @Override
    public Map<String, Object> selectUserOperationRecord(Integer userID, String startDateStr, String endDateStr) throws SystemLogServiceException {
        return null;
    }

    @Override
    public Map<String, Object> selectUserOperationRecord(Integer userID, String startDateStr, String endDateStr, int offset, int limit) throws SystemLogServiceException {
        return null;
    }

    /**
     * Date 具体格式
     */
    private DateFormat dateFormatDetail = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    private DateFormat dateFormatSimple = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 将 AccessRecordDO 对象转换为 AccessRecordDTO 对象
     *
     * @param accessRecordDO AccessRecordDO 对象
     * @return 返回 AccessRecordDTO 对象
     */
    private AccessRecordDTO convertAccessRecordDOToAccessRecordDTO(AccessRecordDO accessRecordDO) {
        AccessRecordDTO accessRecordDTO = new AccessRecordDTO();
        accessRecordDTO.setId(accessRecordDO.getId());
        accessRecordDTO.setUserID(accessRecordDO.getUserID());
        accessRecordDTO.setUserName(accessRecordDO.getUserName());
        accessRecordDTO.setAccessIP(accessRecordDO.getAccessIP());
        accessRecordDTO.setAccessType(accessRecordDO.getAccessType().equals(SystemLogService.ACCESS_TYPE_LOGIN) ? "登入" : "登出");
        accessRecordDTO.setAccessTime(dateFormatDetail.format(accessRecordDO.getAccessTime()));
        return accessRecordDTO;
    }

    /**
     * 将 UserOperationRecordDO 对象转换为 UserOperationRecordDTO 对象
     *
     * @param userOperationRecordDO UserOperationRecordDO 对象
     * @return 返回 UserOperationRecordDTO 对象
     */
    private UserOperationRecordDTO convertUserOperationRecordDOToUserOperationRecordDTO(UserOperationRecordDO userOperationRecordDO) {
        UserOperationRecordDTO userOperationRecordDTO = new UserOperationRecordDTO();
        userOperationRecordDTO.setId(userOperationRecordDO.getId());
        userOperationRecordDTO.setUserID(userOperationRecordDO.getUserID());
        userOperationRecordDTO.setUserName(userOperationRecordDO.getUserName());
        userOperationRecordDTO.setOperationName(userOperationRecordDO.getOperationName());
        userOperationRecordDTO.setOperationResult(userOperationRecordDO.getOperationResult());
        userOperationRecordDTO.setOperationTime(dateFormatDetail.format(userOperationRecordDO.getOperationTime()));
        return userOperationRecordDTO;
    }
}
