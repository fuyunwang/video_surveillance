package com.fuyunwang.chuoyue.system.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadIdCardImage(MultipartFile file);
}
