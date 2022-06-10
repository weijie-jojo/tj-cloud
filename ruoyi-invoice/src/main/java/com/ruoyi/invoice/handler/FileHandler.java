package com.ruoyi.invoice.handler;



import com.ruoyi.invoice.annotation.Log;
import com.ruoyi.invoice.config.ConfigProps;
import com.ruoyi.invoice.dto.DataDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@Api(tags = "下载上传")
@RequestMapping("/api/files")
public class FileHandler {
    @Resource
    private ConfigProps configProps;

    @PostMapping(value ="/doUpload")
    @Log("上传文件")
    @ApiOperation("上传文件")
    public DataDto<Integer> postFile(@RequestParam(value="file",required=true) MultipartFile file) throws IOException {
        System.out.println(111);
        FileUtils.copyToFile(file.getInputStream(),
                new File(configProps.getName() + file.getOriginalFilename()));
        DataDto dto = new DataDto();
        dto.success("文件上传成功",file.getOriginalFilename());
        return dto;
    }

    @GetMapping("/showImg")
    @Log("查看图片")
    @ApiOperation("查看图片")
    public void showImg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String imgPath = req.getParameter("imgPath");
        BufferedImage image = ImageIO.read(new FileInputStream("/data1/img/" + imgPath));
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "png", out);
    }
//    @GetMapping("/doDownload")
//    public void doDownload(HttpServletResponse resp,HttpServletRequest req) throws Exception {
//        String fileName=req.getParameter("fileName");
//        System.out.println("fileName===="+fileName);
//        // 1.设置文件是以流的形式发送数据
//        resp.setContentType("application/octet-stream");
//        // 2.告诉前端浏览器，这个文件用来下载，并且文件名是xxx
//        resp.addHeader("Content-Disposition", "attachment; filename="
//                + URLEncoder.encode(fileName, "UTF-8"));
//        File f = new File(configProps.getName() + fileName);
//        FileUtils.copyFile(f, resp.getOutputStream());
//    }

}
