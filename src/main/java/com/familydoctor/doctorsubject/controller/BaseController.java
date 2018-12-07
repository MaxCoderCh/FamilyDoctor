package com.familydoctor.doctorsubject.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * controller基类
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

    /**
     * date类型参数报错
     *
     * @param bin
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder bin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor cust = new CustomDateEditor(sdf, true);
        bin.registerCustomEditor(Date.class, cust);
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

    /**
     * 请求成功
     *
     * @param result
     * @return resultMap
     */
    public Map requestSuccessful(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "40001");
        resultMap.put("msg", "Request_Success");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 请求失败
     *
     * @param result
     * @return resultMap
     */
    public Map requestUnsuccessful(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "41001");
        resultMap.put("msg", "Request_Unsuccessful");
        resultMap.put("result", result);
        return resultMap;
    }
}