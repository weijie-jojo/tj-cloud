package com.ruoyi.company.controller;


import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.util.TableTools;
import com.ruoyi.company.config.ConfigProps;
import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.vo.SelfEmployedVo;
import com.ruoyi.company.service.ISelfEmployedService;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
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
    public void showTxt(String imgPath, HttpServletResponse response) throws IOException {
        System.out.println("111");
        // 设置编码
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(imgPath, "UTF-8"));
//        String path = configProps.getName() + imgPath;
        String path ="/data/img/" + imgPath;
        System.out.println("path=="+path);
        File file=new File(path);
        System.out.println("绝对路径=="+file.getAbsolutePath());
        try {
            FileInputStream in = new FileInputStream(file);
//            response.setHeader("Content-Type", "application/pdf");
            OutputStream out = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(in), out);
//            FileCopyUtils.copy(in, response.getOutputStream());
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        File file=new File(configProps.getName() + imgPath);
//        System.out.println("绝对路径=="+file.getAbsolutePath());
//        ArrayList<String> list = new ArrayList<>();
//        try (BufferedReader reader =
//                     new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                list.add(line);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Error parsing file.", e);
//        }
    }

//    private void setTableText(XWPFDocument docxDocument) {
//        //获取第一个表格
//        XWPFTable table = docxDocument.getTableArray(0);
//        List<XWPFTableRow> rows = table.getRows();
//        int i=1;
//        for(XWPFTableRow row :rows){
//            List<XWPFTableCell> cells = row.getTableCells();
//            for(XWPFTableCell cell: cells){
//                cell.setText("第"+String.valueOf(i++)+"格");
//                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
//                //cell.setWidthType(TableWidthType.PCT);
//                //cell.setWidth("30%");
//            }
//        }
//    }
//    public static CTTcPr getCellCTTcPr(XWPFTableCell cell) {
//        CTTc cttc = cell.getCTTc();
//        CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
//        return tcPr;
//    }


    @PostMapping(value = "/getWord")
    @ApiOperation("下载工商登记申请书")
    public List<SelfEmployedVo> getWord(String selfId,String selfCode) throws Exception {
        //生成文件名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhss");
        String nowDate = sdf.format(date);
        String guid= UUID.randomUUID().toString();
        String fileName=nowDate+guid+selfCode+"createTable.docx";

        //根据selfCode获取工商信息
        SelfEmployedVo selfEmployedVo=new SelfEmployedVo();
        selfEmployedVo.setSelfCode(selfCode);
        List<SelfEmployedVo> selfEmployedVos= selfEmployedService.selectEmployedJoinReview(selfEmployedVo);
        System.out.println("selfEmployedVos=="+selfEmployedVos);

        //创建word文档
        XWPFDocument doc = new XWPFDocument();

        //创建段落(标题)
        XWPFParagraph p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runTitle = p1.createRun();
        runTitle.setText("个体工商户登记（备案）申请书");
        runTitle.setBold(true);
        runTitle.setFontSize(24);
        runTitle.setFontFamily("宋体");
        runTitle.addCarriageReturn();//回车键
        runTitle.setKerning(30);

//        XWPFParagraph paragraphX = docxDocument.createParagraph();
//        paragraphX.setAlignment(ParagraphAlignment.LEFT);//对齐方式
//        paragraphX.setFirstLineIndent(400);//首行缩进
//        //创建段落中的run
//        XWPFRun run = paragraphX.createRun();
//        run.setText("开始新的额一页了健康卡离开了危，机容量为金融界王仁君我快速建房可谓集，有分页吗，按时交付问我问问");
//        //run.addCarriageReturn();//回车键
//        XWPFRun run2 = paragraphX.createRun();
//        run2.setText("这是第二段了吧，接口了就废了我今儿来将危及，不知道嗯么回事了了，啦啦啦啦啦啦啦");
//        run2.setText("这个不是能分段吗，测试一下试试");
//        run2.setBold(true);//加粗

        //创建表格 16行*3列(创建table 时，会有一个默认一行一列的表格)
        XWPFTable table = doc.createTable(16,3);
        TableTools.mergeCellsHorizonal(table, 0, 0, 2);
//        table.getRow(0).getCell(0).setText("基本信息（必填项）");



        //写到本地
        try (FileOutputStream out = new FileOutputStream(configProps.getName()+fileName)) {
            doc.write(out);
        }
//        doc.write(out);
//        out.close();
//        Thread.sleep(2000);

//        //word转成pdf
//        FileInputStream fileInputStream = null;
//        FileOutputStream  fileOutputStream=null;
//        try {
//            // 读取docx文件
//            fileInputStream = new FileInputStream(file.getAbsolutePath());
//            XWPFDocument doc = new XWPFDocument(fileInputStream);
//            doc.createStyles();
//            PdfOptions pdfOptions = PdfOptions.create();
//            // 输出路径
//            fileOutputStream= new FileOutputStream(new File(configProps.getName()+nowDate+guid+"转换结果.pdf"));
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
        return selfEmployedVos;

    }

}
