package com.zzyl.controller;

import com.zzyl.base.ResponseResult;
import com.zzyl.service.HomeService;
import com.zzyl.vo.HomeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机构看板(首页统计)
 */
@RestController
@RequestMapping("/home")
@Api(tags = "机构看板")
public class HomeController extends BaseController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/overview")
    @ApiOperation("机构看板总览(床位/房间/在住老人健康标识/今日用餐/今日护理/IOT设备告警)")
    public ResponseResult<HomeVo> overview() {
        return success(homeService.overview());
    }
}
