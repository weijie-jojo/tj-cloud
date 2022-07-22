package com.ruoyi.company.controller;

import com.google.code.appengine.awt.Color;
import com.lowagie.text.Font;
import com.ruoyi.company.config.ConfigProps;
import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.service.ISelfEmployedService;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@Api(tags = "文件管理")
public class WordExportController {
    @Resource
    private ConfigProps configProps;

    @Resource
    private ISelfEmployedService selfEmployedService;

    @GetMapping("/showTxt")
    @ApiOperation("查看文件")
    public List<String> showTxt(String imgPath, HttpServletResponse resp) throws IOException {
//        String imgPath = req.getParameter("imgPath");
//        FileInputStream txt = new FileInputStream(configProps.getName() + imgPath);
        File file=new File(configProps.getName() + imgPath);
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing file.", e);
        }
        return list;
    }

    private void setTableText(XWPFDocument docxDocument) {
        //获取第一个表格
        XWPFTable table = docxDocument.getTableArray(0);
        List<XWPFTableRow> rows = table.getRows();
        int i=1;
        for(XWPFTableRow row :rows){
            List<XWPFTableCell> cells = row.getTableCells();
            for(XWPFTableCell cell: cells){
                cell.setText("第"+String.valueOf(i++)+"格");
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                //cell.setWidthType(TableWidthType.PCT);
                //cell.setWidth("30%");
            }
        }
    }
    public static CTTcPr getCellCTTcPr(XWPFTableCell cell) {
        CTTc cttc = cell.getCTTc();
        CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
        return tcPr;
    }


    @PostMapping(value = "/getWord")
    @ApiOperation("下载工商登记申请书")
    public String getWord(String selfId,String selfCode) throws Exception {
        //生成文件名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhss");
        String nowDate = sdf.format(date);
        String guid= UUID.randomUUID().toString();
        String fileName=nowDate+guid+selfCode+"createTable.docx";

        //Blank Document
        XWPFDocument docxDocument = new XWPFDocument();
        File file=new File(configProps.getName()+fileName);
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(file);

        //创建第一段落
        XWPFParagraph firstParagraphX = docxDocument.createParagraph();
        firstParagraphX.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun runTitle = firstParagraphX.createRun();
        runTitle.setText("个体工商户登记（备案）申请书");
        runTitle.setBold(true);
        runTitle.setFontSize(24);
        runTitle.setFontFamily("宋体");
        runTitle.addCarriageReturn();//回车键
        runTitle.setKerning(30);

        XWPFParagraph paragraphX = docxDocument.createParagraph();

        paragraphX.setAlignment(ParagraphAlignment.LEFT);//对齐方式
        paragraphX.setFirstLineIndent(400);//首行缩进
        //创建段落中的run
        XWPFRun run = paragraphX.createRun();
        run.setText("开始新的额一页了健康卡离开了危，机容量为金融界王仁君我快速建房可谓集，有分页吗，按时交付问我问问");
        //run.addCarriageReturn();//回车键

        XWPFRun run2 = paragraphX.createRun();
        run2.setText("这是第二段了吧，接口了就废了我今儿来将危及，不知道嗯么回事了了，啦啦啦啦啦啦啦");
        run2.setText("这个不是能分段吗，测试一下试试");
        run2.setBold(true);//加粗

        //创建第二段落
        XWPFParagraph paragraphX2 = docxDocument.createParagraph();
        paragraphX2.setIndentationFirstLine(420);//首行缩进
        XWPFRun secondRun = paragraphX2.createRun();
        secondRun.setText("第二天的开始，就忙吧尽快立法捡垃圾而");
        secondRun.setColor("FFC0CB");
        secondRun.setUnderline(UnderlinePatterns.SINGLE);
        secondRun.addCarriageReturn();

        //创建表格 4行*5列(创建table 时，会有一个默认一行一列的表格)
        // XWPFTable table =  createTable( docxDocument,4,5);
        XWPFTable table = docxDocument.createTable(4,5);
        table.setWidth("95%");
        table.setWidthType(TableWidthType.PCT);//设置表格相对宽度
        table.setTableAlignment(TableRowAlign.CENTER);

        //合并单元格
        XWPFTableRow row1 = table.getRow(0);
        XWPFTableCell cell1 = row1.getCell(0);
        CTTcPr cellCtPr = getCellCTTcPr(cell1);
        cellCtPr.addNewHMerge().setVal(STMerge.RESTART);

        XWPFTableCell cell2 = row1.getCell(1);
        CTTcPr cellCtPr2 = getCellCTTcPr(cell2);
        cellCtPr2.addNewHMerge().setVal(STMerge.CONTINUE);

        //给表格填充文本
        setTableText(docxDocument);

//        //创建一个表格，并指定宽度
//        XWPFTable table = doc.createTable(4, 4);
//        TableTools.widthTable(table, MiniTableRenderData.WIDTH_A4_FULL, 4);
//        //设置第0行数据
//        List<XWPFTableCell> row0 = table.getRow(0).getTableCells();
//        row0.get(0).setText("xxxx"); //为第0行第0列设置内容
//        row0.get(0).setWidth("200");
//        row0.get(1).setText("aaaa");
//        row0.get(2).setText("bbbb");
//        row0.get(3).setText("cccc");


//        //添加标题
//        XWPFParagraph titleParagraph = document.createParagraph();
//        //设置段落居中
//        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
//
//        XWPFRun titleParagraphRun = titleParagraph.createRun();
//        titleParagraphRun.setText("Java PoI 测试");
//        titleParagraphRun.setColor("000000");
//        titleParagraphRun.setFontSize(20);


//        //段落
//        XWPFParagraph firstParagraph = document.createParagraph();
//        XWPFRun run = firstParagraph.createRun();
//        run.setText("Java POI 生成word文件。");
//        run.setColor("696969");
//        run.setFontSize(16);
//
//        //设置段落背景颜色
//        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
//        cTShd.setVal(STShd.CLEAR);
//        cTShd.setFill("97FFFF");
//
//
//        //换行
//        XWPFParagraph paragraph1 = document.createParagraph();
//        XWPFRun paragraphRun1 = paragraph1.createRun();
//        paragraphRun1.setText("\r");
//
//
//        //基本信息表格
//        XWPFTable infoTable = document.createTable();
//        //去表格边框
//        infoTable.getCTTbl().getTblPr().unsetTblBorders();
//
//
//        //列宽自动分割
//        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
//        infoTableWidth.setType(STTblWidth.DXA);
//        infoTableWidth.setW(BigInteger.valueOf(9072));
//
//
//        //表格第一行
//        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
//        infoTableRowOne.getCell(0).setText("职位");
//        infoTableRowOne.addNewTableCell().setText(": Java 开发工程师");
//
//        //表格第二行
//        XWPFTableRow infoTableRowTwo = infoTable.createRow();
//        infoTableRowTwo.getCell(0).setText("姓名");
//        infoTableRowTwo.getCell(1).setText(": seawater");
//
//        //表格第三行
//        XWPFTableRow infoTableRowThree = infoTable.createRow();
//        infoTableRowThree.getCell(0).setText("生日");
//        infoTableRowThree.getCell(1).setText(": xxx-xx-xx");
//
//        //表格第四行
//        XWPFTableRow infoTableRowFour = infoTable.createRow();
//        infoTableRowFour.getCell(0).setText("性别");
//        infoTableRowFour.getCell(1).setText(": 男");
//
//        //表格第五行
//        XWPFTableRow infoTableRowFive = infoTable.createRow();
//        infoTableRowFive.getCell(0).setText("现居地");
//        infoTableRowFive.getCell(1).setText(": xx");
//
//
//        //两个表格之间加个换行
//        XWPFParagraph paragraph = document.createParagraph();
//        XWPFRun paragraphRun = paragraph.createRun();
//        paragraphRun.setText("\r");
//
//
//
//        //工作经历表格
//        XWPFTable ComTable = document.createTable();
//
//
//        //列宽自动分割
//        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
//        comTableWidth.setType(STTblWidth.DXA);
//        comTableWidth.setW(BigInteger.valueOf(9072));
//
//        //表格第一行
//        XWPFTableRow comTableRowOne = ComTable.getRow(0);
//        comTableRowOne.getCell(0).setText("开始时间");
//        comTableRowOne.addNewTableCell().setText("结束时间");
//        comTableRowOne.addNewTableCell().setText("公司名称");
//        comTableRowOne.addNewTableCell().setText("title");
//
//        //表格第二行
//        XWPFTableRow comTableRowTwo = ComTable.createRow();
//        comTableRowTwo.getCell(0).setText("2016-09-06");
//        comTableRowTwo.getCell(1).setText("至今");
//        comTableRowTwo.getCell(2).setText("seawater");
//        comTableRowTwo.getCell(3).setText("Java开发工程师");
//
//        //表格第三行
//        XWPFTableRow comTableRowThree = ComTable.createRow();
//        comTableRowThree.getCell(0).setText("2016-09-06");
//        comTableRowThree.getCell(1).setText("至今");
//        comTableRowThree.getCell(2).setText("seawater");
//        comTableRowThree.getCell(3).setText("Java开发工程师");
//
//
//        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
//        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
//
//        //添加页眉
//        CTP ctpHeader = CTP.Factory.newInstance();
//        CTR ctrHeader = ctpHeader.addNewR();
//        CTText ctHeader = ctrHeader.addNewT();
//        String headerText = "Java POI create MS word file.";
//        ctHeader.setStringValue(headerText);
//        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
//        //设置为右对齐
//        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
//        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
//        parsHeader[0] = headerParagraph;
//        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
//
//
//        //添加页脚
//        CTP ctpFooter = CTP.Factory.newInstance();
//        CTR ctrFooter = ctpFooter.addNewR();
//        CTText ctFooter = ctrFooter.addNewT();
//        String footerText = "http://blog.csdn.net/zhouseawater";
//        ctFooter.setStringValue(footerText);
//        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
//        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
//        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
//        parsFooter[0] = footerParagraph;
//        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);

        //写到本地
        docxDocument.write(out);
        out.close();
        Thread.sleep(1000);

//        //word转成pdf
//        FileInputStream fileInputStream = null;
//        FileOutputStream  fileOutputStream=null;
//        try {
//            // 读取docx文件
//            fileInputStream = new FileInputStream(file.getAbsolutePath());
//            XWPFDocument doc = new XWPFDocument(fileInputStream);
////            doc.createStyles();
//            PdfOptions pdfOptions = PdfOptions.create();
//            // 输出路径
//            fileOutputStream= new FileOutputStream(new File(configProps+nowDate+guid+"转换结果.pdf"));
//            // 调用转换
//            PdfConverter.getInstance().convert(doc, fileOutputStream, pdfOptions);
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }finally {
//            fileInputStream.close();
//            fileOutputStream.close();
//        }

        //插入文件名到数据库
        SelfEmployed selfEmployed=new SelfEmployed();
        selfEmployed.setFileName8(fileName);
        selfEmployed.setSelfId(selfId);
        selfEmployedService.updateSelfEmployed(selfEmployed);
        return fileName;

    }

}
