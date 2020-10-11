package com.fuyunwang.chuoyue.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.dto.DeviceDto;
import com.fuyunwang.chuoyue.system.entity.Device;
import com.fuyunwang.chuoyue.system.service.IDeviceService;
import com.fuyunwang.chuoyue.system.vo.DeviceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FuyunWang
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private IDeviceService iDeviceService;

    @RequestMapping(value="/getbypage",method = RequestMethod.POST)
    public ResponseResult<IPage<Device>> getDevicesByPage(@RequestParam("pagenum")Integer pagenum, @RequestParam("pagesize")Integer pagesize){
        IPage<Device> deviceIPage=iDeviceService.getDevicesByPage(pagenum,pagesize);
        return ResponseResult.createBySuccess(deviceIPage);
    }

    @RequestMapping(value = "/change_state",method = RequestMethod.POST)
    public ResponseResult<DeviceVo> changeAlgorithmState(@Valid @RequestBody DeviceDto deviceDto){
        DeviceVo deviceVo=iDeviceService.changeAlgorithmState(deviceDto);
        return ResponseResult.createBySuccess("算法状态修改成功",deviceVo);
    }
}
