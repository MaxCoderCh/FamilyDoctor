package com.familydoctor.doctorsubject.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * controller基础模板
 */
public abstract class BaseController {

    /**
     * 获取使用者
     *
     * @return request.getHeader(" token ")
     */
    public String getCurrentUser() {

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        return request.getHeader("token");
    }

    private Map<String, Object> resultMap;

    /**
     * 插入时间戳
     *
     * @return date
     */
    public Date addTime() {
        Date date = new Date();

        return date;
    }

    public Map BadRequest(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "11000");
        resultMap.put("msg", "Bad_Request");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 添加操作请求成功
     *
     * @param result
     * @return resultMap
     */
    public Map requestInsertSuccess(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "Insert_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 添加操作失败
     *
     * @param result
     * @return resultMap
     */
    public Map requestInsertFail(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20001");
        resultMap.put("msg", "Insert_Fail");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 删除操作请求成功
     *
     * @param result
     * @return resultMap
     */
    public Map requestDeleteSuccess(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "21000");
        resultMap.put("msg", "Delete_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 删除操作失败
     *
     * @param result
     * @return resultMap
     */
    public Map requestDeleteFail(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "21001");
        resultMap.put("msg", "Delete_Fail");
        resultMap.put("result", result);
        return resultMap;

    }

    /**
     * 更新操作请求成功
     *
     * @param result
     * @return resultMap
     */
    public Map requestUpdateSuccess(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "22000");
        resultMap.put("msg", "Update_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 更新操作失败
     *
     * @param result
     * @return resultMap
     */
    public Map requestUpdateFail(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "22001");
        resultMap.put("msg", "Update_Fail");
        resultMap.put("result", result);
        return resultMap;

    }

    /**
     * 查询操作请求成功
     *
     * @param result
     * @return resultMap
     */
    public Map requestSelectSuccess(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "23000");
        resultMap.put("msg", "Select_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 查询操作失败
     *
     * @param result
     * @return resultMap
     */
    public Map requestSelectFail(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "23001");
        resultMap.put("msg", "Select_Fail");
        resultMap.put("result", result);
        return resultMap;

    }

    /**
     * 请求参数错误
     *
     * @param result
     * @return resultMap
     */
    public Map requestArgumentError(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "30001");
        resultMap.put("msg", "Argument_Error");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 请求参数为空
     *
     * @param result
     * @return resultMap
     */
    public Map requestArgumentEmpty(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "31001");
        resultMap.put("msg", "Argument_Empty");
        resultMap.put("result", result);
        return resultMap;
    }

}