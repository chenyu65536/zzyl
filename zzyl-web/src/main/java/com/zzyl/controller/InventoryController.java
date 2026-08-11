package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.service.InventoryService;
import com.zzyl.vo.InventoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 库存管理(库存盘点/低库存预警)
 */
@RestController
@RequestMapping("/inventory")
@Api(tags = "库存管理")
public class InventoryController extends BaseController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/page")
    @ApiOperation("分页查询库存汇总(库存盘点)")
    public ResponseResult<PageResponse<InventoryVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                                @RequestParam(defaultValue = "10") int pageSize,
                                                                @RequestParam(required = false) String materialName,
                                                                @RequestParam(required = false) Long typeId,
                                                                @RequestParam(required = false) Long warehouseId) {
        return success(inventoryService.findByPage(pageNum, pageSize, materialName, typeId, warehouseId));
    }

    @GetMapping("/warning")
    @ApiOperation("低库存预警列表")
    public ResponseResult<List<InventoryVo>> findWarningList() {
        return success(inventoryService.findWarningList());
    }
}
