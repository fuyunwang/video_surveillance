package com.fuyunwang.chuoyue.system.service.impl;

import com.fuyunwang.chuoyue.common.utils.FastDfsClientUtil;
import com.fuyunwang.chuoyue.system.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadIdCardImage(MultipartFile file) {
        try {
            String path= FastDfsClientUtil.saveFile(file);
            if (StringUtils.isNotBlank(path)){
                return path;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
