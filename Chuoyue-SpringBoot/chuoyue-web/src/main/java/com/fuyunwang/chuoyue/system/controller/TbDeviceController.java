package com.fuyunwang.chuoyue.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.common.base.GlobalConstant;
import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.common.exception.ParamException;
import com.fuyunwang.chuoyue.system.dto.DeviceDto;
import com.fuyunwang.chuoyue.system.entity.TbDevice;
import com.fuyunwang.chuoyue.system.service.ITbDeviceService;
import com.fuyunwang.chuoyue.system.vo.DeviceVo;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/tb-device")
public class TbDeviceController {
    @Autowired
    private ITbDeviceService iDeviceService;

    @RequestMapping(value="/getbypage",method = RequestMethod.POST)
    public ResponseResult<IPage<TbDevice>> getDevicesByPage(@RequestParam("pagenum")Integer pagenum, @RequestParam("pagesize")Integer pagesize){
        IPage<TbDevice> deviceIPage=iDeviceService.getDevicesByPage(pagenum,pagesize);
        return ResponseResult.createBySuccess(deviceIPage);
    }

    @RequestMapping(value = "/change_state",method = RequestMethod.POST)
    public ResponseResult<DeviceVo> changeAlgorithmState(@Valid @RequestBody DeviceDto deviceDto){
        DeviceVo deviceVo=iDeviceService.changeAlgorithmState(deviceDto);
        return ResponseResult.createBySuccess("算法状态修改成功",deviceVo);
    }
}
