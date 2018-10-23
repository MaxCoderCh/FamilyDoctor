package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.bean.RegisteredBean;
import com.familydoctor.doctorsubject.entity.Registered;
import com.familydoctor.doctorsubject.service.RegisteredService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 挂号
 */
@RestController
@RequestMapping(value = "familydoctor/registered")
public class RegisteredController extends BaseController {

    @Autowired
    private RegisteredService registeredService;

    /**
     * 添加挂号信息
     *
     * @param registered
     * @return requestInsertSuccess(registered)
     */
    @PostMapping(value = "add")
    public Map addRegisteredMsg(Registered registered) {

        if (registered == null) {
            return requestArgumentEmpty("registered为空");
        }

        int i = registeredService.insertRegistered(registered);
        if (i > 0) {
            return requestInsertSuccess(registered);
        } else {
            return requestInsertFail("添加失败");
        }

    }

    /**
     * 由Id查询对应的挂号
     *
     * @param registered
     * @return requestSelectSuccess(resultHospital)
     */
    @GetMapping(value = "selectById")
    public Map selectRegisteredById(Registered registered) {

        if (StringUtils.isBlank(registered.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        Registered resultRegistered = registeredService.selectById(registered.getId());
        if (resultRegistered != null) {
            return requestSelectSuccess(resultRegistered);
        } else {
            return requestSelectFail("查询失败");
        }

    }

    /**
     * 由beginTime,endTime,hospitalId,branchId查询挂号信息
     *
     * @param beginTime
     * @param endTime
     * @param hospitalId
     * @param branchId
     * @return requestSelectSuccess(registeredList)
     */
    @GetMapping(value = "selectByParam")
    public Map selectRegisteredByParam(String beginTime, String endTime, String hospitalId, String branchId) {

        if (!StringUtils.isBlank(beginTime) && !StringUtils.isBlank(endTime) && !StringUtils.isBlank(hospitalId) && !StringUtils.isBlank(branchId)) {
            RegisteredBean registeredBean = new RegisteredBean();
            registeredBean.setBranchId(branchId);
            registeredBean.setHospitalId(hospitalId);
            registeredBean.setStartTime(beginTime);
            registeredBean.setEndTime(endTime);

            List<Registered> registeredList = registeredService.selectParam(registeredBean);
            if (registeredList != null && !registeredList.isEmpty()) {
                return requestSelectSuccess(registeredList);
            } else {
                return requestSelectFail("查询失败");
            }

        }

        return requestArgumentEmpty("参数为空");

    }

}
