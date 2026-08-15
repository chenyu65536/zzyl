package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.MealOrderDto;
import com.zzyl.service.MealOrderService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.vo.MealOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订餐管理
 */
@RestController
@RequestMapping("/meal-order")
@Api(tags = "订餐管理")
public class MealOrderController extends BaseController {

    @Autowired
    private MealOrderService mealOrderService;

    @PostMapping
    @ApiOperation("新增订餐")
    public ResponseResult add(@Validated @RequestBody MealOrderDto dto) {
        mealOrderService.add(dto);
        return success();
    }

    @PutMapping("/{id}/send")
    @ApiOperation("送餐完成")
    public ResponseResult send(@PathVariable Long id,
                               @RequestParam Long staffId,
                               @RequestParam(required = false) String deliverTime) {
        mealOrderService.send(id, staffId,
                ObjectUtil.isEmpty(deliverTime) ? null : LocalDateTime.parse(deliverTime));
        return success();
    }

    @PutMapping("/{id}/dine")
    @ApiOperation("用餐打卡")
    public ResponseResult dine(@PathVariable Long id) {
        mealOrderService.dine(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询订餐详情")
    public ResponseResult<MealOrderVo> findById(@PathVariable Long id) {
        return success(mealOrderService.findById(id));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询订餐")
    public ResponseResult<PageResponse<MealOrderVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                                @RequestParam(defaultValue = "10") int pageSize,
                                                                @RequestParam(required = false) String elderName,
                                                                @RequestParam(required = false) Integer status,
                                                                @RequestParam(required = false) String startDate,
                                                                @RequestParam(required = false) String endDate) {
        return success(mealOrderService.findByPage(pageNum, pageSize, elderName, status,
                ObjectUtil.isEmpty(startDate) ? null : LocalDate.parse(startDate),
                ObjectUtil.isEmpty(endDate) ? null : LocalDate.parse(endDate)));
    }
}
