package com.familydoctor.doctorsubject.controller;

import com.familydoctor.doctorsubject.entity.Frequency;
import com.familydoctor.doctorsubject.service.FrequencyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 给药(familydoctor/frequency)
 * 添加(add),修改(update),删除(softdelete),查询(selectall),id查询(selectbyid),分页(selectpage)
 */
@RestController
@RequestMapping(value = "familydoctor/frequency")
public class FrequencyController extends BaseController {

    @Autowired
    private FrequencyService frequencyService;

    /**
     * 添加给药
     *
     * @param frequency
     */
    @PostMapping(value = "add")
    public Map AddFFrequencyMsg(Frequency frequency) {

        if (frequency == null) {
            return requestArgumentEmpty("参数为空");
        }

        int i = frequencyService.addFrequency(frequency);
        if (i > 0) {
            return requestInsertSuccess("添加成功");
        }
        return requestInsertFail("添加失败");
    }


    /**
     * 物理删除给药
     *
     * @param frequency
     */
    @GetMapping(value = "delete")
    public Map deleteFrequencyMsg(Frequency frequency) {

        if (StringUtils.isBlank(frequency.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        int i = frequencyService.deleteById(frequency);
        if (i > 0) {
            return requestDeleteSuccess("已成功删除");
        }
        return requestDeleteFail("删除失败");
    }

    /**
     * 罗逻辑删除给药
     *
     * @param frequency
     */
    @GetMapping(value = "softDel")
    public Map softDelFrequency(Frequency frequency) {

        if (StringUtils.isBlank(frequency.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        frequency.setDeleteTime(addTime());
        int i = frequencyService.softDel(frequency);
        if (i > 0) {
            return requestDeleteSuccess("删除成功");
        }
        return requestDeleteFail("删除失败");
    }

    /**
     * 修改给药
     *
     * @param frequency
     */
    @PostMapping(value = "update")
    public Map updateFrequencyMsg(Frequency frequency) {

        if (StringUtils.isBlank(frequency.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        frequency.setUpdateTime(addTime());
        int i = frequencyService.updateById(frequency);
        if (i > 0) {
            return requestUpdateSuccess(frequency);
        }
        return requestUpdateFail("更新失败");
    }


    /**
     * 由Id查询对应的给药
     *
     * @param frequency
     */
    @GetMapping(value = "selectbyid")
    public Map selectFrequencyMsg(Frequency frequency) {

        if (StringUtils.isBlank(frequency.getId())) {
            return requestArgumentEmpty("参数为空");
        }

        Frequency resultFrequency = frequencyService.selectById(frequency);
        if (resultFrequency != null) {
            return requestSelectSuccess(resultFrequency);
        }
        return requestSelectFail("查询失败");
    }


    /**
     * 查询给药列表
     */
    @GetMapping(value = "selectall")
    public Map selectAllFrequency() {

        List<Frequency> frequencyList = frequencyService.selectAll();
        if (frequencyList != null && !frequencyList.isEmpty()) {
            return requestSelectSuccess(frequencyList);
        }
        return requestSelectFail("查询失败");
    }


    /**
     * 分页查询
     *
     * @param frequency
     */
    @GetMapping(value = "selectPage")
    public Map selectPage(Frequency frequency) {

        List<Frequency> frequencyList = frequencyService.selectPage(frequency);

        if (frequencyList != null && !frequencyList.isEmpty()) {
            return requestSelectSuccess(frequencyList);
        }

        return requestSelectFail("查询失败");
    }

}
