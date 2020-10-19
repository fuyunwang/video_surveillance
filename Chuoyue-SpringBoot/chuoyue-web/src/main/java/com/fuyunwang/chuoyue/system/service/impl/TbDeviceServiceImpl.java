package com.fuyunwang.chuoyue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyunwang.chuoyue.common.base.GlobalConstant;
import com.fuyunwang.chuoyue.common.exception.ParamException;
import com.fuyunwang.chuoyue.system.dto.DeviceDto;
import com.fuyunwang.chuoyue.system.entity.TbDevice;
import com.fuyunwang.chuoyue.system.mapper.TbDeviceMapper;
import com.fuyunwang.chuoyue.system.service.ITbDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyunwang.chuoyue.system.vo.DeviceVo;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuyunWang
 * @since 2020-10-19
 */
@Slf4j
@Service
public class TbDeviceServiceImpl extends ServiceImpl<TbDeviceMapper, TbDevice> implements ITbDeviceService {

    @Autowired
    private TbDeviceMapper tbDeviceMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public IPage<TbDevice> getDevicesByPage(Integer pagenum, Integer pagesize) {
        Page<TbDevice> page=new Page<>(pagenum,pagesize);
        List authorities = (List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (CollectionUtils.isNotEmpty(authorities)){
            String roleName = (String) ((GrantedAuthority)(authorities.get(0))).getAuthority();
            if (roleName.equals("admin")){
                IPage<TbDevice> departmentIPage = tbDeviceMapper.selectPage(page, new QueryWrapper<>());
                return departmentIPage;
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public DeviceVo changeAlgorithmState(DeviceDto deviceDto) {
        TbDevice device = tbDeviceMapper.selectById(deviceDto.getId());
        if (device==null){
            throw new ParamException("所选设备算法不存在,算法启用失败");
        }
        String selectedDevice = (String) redisTemplate.opsForValue().get(GlobalConstant.Algorithm.SELECTED_ALGORITHM);
        log.info(Joiner.on("").join(GlobalConstant.Algorithm.ALGORITHM_PREFIX,deviceDto.getId()));
        if (StringUtils.isNotBlank(selectedDevice)&&!StringUtils.equals(selectedDevice,
                Joiner.on("").join(GlobalConstant.Algorithm.ALGORITHM_PREFIX,deviceDto.getId()))){
            throw new ParamException("当前已经选择了一种检测算法,请关闭原有算法重试");
        }
        if (deviceDto.getState()==1){
            redisTemplate.opsForValue().set(GlobalConstant.Algorithm.SELECTED_ALGORITHM,GlobalConstant.Algorithm.ALGORITHM_PREFIX+deviceDto.getId());
        }else{
            redisTemplate.delete(GlobalConstant.Algorithm.SELECTED_ALGORITHM);
        }
        device.setState(deviceDto.getState());
        int i = tbDeviceMapper.updateById(device);
        DeviceVo deviceVo=new DeviceVo();
        BeanUtils.copyProperties(device,deviceVo);
        return deviceVo;
    }
}
