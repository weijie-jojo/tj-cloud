package com.ruoyi.company.controller;


import com.alibaba.csp.sentinel.transport.CommandCenter;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.style.TableStyle;
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
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
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
        SelfEmployedVo selfEmployed=selfEmployedVos.get(0);
        System.out.println("selfEmployed=="+selfEmployed);

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

        //创建表格 16行*4列(创建table 时，会有一个默认一行一列的表格)
        XWPFTable table = doc.createTable(16,4);
        // 设置表格宽度为A4纸最大宽度
        TableTools.widthTable(table, MiniTableRenderData.WIDTH_A4_FULL, 10);
        // 设置表格居中
        TableStyle style = new TableStyle();
        style.setAlign(STJc.CENTER);
        TableTools.styleTable(table, style);
        //合并单元格
        TableTools.mergeCellsHorizonal(table, 0, 0, 3);
        TableTools.mergeCellsHorizonal(table, 3, 1, 3);
        TableTools.mergeCellsHorizonal(table, 4, 1, 3);
        TableTools.mergeCellsHorizonal(table, 5, 0, 3);
        TableTools.mergeCellsHorizonal(table, 7, 1, 3);
        TableTools.mergeCellsHorizonal(table, 8, 0, 3);
        TableTools.mergeCellsHorizonal(table, 9, 2, 3);
        TableTools.mergeCellsHorizonal(table, 10, 2, 3);
        TableTools.mergeCellsHorizonal(table, 11, 2, 3);
        TableTools.mergeCellsHorizonal(table, 12, 2, 3);
        TableTools.mergeCellsHorizonal(table, 13, 2, 3);
        TableTools.mergeCellsHorizonal(table, 14, 2, 3);
        TableTools.mergeCellsHorizonal(table, 15, 2, 3);
        //第一行单元格
        XWPFTableCell cell1 = table.getRow(0).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph addParagraph1 =  cell1.addParagraph();
        addParagraph1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r1 = addParagraph1.createRun();
        r1.setText("基本信息（必填项）");
        r1.setFontFamily("宋体");
        r1.setFontSize(16);
        r1.setBold(true);//字体加粗

        //第二行单元格
        XWPFTableCell cell2_1 = table.getRow(1).getCell(0);
        XWPFParagraph addParagraph2_1 =  cell2_1.addParagraph();
        addParagraph2_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r2_1 = addParagraph2_1.createRun();
        r2_1.setText("名    称\n" +
                "(选填)");
        r2_1.setFontFamily("宋体");
        r2_1.setFontSize(12);

        XWPFTableCell cell2_2 = table.getRow(1).getCell(1);
        XWPFParagraph addParagraph2_2 =  cell2_2.addParagraph();
        addParagraph2_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2_2 = addParagraph2_2.createRun();
        r2_2.setText(selfEmployed.getSelfName());
        r2_2.setFontFamily("宋体");
        r2_2.setFontSize(12);

        XWPFTableCell cell2_3 = table.getRow(1).getCell(2);
        XWPFParagraph addParagraph2_3 =  cell2_3.addParagraph();
        addParagraph2_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2_3 = addParagraph2_3.createRun();
        r2_3.setText("统一社会信用代码\n" +
                "(设立登记不填写)");
        r2_3.setFontFamily("宋体");
        r2_3.setFontSize(12);

        XWPFTableCell cell2_4 = table.getRow(1).getCell(3);
        XWPFParagraph addParagraph2_4 =  cell2_4.addParagraph();
        addParagraph2_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2_4 = addParagraph2_4.createRun();
        r2_4.setText(selfEmployed.getTaxId());
        r2_4.setFontFamily("宋体");
        r2_4.setFontSize(12);

        //第三行单元格
        XWPFTableCell cell3_1 = table.getRow(2).getCell(0);
        XWPFParagraph addParagraph3_1 =  cell3_1.addParagraph();
        addParagraph3_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r3_1 = addParagraph3_1.createRun();
        r3_1.setText("联系电话");
        r3_1.setFontFamily("宋体");
        r3_1.setFontSize(12);

        XWPFTableCell cell3_2 = table.getRow(2).getCell(1);
        XWPFParagraph addParagraph3_2 =  cell3_2.addParagraph();
        addParagraph3_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r3_2 = addParagraph3_2.createRun();
        r3_2.setText(selfEmployed.getContactPhone());
        r3_2.setFontFamily("宋体");
        r3_2.setFontSize(12);

        XWPFTableCell cell3_3 = table.getRow(2).getCell(2);
        XWPFParagraph addParagraph3_3 =  cell3_3.addParagraph();
        addParagraph3_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r3_3 = addParagraph3_3.createRun();
        r3_3.setText("邮政编码");
        r3_3.setFontFamily("宋体");
        r3_3.setFontSize(12);

        XWPFTableCell cell3_4 = table.getRow(2).getCell(3);
        XWPFParagraph addParagraph3_4 =  cell3_4.addParagraph();
        addParagraph3_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r3_4 = addParagraph3_4.createRun();
        r3_4.setText("364400");
        r3_4.setFontFamily("宋体");
        r3_4.setFontSize(12);

        //第四行单元格
        XWPFTableCell cell4_1 = table.getRow(3).getCell(0);
        XWPFParagraph addParagraph4_1 =  cell4_1.addParagraph();
        addParagraph4_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r4_1 = addParagraph4_1.createRun();
        r4_1.setText("经营场所");
        r4_1.setFontFamily("宋体");
        r4_1.setFontSize(12);

        XWPFTableCell cell4_2 = table.getRow(3).getCell(1);
        XWPFParagraph addParagraph4_2 =  cell4_2.addParagraph();
        addParagraph4_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r4_2 = addParagraph4_2.createRun();
        r4_2.setText("漳平市菁城街道双拥路202号A栋210室（集群注册）");
        r4_2.setFontFamily("宋体");
        r4_2.setFontSize(12);

        //第五行单元格
        XWPFTableCell cell5_1 = table.getRow(4).getCell(0);
        XWPFParagraph addParagraph5_1 =  cell5_1.addParagraph();
        addParagraph5_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r5_1 = addParagraph5_1.createRun();
        r5_1.setText("电子商务经营者");
        r5_1.setFontFamily("宋体");
        r5_1.setFontSize(12);

        XWPFTableCell cell5_2 = table.getRow(4).getCell(1);
        XWPFParagraph addParagraph5_2 =  cell5_2.addParagraph();
        addParagraph5_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r5_2 = addParagraph5_2.createRun();
        r5_2.setText("□是     □否");
        r5_2.setFontFamily("宋体");
        r5_2.setFontSize(12);

        //第六行单元格
        XWPFTableCell cell6_1 = table.getRow(5).getCell(0);
        XWPFParagraph addParagraph6_1 =  cell6_1.addParagraph();
        addParagraph6_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r6_1 = addParagraph6_1.createRun();
        r6_1.setText("设立（仅设立登记填写）");
        r6_1.setFontFamily("宋体");
        r6_1.setFontSize(16);
        r6_1.setBold(true);//字体加粗

        //第七行单元格
        XWPFTableCell cell7_1 = table.getRow(6).getCell(0);
        XWPFParagraph addParagraph7_1 =  cell7_1.addParagraph();
        addParagraph7_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r7_1 = addParagraph7_1.createRun();
        r7_1.setText("出 资 额");
        r7_1.setFontFamily("宋体");
        r7_1.setFontSize(12);

        XWPFTableCell cell7_2 = table.getRow(6).getCell(1);
        XWPFParagraph addParagraph7_2 =  cell7_2.addParagraph();
        addParagraph7_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r7_2 = addParagraph7_2.createRun();
        r7_2.setText(selfEmployed.getContributionAmount().toString()+"万元（人民币）");
        r7_2.setFontFamily("宋体");
        r7_2.setFontSize(12);

        XWPFTableCell cell7_3 = table.getRow(6).getCell(2);
        XWPFParagraph addParagraph7_3 =  cell7_3.addParagraph();
        addParagraph7_3.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r7_3 = addParagraph7_3.createRun();
        r7_3.setText("申领执照");
        r7_3.setFontFamily("宋体");
        r7_3.setFontSize(12);

        XWPFTableCell cell7_4 = table.getRow(6).getCell(3);
        XWPFParagraph addParagraph7_4 =  cell7_4.addParagraph();
        addParagraph7_4.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r7_4 = addParagraph7_1.createRun();
        r7_4.setText("申领纸质执照  其中：副本  1   个（电子执照系统自动生成，纸质执照自行勾选）");
        r7_4.setFontFamily("宋体");
        r7_4.setFontSize(12);

        //第八行单元格
        XWPFTableCell cell8_1 = table.getRow(7).getCell(0);
        XWPFParagraph addParagraph8_1 =  cell8_1.addParagraph();
        addParagraph8_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r8_1 = addParagraph8_1.createRun();
        r8_1.setText("经营范围\n" +
                "（根据登记机关公布的经营项目分类标准办理经营范围登记）");
        r8_1.setFontFamily("宋体");
        r8_1.setFontSize(12);

        XWPFTableCell cell8_2 = table.getRow(7).getCell(1);
        XWPFParagraph addParagraph8_2 =  cell8_2.addParagraph();
        addParagraph8_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r8_2 = addParagraph8_2.createRun();
        r8_2.setText(selfEmployed.getNatureBusiness());
        r8_2.setFontFamily("宋体");
        r8_2.setFontSize(12);

        //第九行单元格
        XWPFTableCell cell9_1 = table.getRow(8).getCell(0);
        XWPFParagraph addParagraph9_1 =  cell9_1.addParagraph();
        addParagraph9_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r9_1 = addParagraph9_1.createRun();
        r9_1.setText("□变更（仅变更登记填写，只填写与本次申请有关的事项）");
        r9_1.setFontFamily("宋体");
        r9_1.setFontSize(16);
        r9_1.setBold(true);//字体加粗

        //第十行单元格
        XWPFTableCell cell10_1 = table.getRow(9).getCell(0);
        XWPFParagraph addParagraph10_1 =  cell10_1.addParagraph();
        addParagraph10_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r10_1 = addParagraph10_1.createRun();
        r10_1.setText("变更事项\n" +
                "（可多选）");
        r10_1.setFontFamily("宋体");
        r10_1.setFontSize(12);

        XWPFTableCell cell10_2 = table.getRow(9).getCell(1);
        XWPFParagraph addParagraph10_2 =  cell10_2.addParagraph();
        addParagraph10_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r10_2 = addParagraph10_2.createRun();
        r10_2.setText("原登记内容");
        r10_2.setFontFamily("宋体");
        r10_2.setFontSize(12);

        XWPFTableCell cell10_3 = table.getRow(9).getCell(2);
        XWPFParagraph addParagraph10_3 =  cell10_3.addParagraph();
        addParagraph10_3.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r10_3 = addParagraph10_3.createRun();
        r10_3.setText("变更后登记内容");
        r10_3.setFontFamily("宋体");
        r10_3.setFontSize(12);

        //第十一行单元格
        XWPFTableCell cell11_1 = table.getRow(10).getCell(0);
        XWPFParagraph addParagraph11_1 =  cell11_1.addParagraph();
        addParagraph11_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r11_1 = addParagraph11_1.createRun();
        r11_1.setText("□名称");
        r11_1.setFontFamily("宋体");
        r11_1.setFontSize(12);

        XWPFTableCell cell11_2 = table.getRow(10).getCell(1);
        XWPFParagraph addParagraph11_2 =  cell11_2.addParagraph();
        addParagraph11_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r11_2 = addParagraph11_2.createRun();
//        r11_2.setText("原登记内容");
//        r11_2.setFontFamily("宋体");
//        r11_2.setFontSize(12);

        XWPFTableCell cell11_3 = table.getRow(10).getCell(2);
        XWPFParagraph addParagraph11_3 =  cell11_3.addParagraph();
        addParagraph11_3.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r11_3 = addParagraph11_3.createRun();
//        r11_3.setText("变更后登记内容");
//        r11_3.setFontFamily("宋体");
//        r11_3.setFontSize(12);

        //第十二行单元格
        XWPFTableCell cell12_1 = table.getRow(11).getCell(0);
        XWPFParagraph addParagraph12_1 =  cell12_1.addParagraph();
        addParagraph12_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r12_1 = addParagraph12_1.createRun();
        r12_1.setText("□经营场所");
        r12_1.setFontFamily("宋体");
        r12_1.setFontSize(12);

        XWPFTableCell cell12_2 = table.getRow(11).getCell(1);
        XWPFParagraph addParagraph12_2 =  cell12_2.addParagraph();
        addParagraph12_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r12_2 = addParagraph12_2.createRun();
//        r12_2.setText("原登记内容");
//        r12_2.setFontFamily("宋体");
//        r12_2.setFontSize(12);

        XWPFTableCell cell12_3 = table.getRow(11).getCell(2);
        XWPFParagraph addParagraph12_3 =  cell12_3.addParagraph();
        addParagraph12_3.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r12_3 = addParagraph12_3.createRun();
//        r12_3.setText("变更后登记内容");
//        r12_3.setFontFamily("宋体");
//        r12_3.setFontSize(12);

        //第十三行单元格
        XWPFTableCell cell13_1 = table.getRow(12).getCell(0);
        XWPFParagraph addParagraph13_1 =  cell13_1.addParagraph();
        addParagraph13_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r13_1 = addParagraph13_1.createRun();
        r13_1.setText("□经营范围（根据登记机关公布的经营项目分类标准办理经营范围登记）");
        r13_1.setFontFamily("宋体");
        r13_1.setFontSize(12);

        XWPFTableCell cell13_2 = table.getRow(12).getCell(1);
        XWPFParagraph addParagraph13_2 =  cell13_2.addParagraph();
        addParagraph13_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r13_2 = addParagraph13_2.createRun();
//        r13_2.setText("原登记内容");
//        r13_2.setFontFamily("宋体");
//        r13_2.setFontSize(12);

        XWPFTableCell cell13_3 = table.getRow(12).getCell(2);
        XWPFParagraph addParagraph13_3 =  cell13_3.addParagraph();
        addParagraph13_3.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r13_3 = addParagraph13_3.createRun();
        r13_3.setText("(申请人须根据企业自身情况填写《企业登记政府部门共享信息表》相关内容。)");
        r13_3.setFontFamily("宋体");
        r13_3.setFontSize(12);

        //第十四行单元格
        XWPFTableCell cell14_1 = table.getRow(13).getCell(0);
        XWPFParagraph addParagraph14_1 =  cell14_1.addParagraph();
        addParagraph14_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r14_1 = addParagraph14_1.createRun();
        r14_1.setText("□出资额");
        r14_1.setFontFamily("宋体");
        r14_1.setFontSize(12);

        XWPFTableCell cell14_2 = table.getRow(13).getCell(1);
        XWPFParagraph addParagraph14_2 =  cell14_2.addParagraph();
        addParagraph14_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r14_2 = addParagraph14_2.createRun();
//        r14_2.setText("原登记内容");
//        r14_2.setFontFamily("宋体");
//        r14_2.setFontSize(12);

        XWPFTableCell cell14_3 = table.getRow(13).getCell(2);
        XWPFParagraph addParagraph14_3 =  cell14_3.addParagraph();
        addParagraph14_3.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r14_3 = addParagraph14_3.createRun();
//        r14_3.setText("变更后登记内容");
//        r14_3.setFontFamily("宋体");
//        r14_3.setFontSize(12);

        //第十五行单元格
        XWPFTableCell cell15_1 = table.getRow(14).getCell(0);
        XWPFParagraph addParagraph15_1 =  cell15_1.addParagraph();
        addParagraph15_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r15_1 = addParagraph15_1.createRun();
        r15_1.setText("□变更组成形式");
        r15_1.setFontFamily("宋体");
        r15_1.setFontSize(12);

        XWPFTableCell cell15_2 = table.getRow(14).getCell(1);
        XWPFParagraph addParagraph15_2 =  cell15_2.addParagraph();
        addParagraph15_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r15_2 = addParagraph15_2.createRun();
        r15_2.setText("□个人经营       □家庭经营");
        r15_2.setFontFamily("宋体");
        r15_2.setFontSize(12);

        XWPFTableCell cell15_3 = table.getRow(14).getCell(2);
        XWPFParagraph addParagraph15_3 =  cell15_3.addParagraph();
        addParagraph15_3.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r15_3 = addParagraph15_3.createRun();
        r15_3.setText("□个人经营       □家庭经营");
        r15_3.setFontFamily("宋体");
        r15_3.setFontSize(12);

        //第十六行单元格
        XWPFTableCell cell16_1 = table.getRow(15).getCell(0);
        XWPFParagraph addParagraph16_1 =  cell16_1.addParagraph();
        addParagraph16_1.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r16_1 = addParagraph16_1.createRun();
        r16_1.setText("□变更经营者姓名、住所");
        r16_1.setFontFamily("宋体");
        r16_1.setFontSize(12);

        XWPFTableCell cell16_2 = table.getRow(15).getCell(1);
        XWPFParagraph addParagraph16_2 =  cell16_2.addParagraph();
        addParagraph16_2.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r16_2 = addParagraph16_2.createRun();
//        r16_2.setText("原登记内容");
//        r16_2.setFontFamily("宋体");
//        r16_2.setFontSize(12);

        XWPFTableCell cell16_3 = table.getRow(15).getCell(2);
        XWPFParagraph addParagraph16_3 =  cell16_3.addParagraph();
        addParagraph16_3.setAlignment(ParagraphAlignment.CENTER);//居中
        XWPFRun r16_3 = addParagraph16_3.createRun();
//        r16_3.setText("变更后登记内容");
//        r16_3.setFontFamily("宋体");
//        r16_3.setFontSize(12);
        //页脚
        XWPFFooter footer = doc.createFooter(HeaderFooterType.DEFAULT);
        footer.createParagraph().createRun().setText("注:本申请书适用个体工商户申请设立、变更、备案、注销。");

        //写到本地
        File file =new File(configProps.getName()+fileName);
        try (FileOutputStream out = new FileOutputStream(file)) {
            doc.write(out);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        //word转成pdf
//        FileInputStream fileInputStream = null;
//        FileOutputStream  fileOutputStream=null;
//        try {
//            // 读取docx文件
//            fileInputStream = new FileInputStream(file.getAbsolutePath());
//            XWPFDocument doc1 = new XWPFDocument(fileInputStream);
//            doc1.createStyles();
//            PdfOptions pdfOptions = PdfOptions.create();
//            // 输出路径
//            fileOutputStream= new FileOutputStream(new File(configProps.getName()+nowDate+guid+"转换结果.pdf"));
//            // 调用转换
//            PdfConverter.getInstance().convert(doc1, fileOutputStream, pdfOptions);
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }finally {
//            fileInputStream.close();
//            fileOutputStream.close();
//        }

        //插入文件名到数据库
        SelfEmployed selfEmployed2=new SelfEmployed();
        selfEmployed2.setFileName8(fileName);
        selfEmployed2.setSelfId(selfId);
        selfEmployedService.updateSelfEmployed(selfEmployed2);
        return selfEmployedVos;

    }

}
