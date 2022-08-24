package com.ruoyi.file.controller;

import com.ruoyi.file.config.ConfigProps;
import com.ruoyi.file.dto.DataDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.system.api.domain.SysFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件请求处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/api/files")
@Api(tags = "下载上传")
public class SysFileController
{
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private ISysFileService sysFileService;
    @Autowired
    private ConfigProps configProps;
    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    @PostMapping(value ="/doUpload")
    @ApiOperation("上传文件")
    public DataDto<Integer> postFile(@RequestParam(value="file",required=true) MultipartFile file) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhss");
        String nowDate = sdf.format(date);
        String guid= UUID.randomUUID().toString();
        String fileName=nowDate+guid+file.getOriginalFilename();
        System.out.println("fileName"+fileName);
        org.apache.commons.io.FileUtils.copyToFile(file.getInputStream(),
                new File(configProps.getName() + fileName));
        DataDto dto = new DataDto();
        dto.success("文件上传成功",fileName,configProps.getName()+fileName);
        return dto;
    }
    @GetMapping("/showImg")
    @ApiOperation("查看图片")
    public void showImg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String imgPath = req.getParameter("imgPath");
        BufferedImage image = ImageIO.read(new FileInputStream("/data/img/" + imgPath));
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "png", out);
    }

    @GetMapping("/showTxt")
    @ApiOperation("查看文件")
    public void showTxt(String imgPath, HttpServletResponse response) throws IOException {
        String path ="/data/img/" + imgPath;
        File file=new File(path);
        System.out.println("绝对路径=="+file.getAbsolutePath());
        try {
            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(in), out);
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/doDownload")
    public void doDownload(HttpServletResponse resp,HttpServletRequest req) throws Exception {
        String fileName=req.getParameter("fileName");
        System.out.println("fileName===="+fileName);
        // 1.设置文件是以流的形式发送数据
        resp.setContentType("application/octet-stream");
        // 2.告诉前端浏览器，这个文件用来下载，并且文件名是xxx
        resp.addHeader("Content-Disposition", "attachment; filename="
                + URLEncoder.encode(fileName, "UTF-8"));
        File f = new File(configProps.getName() + fileName);
        org.apache.commons.io.FileUtils.copyFile(f, resp.getOutputStream());
    }

}