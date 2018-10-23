package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.DeliveryWay;
import com.familydoctor.doctorsubject.mapper.DeliveryWayMapper;
import com.familydoctor.doctorsubject.service.DeliveryWayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 给药,返回值为成功返回值
 */
@Slf4j
@RestController
@RequestMapping(value = "familydoctor/deliveryWay")
public class DeliveryWayController extends BaseController {

    @Autowired
    private DeliveryWayService deliveryWayService;

    /**
     * 添加给药信息
     *
     * @param deliveryWay
     * @return requestInsertSuccess(deliveryWay)
     */
    @PostMapping(value = "add")
    public Map addDeliveryWay(DeliveryWay deliveryWay) {

        if (deliveryWay == null) {
            return requestArgumentEmpty("添加对象为空");
        }

        int i = deliveryWayService.insertDelivery(deliveryWay);
        if (i > 0) {
            return requestInsertSuccess(deliveryWay);
        } else {
            return requestInsertFail(deliveryWay);
        }
    }

    /**
     * 删除给药
     *
     * @param deliveryWay
     * @return requestDeleteSuccess(deliveryWay)
     */
    @GetMapping(value = "delete")
    public Map deleteDelivery(DeliveryWay deliveryWay) {

        if (StringUtils.isBlank(deliveryWay.getId())) {
            return requestArgumentEmpty("传入id为空");
        }

        deliveryWay.setDeleteTime(addTime());
        int i = deliveryWayService.softDel(deliveryWay);
        if (i > 0) {
            return requestDeleteSuccess(deliveryWay);
        } else {
            return requestDeleteFail(deliveryWay);
        }
    }

    /**
     * 修改给药
     *
     * @param deliveryWay
     * @return requestUpdateSuccess(deliveryWay)
     */
    @PostMapping(value = "update")
    public Map updateDeliveryWay(DeliveryWay deliveryWay) {

        if (StringUtils.isBlank(deliveryWay.getId())) {
            return requestArgumentEmpty("传入的值为空");
        }

        deliveryWay.setUpdateTime(addTime());
        int i = deliveryWayService.updateById(deliveryWay);
        if (i > 0) {
            return requestUpdateSuccess(deliveryWay);
        } else {
            return requestUpdateFail(deliveryWay);
        }
    }

    /**
     * 由Id是否传入查询不同给药状态
     *
     * @param deliveryWay
     * @return deliveryWay
     */
    @GetMapping(value = "selectByIdOrAll")
    public Map selectDelivery(DeliveryWay deliveryWay) {

        if (StringUtils.isBlank(deliveryWay.getId())) {
            //查询所有给药
            List<DeliveryWay> deliveryWayList = deliveryWayService.selectAllDelivery();
            if (deliveryWay != null && !deliveryWayList.isEmpty()) {
                return requestSelectSuccess(deliveryWayList);
            }
            return requestSelectFail("查询失败");
        }

        DeliveryWay resultDeliveryWay = deliveryWayService.selectById(deliveryWay);
        if (resultDeliveryWay != null) {
            //查询对应Id给药
            return requestSelectSuccess(resultDeliveryWay);
        } else {
            return requestSelectFail("查询结果为空");
        }
    }

    /**
     * 分页查询
     *
     * @param deliveryWay
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(DeliveryWay deliveryWay) {

        List<DeliveryWay> deliveryWayList = deliveryWayService.selectPage(deliveryWay);

        if (deliveryWayList != null && !deliveryWayList.isEmpty()) {
            return requestSelectSuccess(deliveryWayList);
        }

        return requestSelectFail("查询失败");
    }

}
