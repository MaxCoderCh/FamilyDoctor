package com.familydoctor.doctorsubject.controller;


import com.familydoctor.doctorsubject.entity.Branch;
import com.familydoctor.doctorsubject.service.BranchService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * 科室接口,含有添加(add),由Id查询(selectbyid),更新(update),删除(softdelete),由hospitalid查询(selective)
 */
@Slf4j
@RestController
@RequestMapping(value = "familydoctor/branch")
public class BranchController extends BaseController {

    @Autowired
    private BranchService branchService;

    /**
     * 添加科室信息
     *
     * @param branch
     * @return requestInsertSuccess(branch)
     */
    @PostMapping(value = "add")
    public Map addBranchHospitalId(Branch branch) {
        if (branch == null) {
            return requestArgumentEmpty("传入数据为空");
        }

        branch.setCreateUser(getCurrentUser());
        int i = branchService.addBranch(branch);
        if (i > 0) {
            return requestInsertSuccess(branch);
        } else {
            return requestInsertFail("添加branch失败");
        }

    }


    /**
     * 由Branch.id查询对应科室信息
     *
     * @param branch
     * @return requestSelectSuccess(branch)
     */
    @GetMapping(value = "selectbyid")
    public Map selectBranchById(Branch branch) {

        if (StringUtils.isBlank(branch.getId())) {
            return requestArgumentEmpty("传入branch.id为空");
        }

        Branch resultBranch = branchService.selectBranch(branch.getId());
        if (resultBranch == null) {
            return requestSelectFail("查询结果为空");
        } else {
            return requestSelectSuccess(resultBranch);
        }

    }

    /**
     * 更新科室信息
     *
     * @param branch
     * @return requestUpdateSuccess(branch)
     */
    @PostMapping(value = "update")
    public Map updateBranchMessage(Branch branch) {

        if (StringUtils.isBlank(branch.getId())) {
            return requestArgumentEmpty("传入id为空");
        }

        branch.setUpdateTime(addTime());
        int i = branchService.updateBranch(branch);
        if (i > 0) {
            return requestUpdateSuccess(branch);
        } else {
            return requestUpdateFail("更新失败");
        }
    }

    /**
     * 删除科室信息
     *
     * @param branch
     * @return requestDeleteSuccess(branch)
     */
    @GetMapping(value = "softdelete")
    public Map softDelBranchMassage(Branch branch) {

        if (StringUtils.isBlank(branch.getId())) {
            return requestArgumentEmpty("传入id为空");
        }

        branch.setDeleteTime(addTime());
        int i = branchService.softDel(branch.getId());
        if (i > 0) {
            return requestDeleteSuccess(branch);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 物理删除科室信息
     *
     * @param branch
     * @return requestDeleteSuccess(branch)
     */
    @GetMapping(value = "delete")
    public Map deleteBranchMsg(Branch branch) {

        if (StringUtils.isBlank(branch.getId())) {
            return requestArgumentEmpty("传入id为空");
        }

        branch.setDeleteTime(addTime());
        int i = branchService.deleteById(branch.getId());
        if (i > 0) {
            return requestDeleteSuccess(branch);
        } else {
            return requestDeleteFail("删除失败");
        }

    }

    /**
     * 由所传hospitalId判断查询内容
     *
     * @param branch
     * @return requestSelectSuccess(listBranch)
     */
    @GetMapping(value = "selective")
    public Map selectAllBranchByHospitalId(Branch branch) {


        if (StringUtils.isBlank(branch.getHospitalId())) {
            //查询所有医院所有科室
            List<Branch> listBranch = branchService.selectAllBranch();
            if (listBranch != null && !listBranch.isEmpty()) {
                return requestSelectSuccess(listBranch);
            }
            return requestSelectFail("查询结果为空");
        }

        List<Branch> otherBranchList = branchService.selectByHospitalId(branch);
        if (otherBranchList == null) {
            return requestSelectFail("查询失败");
        } else {
            //查询挂号医院所有科室
            return requestSelectSuccess(otherBranchList);
        }
    }

}