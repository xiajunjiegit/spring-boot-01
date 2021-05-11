package com.atguigu.controller;

import com.atguigu.util.BarCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * Created by daijie on 2019/9/5
 * 条形码接口
 */
@RestController
@RequestMapping("/barCode")
public class BarCodeController {

    @RequestMapping("/getBarCode")
    public void getBarCode(String code,HttpServletResponse response) throws Exception{
        BufferedImage image = BarCodeUtil.insertWords(BarCodeUtil.getBarCode(code), code);
        ImageIO.write(image, "jpg", response.getOutputStream());
        //String message, Double height, Double width, boolean withQuietZone, boolean hideText
    }

}
