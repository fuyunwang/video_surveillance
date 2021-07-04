package com.fuyunwang.chuoyue.system.controller;

import com.fuyunwang.chuoyue.common.base.ResponseResult;
import com.fuyunwang.chuoyue.system.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "/file",description = "文件操作",protocols = "http")
@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传",notes = "上传文件",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁⽌访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "请求参数不正确")
    })
    @PostMapping(value = "/upload",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult uploadFile(@RequestParam("idcardimg") MultipartFile file){
        String path=fileService.uploadIdCardImage(file);
        if (path != null) {
             return ResponseResult.createBySuccess("文件上传成功",path);
        }
        return ResponseResult.createByError("文件上传失败");
    }

}
