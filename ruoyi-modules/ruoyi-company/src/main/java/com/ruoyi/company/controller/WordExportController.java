package com.ruoyi.company.controller;

import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.company.config.ConfigProps;
import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.dto.DataDto;
import com.ruoyi.company.service.ISelfEmployedService;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.Global;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@Api(tags = "文件下载")
public class WordExportController {
    @Resource
    private ConfigProps configProps;

    @Resource
    private ISelfEmployedService selfEmployedService;

    @PostMapping(value = "/getWord")
    @ApiOperation("下载工商登记申请书")
    public String getWord(String selfId,String selfCode) throws Exception {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhss");
        String nowDate = sdf.format(date);
        String guid= UUID.randomUUID().toString();
        String fileName=nowDate+guid+selfCode+"create_table.docx";
        System.out.println("fileName"+fileName);

        //Blank Document
        XWPFDocument document= new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(configProps.getName()+fileName));

        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("Java PoI");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);


        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("Java POI 生成word文件。");
        run.setColor("696969");
        run.setFontSize(16);

        //设置段落背景颜色
        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
        cTShd.setVal(STShd.CLEAR);
        cTShd.setFill("97FFFF");


        //换行
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");


        //基本信息表格
        XWPFTable infoTable = document.createTable();
        //去表格边框
        infoTable.getCTTbl().getTblPr().unsetTblBorders();


        //列宽自动分割
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));


        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("职位");
        infoTableRowOne.addNewTableCell().setText(": Java 开发工程师");

        //表格第二行
        XWPFTableRow infoTableRowTwo = infoTable.createRow();
        infoTableRowTwo.getCell(0).setText("姓名");
        infoTableRowTwo.getCell(1).setText(": seawater");

        //表格第三行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("生日");
        infoTableRowThree.getCell(1).setText(": xxx-xx-xx");

        //表格第四行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("性别");
        infoTableRowFour.getCell(1).setText(": 男");

        //表格第五行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("现居地");
        infoTableRowFive.getCell(1).setText(": xx");


        //两个表格之间加个换行
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("\r");



        //工作经历表格
        XWPFTable ComTable = document.createTable();


        //列宽自动分割
        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
        comTableWidth.setType(STTblWidth.DXA);
        comTableWidth.setW(BigInteger.valueOf(9072));

        //表格第一行
        XWPFTableRow comTableRowOne = ComTable.getRow(0);
        comTableRowOne.getCell(0).setText("开始时间");
        comTableRowOne.addNewTableCell().setText("结束时间");
        comTableRowOne.addNewTableCell().setText("公司名称");
        comTableRowOne.addNewTableCell().setText("title");

        //表格第二行
        XWPFTableRow comTableRowTwo = ComTable.createRow();
        comTableRowTwo.getCell(0).setText("2016-09-06");
        comTableRowTwo.getCell(1).setText("至今");
        comTableRowTwo.getCell(2).setText("seawater");
        comTableRowTwo.getCell(3).setText("Java开发工程师");

        //表格第三行
        XWPFTableRow comTableRowThree = ComTable.createRow();
        comTableRowThree.getCell(0).setText("2016-09-06");
        comTableRowThree.getCell(1).setText("至今");
        comTableRowThree.getCell(2).setText("seawater");
        comTableRowThree.getCell(3).setText("Java开发工程师");


        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "Java POI create MS word file.";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);


        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "http://blog.csdn.net/zhouseawater";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);

        document.write(out);
        out.close();

//        URL url = new URL("./"+configProps.getName()+fileName);
//        URLConnection connection = url.openConnection();
//        InputStream fileInputStream = connection.getInputStream();
////        InputStream fileInputStream=new InputStream(configProps.getName()+fileName);
//        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
//        PdfOptions pdfOptions = PdfOptions.create();
//        PdfConverter.getInstance().convert(xwpfDocument, out, pdfOptions);


        System.out.println("create_table document written success.");
        SelfEmployed selfEmployed=new SelfEmployed();
        selfEmployed.setFileName8(fileName);
        selfEmployed.setSelfId(selfId);
        selfEmployedService.updateSelfEmployed(selfEmployed);
        return fileName;

    }

    public static void main(String[] args) throws IOException {

        //Blank Document
        XWPFDocument document= new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("create_table.docx"));


        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("Java PoI");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);


        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("Java POI 生成word文件。");
        run.setColor("696969");
        run.setFontSize(16);

        //设置段落背景颜色
        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
        cTShd.setVal(STShd.CLEAR);
        cTShd.setFill("97FFFF");


        //换行
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");


        //基本信息表格
        XWPFTable infoTable = document.createTable();
        //去表格边框
        infoTable.getCTTbl().getTblPr().unsetTblBorders();


        //列宽自动分割
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));


        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("职位");
        infoTableRowOne.addNewTableCell().setText(": Java 开发工程师");

        //表格第二行
        XWPFTableRow infoTableRowTwo = infoTable.createRow();
        infoTableRowTwo.getCell(0).setText("姓名");
        infoTableRowTwo.getCell(1).setText(": seawater");

        //表格第三行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("生日");
        infoTableRowThree.getCell(1).setText(": xxx-xx-xx");

        //表格第四行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("性别");
        infoTableRowFour.getCell(1).setText(": 男");

        //表格第五行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("现居地");
        infoTableRowFive.getCell(1).setText(": xx");


        //两个表格之间加个换行
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("\r");



        //工作经历表格
        XWPFTable ComTable = document.createTable();


        //列宽自动分割
        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
        comTableWidth.setType(STTblWidth.DXA);
        comTableWidth.setW(BigInteger.valueOf(9072));

        //表格第一行
        XWPFTableRow comTableRowOne = ComTable.getRow(0);
        comTableRowOne.getCell(0).setText("开始时间");
        comTableRowOne.addNewTableCell().setText("结束时间");
        comTableRowOne.addNewTableCell().setText("公司名称");
        comTableRowOne.addNewTableCell().setText("title");

        //表格第二行
        XWPFTableRow comTableRowTwo = ComTable.createRow();
        comTableRowTwo.getCell(0).setText("2016-09-06");
        comTableRowTwo.getCell(1).setText("至今");
        comTableRowTwo.getCell(2).setText("seawater");
        comTableRowTwo.getCell(3).setText("Java开发工程师");

        //表格第三行
        XWPFTableRow comTableRowThree = ComTable.createRow();
        comTableRowThree.getCell(0).setText("2016-09-06");
        comTableRowThree.getCell(1).setText("至今");
        comTableRowThree.getCell(2).setText("seawater");
        comTableRowThree.getCell(3).setText("Java开发工程师");


        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "Java POI create MS word file.";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);


        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "http://blog.csdn.net/zhouseawater";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);

        PdfOptions pdfOptions = PdfOptions.create();

        document.write(out);
        out.close();
        System.out.println("create_table document written success.");
    }
}
