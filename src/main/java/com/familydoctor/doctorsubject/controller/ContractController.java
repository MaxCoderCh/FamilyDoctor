package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.entity.Contract;
import com.familydoctor.doctorsubject.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 合同接口(familydoctor/contract),sign接口中创建
 * 含有医生查询其合同(selectbydoctor)
 */
@Slf4j
@RestController
@RequestMapping(value = "familydoctor/contract")
public class ContractController extends BaseController {

    @Autowired
    private ContractService contractService;

    @PostMapping(value = "add")
    public Map addContract(Contract contract) {
        if (contract == null) {
            return requestArgumentEmpty("contract为空");
        }

        contract.setCreateUser(getCurrentUser());
        int i = contractService.insertContract(contract);
        if (i < 1) {
            return requestInsertFail("添加Contract失败");
        }

        return requestInsertSuccess("添加成功");
    }

    /**
     * 查询一个医生的所有contract
     *
     * @param
     * @return requestSelectSuccess(contractsList)
     */
    @GetMapping(value = "selectbydoctor")
    public Map selectByDoctor() {


        Contract contract = new Contract();
        contract.setCreateUser(getCurrentUser());
        List<Contract> contractsList = contractService.selectParm(contract);

        if (contractsList == null || contractsList.isEmpty()) {
            return requestSelectFail("查询失败");
        }

        return requestSelectSuccess(contractsList);

    }

}
