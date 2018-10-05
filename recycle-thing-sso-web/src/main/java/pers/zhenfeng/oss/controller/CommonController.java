package pers.zhenfeng.oss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.NumberUtil;
import pers.zhenfeng.oss.util.CommonUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Grow-Worm
 * @date 2018/09/29
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/upload")
    public BaseResult<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {

        System.out.println(multipartFile.getName());
        String fileSuffix = ".jpeg";

        try {
            InputStream inputStream = multipartFile.getInputStream();

            String filePath = CommonUtil.uploadFile(inputStream, NumberUtil.generateImageName() + fileSuffix);

            return BaseResultUtil.success(filePath);

        } catch (IOException e) {
            return BaseResultUtil.fail("上传失败");
        }
    }
}
