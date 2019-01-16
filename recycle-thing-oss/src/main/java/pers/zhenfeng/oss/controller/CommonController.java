package pers.zhenfeng.oss.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.zhenfeng.api.bo.CommodityTypeBO;
import pers.zhenfeng.api.service.CommonService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.core.util.NumberUtil;
import pers.zhenfeng.oss.util.CommonUtil;
import pers.zhenfeng.oss.vo.CommodityTypeVO;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Grow-Worm
 * @date 2018/09/29
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private CommonService commonService;

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

    @RequestMapping("/getAllCommodityType")
    public BaseResult<List<CommodityTypeVO>> getAllCommodityTypeVO() {
        BaseResult<List<CommodityTypeBO>> baseResult = commonService.getAllCommodityType();
        if (BaseResultUtil.isFail(baseResult)) {
            return BaseResultUtil.fail(baseResult.getMsg());
        }

        List<CommodityTypeBO> commodityTypeBOS = baseResult.getData();
        if (CollectionUtils.isEmpty(commodityTypeBOS)) {
            return BaseResultUtil.success();
        }

        List<CommodityTypeVO> commodityTypeVOS = Lists.newArrayList();
        commodityTypeBOS.forEach(commodityTypeBO -> {
            CommodityTypeVO commodityTypeVO = new CommodityTypeVO();
            BeanUtils.copyProperties(commodityTypeBO, commodityTypeVO);
            commodityTypeVOS.add(commodityTypeVO);
        });

        return BaseResultUtil.success(commodityTypeVOS);
    }

    @RequestMapping("/insertCommodityType")
    public BaseResult<Integer> insertCommodityType(@RequestBody CommodityTypeVO commodityTypeVO) {
        CommodityTypeBO commodityTypeBO = new CommodityTypeBO();

        BeanUtils.copyProperties(commodityTypeVO, commodityTypeBO);

        BaseResult<Integer> baseResult = commonService.insertCommodityType(commodityTypeBO);
        if (BaseResultUtil.isFail(baseResult)) {
            return BaseResultUtil.fail(baseResult.getMsg());
        }

        return BaseResultUtil.success(baseResult.getData());
    }

    @RequestMapping("deleteCommodityType")
    public BaseResult<Integer> deleteCommodityType(@RequestParam("id") Integer id) {
        return commonService.deleteCommodityType(id);
    }
}
