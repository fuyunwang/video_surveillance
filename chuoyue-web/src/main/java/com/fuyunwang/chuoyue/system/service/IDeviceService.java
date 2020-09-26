package com.fuyunwang.chuoyue.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.system.entity.Device;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-09-25
 */
public interface IDeviceService extends IService<Device> {
    IPage<Device> getDevicesByPage(Integer pagenum, Integer pagesize);
}
