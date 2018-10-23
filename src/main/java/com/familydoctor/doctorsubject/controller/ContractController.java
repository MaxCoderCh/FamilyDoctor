package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.entity.Contract;
import com.familydoctor.doctorsubject.service.ContractService;
import com.familydoctor.doctorsubject.service.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 医生查询患者列表
 */
@Slf4j
@RestController
@RequestMapping(value = "familydoctor/contract")
public class ContractController extends BaseController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private ProduceService produceService;

    /**
     * 查询一个医生的所有患者
     *
     * @param contractId
     * @return requestSelectSuccess(contractsList)
     */
    @GetMapping(value = "selectByDoctor")
    public Map selectByDoctor(String contractId) {

        //获取ProduceId
        String produceId = contractService.selectById(contractId).getProduceId();//OK

        //通过ProduceId获取对应的DoctoreId
        String doctorId = produceService.selectById(produceId).getDoctorId();//OK

        //由DoctoreId查询Produce列表,并获取ProduceId列表
        List<String> produceIdList = produceService.selectByDoctorToId(doctorId);//OK

        //查询患者列表
        List<Contract> contractsList = contractService.selectByDoctorId(produceIdList);
        log.info(String.valueOf(contractsList));

        if (contractsList == null || contractsList.isEmpty()) {
            return requestSelectFail("查询失败");
        }

        return requestSelectSuccess(contractsList);

    }

}
