package com.fuyunwang.chuoyue.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyunwang.chuoyue.system.entity.TbDevice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-19
 */
public interface ITbDeviceService extends IService<TbDevice> {
    IPage<TbDevice> getDevicesByPage(Integer pagenum, Integer pagesize);
}
