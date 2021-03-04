package com.ruoyi.web.controller.position;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.enums.NationalityEnum;
import com.ruoyi.common.utils.ListCountry;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.position.domain.Position;
import com.ruoyi.position.service.IPositionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人力资源Controller
 *
 * @author anan
 * @date 2021-03-03
 */
@RestController
@RequestMapping("/position/position")
public class PositionController extends BaseController {
    @Autowired
    private IPositionService positionService;

        /**
         * 查询人力资源列表
         */
        @PreAuthorize("@ss.hasPermi('position:position:list')")
        @GetMapping("/list")
        public TableDataInfo list(Position position) {
        startPage();
        List<Position> list = positionService.selectPositionList(position);
        return getDataTable(list);
        }
    
    /**
     * 导出人力资源列表
     */
    @PreAuthorize("@ss.hasPermi('position:position:export')")
    @Log(title = "人力资源", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Position position) {
        List<Position> list = positionService.selectPositionList(position);
        ExcelUtil<Position> util = new ExcelUtil<Position>(Position. class);
        return util.exportExcel(list, "position");
    }

    /**
     * 获取人力资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('position:position:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(positionService.selectPositionById(id));
    }

    /**
     * 新增人力资源
     */
    @PreAuthorize("@ss.hasPermi('position:position:add')")
    @Log(title = "人力资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(Position position) {
        if (position != null){
            position.setIsDelete("0");
            position.setCreateTime(new Date());
            position.setFileNumber(this.fileNumber(position.getName()));
            position.setRegistrant(SpringUtils.getUserName());
            return toAjax(positionService.insertPosition(position));
        }
        return AjaxResult.success("数据为空，添加不上");
    }

    /**
     * 修改人力资源
     */
    @PreAuthorize("@ss.hasPermi('position:position:edit')")
    @Log(title = "人力资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Position position) {
        position.setIsDelete("1");
        return toAjax(positionService.updatePosition(position));
    }
    /**
     * 批量修改人力资源
     */
    @PreAuthorize("@ss.hasPermi('position:position:edit')")
    @Log(title = "人力资源", businessType = BusinessType.UPDATE)
    @DeleteMapping("/upateStatus/{ids}")
    public AjaxResult upateStatus(@PathVariable Long[] ids) {
        return toAjax(positionService.updateByIds(ids));
    }

    /**
     * 删除人力资源
     */
    @PreAuthorize("@ss.hasPermi('position:position:remove')")
    @Log(title = "人力资源", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(positionService.deletePositionByIds(ids));
    }

    /**
     * 组件查询
     * @return
     */
    @GetMapping("/getCountry")
    public AjaxResult getCountry(){
        List<Map<String, Object>> country = ListCountry.getCountry();
        List<Map<String, Object>> nationalityList = NationalityEnum.getNationalityList();
        Map<String,Object> map = new HashMap<>();
        map.put("country",country);
        map.put("nationality",nationalityList);
        return AjaxResult.success(map);
    }

    /**
     * 查询待复核的档案
     * @return
     */
    public TableDataInfo selectPositions(){
        startPage();
        System.out.println("ananan");
        List<Position> positionList = positionService.selectPositions();
        return getDataTable(positionList);
    }
}
