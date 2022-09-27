package com.ruoyi.company.controller;


import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.util.TableTools;
import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.ruoyi.company.config.ConfigProps;
import com.ruoyi.company.domain.SelfEmployed;
import com.ruoyi.company.domain.vo.SelfEmployedVo;
import com.ruoyi.company.service.ISelfEmployedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
    public static byte[] docxToPdf(InputStream src,String outPutSrc) throws FileNotFoundException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        new FileOutputStream(new File(configProps.getName()+nowDate+guid+"转换结果.pdf"));
        FileOutputStream fileOutputStream= new FileOutputStream(new File(outPutSrc));
        byte[] resBytes = null;
        String result;
        try {
            // pdf文件的尺寸
            Document pdfDocument = new Document(PageSize.A3, 72, 72, 72, 72);
            XWPFDocument doc = new XWPFDocument(src);
//            Document document=new Document()
            PdfWriter pdfWriter = PdfWriter.getInstance( pdfDocument, fileOutputStream);

            pdfWriter.setInitialLeading(20);
            java.util.List<XWPFParagraph> plist = doc.getParagraphs();
//            List<XWPFTable> tableList = doc.getTables();
            pdfWriter.open();
            pdfDocument.open();

//            Iterator<XWPFTable> tableIterator = tableList.iterator();
//            while (tableIterator.hasNext()) {
//                XWPFTable table = tableIterator.next();
//                String text = table.getText();
//                byte[] tableBs;
//                if (text != null) {
//                    tableBs = text.getBytes();
//                    String str = new String(tableBs);
//                    Chunk chObj1 = new Chunk(str);
//                    pdfDocument.add(chObj1);
//                }
//            }
            for (int i = 0; i < plist.size(); i++) {
                XWPFParagraph pa = plist.get(i);
                java.util.List<XWPFRun> runs = pa.getRuns();
                for (int j = 0; j < runs.size(); j++) {
                    XWPFRun run = runs.get(j);
                    java.util.List<XWPFPicture> piclist = run.getEmbeddedPictures();
                    Iterator<XWPFPicture> iterator = piclist.iterator();
                    while (iterator.hasNext()) {
                        XWPFPicture pic = iterator.next();
                        XWPFPictureData picdata = pic.getPictureData();
                        byte[] bytepic = picdata.getData();
                        Image imag = Image.getInstance(bytepic);
                        pdfDocument.add(imag);
                    }
                    // 中文字体的解决
                    BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                    Font font = new Font(bf, 11.0f, Font.NORMAL, BaseColor.BLACK);
                    String text = run.getText(-1);
                    byte[] bs;
                    if (text != null) {
                        bs = text.getBytes();
                        String str = new String(bs);
                        Chunk chObj1 = new Chunk(str, font);
                        pdfDocument.add(chObj1);
                    }
                }
                pdfDocument.add(new Chunk(Chunk.NEWLINE));
            }
            //需要关闭，不然无法获取到输出流
            pdfDocument.close();
            pdfWriter.close();
//            resBytes = baos.toByteArray();
        } catch (Exception e) {
            System.out.println("docx转pdf文件异常："+e.getMessage());
        }finally {
//            try{
//                if(baos != null){
//                    baos.close();
//                }
//            }catch (IOException e){
//                System.out.println("docx转pdf关闭io流异常：{}"+e);
//            }
        }
        return resBytes;
    }

    //word 转 html
//    public static void convert2Html(String fileName, String outPutFile)
//            throws TransformerException, IOException,
//            ParserConfigurationException {
//
//        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
//        //兼容2007 以上版本
////        XSSFWorkbook  xssfwork=new XSSFWorkbook(new FileInputStream(fileName));
//        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//                DocumentBuilderFactory.newInstance().newDocumentBuilder()
//                        .newDocument());
//        wordToHtmlConverter.setPicturesManager( new PicturesManager()
//        {
//            @Override
//            public String savePicture(byte[] content,
//                                      PictureType pictureType, String suggestedName,
//                                      float widthInches, float heightInches )
//            {
//                return "test/"+suggestedName;
//            }
//        } );
//        wordToHtmlConverter.processDocument(wordDocument);
//        //save pictures
//        List pics=wordDocument.getPicturesTable().getAllPictures();
//        if(pics!=null){
//            for(int i=0;i<pics.size();i++){
//                Picture pic = (Picture)pics.get(i);
//                System.out.println();
//                try {
//                    pic.writeImageContent(new FileOutputStream("D:/test/"
//                            + pic.suggestFullFileName()));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Document htmlDocument = (Document) wordToHtmlConverter.getDocument();
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        DOMSource domSource = new DOMSource((Node) htmlDocument);
//        StreamResult streamResult = new StreamResult(out);
//
//
//        TransformerFactory tf = TransformerFactory.newInstance();
//        Transformer serializer = tf.newTransformer();
//        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//        serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
//        serializer.transform(domSource, streamResult);
//        out.close();
//        writeFile(new String(out.toByteArray()), outPutFile);
//    };

    @PostMapping(value = "/getWord")
    @ApiOperation("下载工商登记申请书")
    public List<SelfEmployedVo> getWord(String selfId,String selfCode) throws Exception {
        //生成文件名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhss");
        String nowDate = sdf.format(date);
        String guid= UUID.randomUUID().toString();
        String fileName=nowDate+guid+selfCode+"createTable.doc";

        //根据selfCode获取工商信息
        SelfEmployedVo selfEmployedVo=new SelfEmployedVo();
        selfEmployedVo.setSelfCode(selfCode);
        List<SelfEmployedVo> selfEmployedVos= selfEmployedService.selectEmployedJoinReview(null,selfEmployedVo);
        SelfEmployedVo selfEmployed=selfEmployedVos.get(0);

        //创建word文档
        File file =new File(configProps.getName()+fileName);
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

        //创建表格 16行*4列(创建table 时，会有一个默认一行一列的表格)
//        XWPFTable table =  doc.getTableArray(0);
        XWPFTable table = doc.createTable(16,4);
        TableTools.borderTable(table,14);//表格边框粗细
        // 设置表格宽度为A4纸最大宽度
//        TableTools.widthTable(table, MiniTableRenderData.WIDTH_A4_FULL, 2000);
        float[] colWidths={5,4,5,4};
        TableTools.widthTable(table,colWidths);
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
        r1.setFontSize(14);
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
        r6_1.setFontSize(14);
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
        r9_1.setFontSize(14);
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
        XWPFParagraph p7= doc.createParagraph();
//        XWPFRun run7 =  p7.createRun();

        XWPFParagraph p8= doc.createParagraph();
        p8.setFirstLineIndent(400);//首行缩进
        XWPFRun run8 =  p8.createRun();
        run8.setText("注:本申请书适用个体工商户申请设立、变更、备案、注销。");

        doc.createParagraph().createRun().addBreak(BreakType.PAGE);
        //创建表格 17行*12列(创建table 时，会有一个默认一行一列的表格)
//        XWPFTable table2 =  doc.getTableArray(1);
        XWPFTable table2 = doc.createTable(17,12);
        TableTools.borderTable(table2,14);//表格边框粗细
        // 设置表格宽度为A4纸最大宽度
//        TableTools.widthTable(table2, MiniTableRenderData.WIDTH_A4_FULL, 20);
        float[] colWidths2={1.5F,1.5F,1.5F,1.5F,1.5F,1.5F,1.5F,1.5F,1.5F,1.5F,1.5F,1.5F};
        TableTools.widthTable(table2,colWidths2);
        // 设置表格居中
        TableStyle style2 = new TableStyle();
        style2.setAlign(STJc.CENTER);
        TableTools.styleTable(table2, style2);
        //合并单元格
        TableTools.mergeCellsHorizonal(table2, 0, 0, 11);
        TableTools.mergeCellsHorizonal(table2, 1, 0, 1);
        TableTools.mergeCellsHorizonal(table2, 1, 1, 10);
        TableTools.mergeCellsHorizonal(table2, 2, 0, 11);
        TableTools.mergeCellsHorizonal(table2, 3, 0, 1);
        TableTools.mergeCellsHorizonal(table2, 3, 1, 2);
        TableTools.mergeCellsHorizonal(table2, 3, 2, 3);
        TableTools.mergeCellsHorizonal(table2, 3, 3, 4);
        TableTools.mergeCellsHorizonal(table2, 3, 4, 5);
        TableTools.mergeCellsHorizonal(table2, 3, 5, 6);
        TableTools.mergeCellsHorizonal(table2, 4, 0, 1);
        TableTools.mergeCellsHorizonal(table2, 4, 1, 2);
        TableTools.mergeCellsHorizonal(table2, 4, 2, 3);
        TableTools.mergeCellsHorizonal(table2, 4, 3, 4);
        TableTools.mergeCellsHorizonal(table2, 4, 4, 5);
        TableTools.mergeCellsHorizonal(table2, 4, 5, 6);
        TableTools.mergeCellsHorizonal(table2, 5, 0, 1);
        TableTools.mergeCellsHorizonal(table2, 5, 1, 2);
        TableTools.mergeCellsHorizonal(table2, 5, 2, 3);
        TableTools.mergeCellsHorizonal(table2, 5, 3, 4);
        TableTools.mergeCellsHorizonal(table2, 5, 4, 5);
        TableTools.mergeCellsHorizonal(table2, 5, 5, 6);
        TableTools.mergeCellsHorizonal(table2, 6, 0, 1);
        TableTools.mergeCellsHorizonal(table2, 6, 1, 2);
        TableTools.mergeCellsHorizonal(table2, 6, 2, 3);
        TableTools.mergeCellsHorizonal(table2, 6, 3, 8);
        TableTools.mergeCellsHorizonal(table2, 7, 0, 1);
        TableTools.mergeCellsHorizonal(table2, 7, 1, 10);
        TableTools.mergeCellsHorizonal(table2, 8, 0, 2);
        TableTools.mergeCellsHorizonal(table2, 8, 1, 3);
        TableTools.mergeCellsHorizonal(table2, 8, 2, 4);
        TableTools.mergeCellsHorizonal(table2, 8, 3, 5);
        TableTools.mergeCellsHorizonal(table2, 9, 0, 2);
        TableTools.mergeCellsHorizonal(table2, 9, 1, 3);
        TableTools.mergeCellsHorizonal(table2, 9, 2, 4);
        TableTools.mergeCellsHorizonal(table2, 9, 3, 5);
        TableTools.mergeCellsHorizonal(table2, 10, 0, 2);
        TableTools.mergeCellsHorizonal(table2, 10, 1, 3);
        TableTools.mergeCellsHorizonal(table2, 10, 2, 4);
        TableTools.mergeCellsHorizonal(table2, 10, 3, 5);
        TableTools.mergeCellsHorizonal(table2, 11, 0, 2);
        TableTools.mergeCellsHorizonal(table2, 11, 1, 3);
        TableTools.mergeCellsHorizonal(table2, 11, 2, 4);
        TableTools.mergeCellsHorizonal(table2, 11, 3, 5);
        TableTools.mergeCellsHorizonal(table2, 12, 0, 11);
        TableTools.mergeCellsHorizonal(table2, 13, 0, 11);
        TableTools.mergeCellsHorizonal(table2, 14, 0, 2);
        TableTools.mergeCellsHorizonal(table2, 14, 1, 9);
        TableTools.mergeCellsHorizonal(table2, 15, 0, 2);
        TableTools.mergeCellsHorizonal(table2, 15, 1, 9);
        TableTools.mergeCellsHorizonal(table2, 16, 0, 2);
        TableTools.mergeCellsHorizonal(table2, 16, 1, 9);

        //第一行单元格
        XWPFTableCell table2_cell1_1 = table2.getRow(0).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph1_1 =  table2_cell1_1.addParagraph();
        table2_paragraph1_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r1_1 = table2_paragraph1_1.createRun();
        table2_r1_1.setText("□备案（仅备案填写）");
        table2_r1_1.setFontFamily("宋体");
        table2_r1_1.setFontSize(16);
        table2_r1_1.setBold(true);//字体加粗

        //第二行单元格
        XWPFTableCell table2_cell2_1 = table2.getRow(1).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph2_1 =  table2_cell2_1.addParagraph();
        table2_paragraph2_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r2_1 = table2_paragraph2_1.createRun();
        table2_r2_1.setText("事   项");
        table2_r2_1.setFontFamily("宋体");
        table2_r2_1.setFontSize(12);

        XWPFTableCell table2_cell2_2 = table2.getRow(1).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph2_2 =  table2_cell2_2.addParagraph();
        table2_paragraph2_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r2_2 = table2_paragraph2_2.createRun();
        table2_r2_2.setText("□联络员\n" +
                "□参加家庭经营的家庭成员姓名");
        table2_r2_2.setFontFamily("宋体");
        table2_r2_2.setFontSize(12);

        //第三行单元格
        XWPFTableCell table2_cell3_1 = table2.getRow(2).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph3_1 =  table2_cell3_1.addParagraph();
        table2_paragraph3_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r3_1 = table2_paragraph3_1.createRun();
        table2_r3_1.setText("□经营者及家庭成员信息（设立登记必填项，变更经营者、备案家庭成员填写）");
        table2_r3_1.setFontFamily("宋体");
        table2_r3_1.setFontSize(16);
        table2_r3_1.setBold(true);//字体加粗

        //第四行单元格
        XWPFTableCell table2_cell4_1 = table2.getRow(3).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph4_1 =  table2_cell4_1.addParagraph();
        table2_paragraph4_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r4_1 = table2_paragraph4_1.createRun();
        table2_r4_1.setText("经营者姓名");
        table2_r4_1.setFontFamily("宋体");
        table2_r4_1.setFontSize(12);

        XWPFTableCell table2_cell4_2 = table2.getRow(3).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph4_2 =  table2_cell4_2.addParagraph();
        table2_paragraph4_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r4_2 = table2_paragraph4_2.createRun();
        table2_r4_2.setText(selfEmployed.getContactName());
        table2_r4_2.setFontFamily("宋体");
        table2_r4_2.setFontSize(12);

        XWPFTableCell table2_cell4_3 = table2.getRow(3).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph4_3 =  table2_cell4_3.addParagraph();
        table2_paragraph4_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r4_3 = table2_paragraph4_3.createRun();
        table2_r4_3.setText("经营者性别");
        table2_r4_3.setFontFamily("宋体");
        table2_r4_3.setFontSize(12);

        XWPFTableCell table2_cell4_4 = table2.getRow(3).getCell(3);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph4_4 =  table2_cell4_4.addParagraph();
        table2_paragraph4_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r4_4 = table2_paragraph4_4.createRun();
        if (selfEmployed.getGender().equals("1")){
            System.out.println("我是男的");
            table2_r4_4.setText("男");
        }
        if (selfEmployed.getGender().equals("2")){
            System.out.println("我是女的");
            table2_r4_4.setText("女");
        }
        table2_r4_4.setFontFamily("宋体");
        table2_r4_4.setFontSize(12);

        XWPFTableCell table2_cell4_5 = table2.getRow(3).getCell(4);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph4_5 =  table2_cell4_5.addParagraph();
        table2_paragraph4_5.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r4_5 = table2_paragraph4_5.createRun();
        table2_r4_5.setText("经营者民族");
        table2_r4_5.setFontFamily("宋体");
        table2_r4_5.setFontSize(12);

        XWPFTableCell table2_cell4_6 = table2.getRow(3).getCell(5);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph4_6 =  table2_cell4_6.addParagraph();
        table2_paragraph4_6.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r4_6 = table2_paragraph4_6.createRun();
        table2_r4_6.setText(selfEmployed.getNation());
        table2_r4_6.setFontFamily("宋体");
        table2_r4_6.setFontSize(12);

        //第五行单元格
        XWPFTableCell table2_cell5_1 = table2.getRow(4).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph5_1 =  table2_cell5_1.addParagraph();
        table2_paragraph5_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r5_1 = table2_paragraph5_1.createRun();
        table2_r5_1.setText("经营者文化程度");
        table2_r5_1.setFontFamily("宋体");
        table2_r5_1.setFontSize(12);

        XWPFTableCell table2_cell5_2 = table2.getRow(4).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph5_2 =  table2_cell5_2.addParagraph();
        table2_paragraph5_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r5_2 = table2_paragraph5_2.createRun();
        table2_r5_2.setText(selfEmployed.getDictLabel());
        table2_r5_2.setFontFamily("宋体");
        table2_r5_2.setFontSize(12);

        XWPFTableCell table2_cell5_3 = table2.getRow(4).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph5_3 =  table2_cell5_3.addParagraph();
        table2_paragraph5_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r5_3 = table2_paragraph5_3.createRun();
        table2_r5_3.setText("经营者政治面貌");
        table2_r5_3.setFontFamily("宋体");
        table2_r5_3.setFontSize(12);

        XWPFTableCell table2_cell5_4 = table2.getRow(4).getCell(3);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph5_4 =  table2_cell5_4.addParagraph();
        table2_paragraph5_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r5_4 = table2_paragraph5_4.createRun();
        table2_r5_4.setText(selfEmployed.getPoliticalStatus());
        table2_r5_4.setFontFamily("宋体");
        table2_r5_4.setFontSize(12);

        XWPFTableCell table2_cell5_5 = table2.getRow(4).getCell(4);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph5_5 =  table2_cell5_5.addParagraph();
        table2_paragraph5_5.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r5_5 = table2_paragraph5_5.createRun();
        table2_r5_5.setText("经营者身份证件类型");
        table2_r5_5.setFontFamily("宋体");
        table2_r5_5.setFontSize(12);

        XWPFTableCell table2_cell5_6 = table2.getRow(4).getCell(5);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph5_6 =  table2_cell5_6.addParagraph();
        table2_paragraph5_6.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r5_6 = table2_paragraph5_6.createRun();
        table2_r5_6.setText("居民身份证");
        table2_r5_6.setFontFamily("宋体");
        table2_r5_6.setFontSize(12);

        //第六行单元格
        XWPFTableCell table2_cell6_1 = table2.getRow(5).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph6_1 =  table2_cell6_1.addParagraph();
        table2_paragraph6_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r6_1 = table2_paragraph6_1.createRun();
        table2_r6_1.setText("经营者身份证件号码");
        table2_r6_1.setFontFamily("宋体");
        table2_r6_1.setFontSize(12);

        XWPFTableCell table2_cell6_2 = table2.getRow(5).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph6_2 =  table2_cell6_2.addParagraph();
        table2_paragraph6_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r6_2 = table2_paragraph6_2.createRun();
        table2_r6_2.setText(selfEmployed.getContactIdNum());
        table2_r6_2.setFontFamily("宋体");
        table2_r6_2.setFontSize(12);

        XWPFTableCell table2_cell6_3 = table2.getRow(5).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph6_3 =  table2_cell6_3.addParagraph();
        table2_paragraph6_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r6_3 = table2_paragraph6_3.createRun();
        table2_r6_3.setText("固定电话");
        table2_r6_3.setFontFamily("宋体");
        table2_r6_3.setFontSize(12);

        XWPFTableCell table2_cell6_4 = table2.getRow(5).getCell(3);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph6_4 =  table2_cell6_4.addParagraph();
        table2_paragraph6_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r6_4 = table2_paragraph6_4.createRun();
        table2_r6_4.setText("无");
        table2_r6_4.setFontFamily("宋体");
        table2_r6_4.setFontSize(12);

        XWPFTableCell table2_cell6_5 = table2.getRow(5).getCell(4);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph6_5 =  table2_cell6_5.addParagraph();
        table2_paragraph6_5.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r6_5 = table2_paragraph6_5.createRun();
        table2_r6_5.setText("移动电话");
        table2_r6_5.setFontFamily("宋体");
        table2_r6_5.setFontSize(12);

        XWPFTableCell table2_cell6_6 = table2.getRow(5).getCell(5);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph6_6 =  table2_cell6_6.addParagraph();
        table2_paragraph6_6.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r6_6 = table2_paragraph6_6.createRun();
        table2_r6_6.setText(selfEmployed.getContactPhone());
        table2_r6_6.setFontFamily("宋体");
        table2_r6_6.setFontSize(12);

        //第七行单元格
        XWPFTableCell table2_cell7_1 = table2.getRow(6).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph7_1 =  table2_cell7_1.addParagraph();
        table2_paragraph7_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r7_1 = table2_paragraph7_1.createRun();
        table2_r7_1.setText("职业状况");
        table2_r7_1.setFontFamily("宋体");
        table2_r7_1.setFontSize(12);

        XWPFTableCell table2_cell7_2 = table2.getRow(6).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph7_2 =  table2_cell7_2.addParagraph();
        table2_paragraph7_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r7_2 = table2_paragraph7_2.createRun();
        table2_r7_2.setText("待业");
        table2_r7_2.setFontFamily("宋体");
        table2_r7_2.setFontSize(12);

        XWPFTableCell table2_cell7_3 = table2.getRow(6).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph7_3 =  table2_cell7_3.addParagraph();
        table2_paragraph7_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r7_3 = table2_paragraph7_3.createRun();
        table2_r7_3.setText("经营者住所");
        table2_r7_3.setFontFamily("宋体");
        table2_r7_3.setFontSize(12);

        XWPFTableCell table2_cell7_4 = table2.getRow(6).getCell(3);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph7_4 =  table2_cell7_4.addParagraph();
        table2_paragraph7_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r7_4 = table2_paragraph7_4.createRun();
        table2_r7_4.setText(selfEmployed.getResidence());
        table2_r7_4.setFontFamily("宋体");
        table2_r7_4.setFontSize(12);

        //第八行单元格
        XWPFTableCell table2_cell8_1 = table2.getRow(7).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph8_1 =  table2_cell8_1.addParagraph();
        table2_paragraph8_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r8_1 = table2_paragraph8_1.createRun();
        table2_r8_1.setText("组成形式");
        table2_r8_1.setFontFamily("宋体");
        table2_r8_1.setFontSize(12);

        XWPFTableCell table2_cell8_2 = table2.getRow(7).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph8_2 =  table2_cell8_2.addParagraph();
        table2_paragraph8_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r8_2 = table2_paragraph8_2.createRun();
        table2_r8_2.setText("□个人经营            □家庭经营");
        table2_r8_2.setFontFamily("宋体");
        table2_r8_2.setFontSize(12);

        //第九行单元格
        XWPFTableCell table2_cell9_1 = table2.getRow(8).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph9_1 =  table2_cell9_1.addParagraph();
        table2_paragraph9_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r9_1 = table2_paragraph9_1.createRun();
        table2_r9_1.setText("参加经营的家庭成员姓名");
        table2_r9_1.setFontFamily("宋体");
        table2_r9_1.setFontSize(12);

        XWPFTableCell table2_cell9_2 = table2.getRow(8).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph9_2 =  table2_cell9_2.addParagraph();
        table2_paragraph9_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r9_2 = table2_paragraph9_2.createRun();
        table2_r9_2.setText("参加经营的家庭成员身份证件类型");
        table2_r9_2.setFontFamily("宋体");
        table2_r9_2.setFontSize(12);

        XWPFTableCell table2_cell9_3 = table2.getRow(8).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph9_3 =  table2_cell9_3.addParagraph();
        table2_paragraph9_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r9_3 = table2_paragraph9_3.createRun();
        table2_r9_3.setText("参加经营的家庭成员身份证件号码");
        table2_r9_3.setFontFamily("宋体");
        table2_r9_3.setFontSize(12);

        XWPFTableCell table2_cell9_4 = table2.getRow(8).getCell(3);
//        XWPFTableRow table2_cell9 = table2.getRow(8);
//        table2_cell9.setHeight(300);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph9_4 =  table2_cell9_4.addParagraph();
        table2_paragraph9_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r9_4 = table2_paragraph9_4.createRun();
        table2_r9_4.setText("参加经营的家庭成员政治面貌");
        table2_r9_4.setFontFamily("宋体");
        table2_r9_4.setFontSize(12);

        XWPFTableRow table2_cell10 = table2.getRow(9);
        table2_cell10.setHeight(800);
        XWPFTableRow table2_cell11 = table2.getRow(10);
        table2_cell11.setHeight(800);
        XWPFTableRow table2_cell12 = table2.getRow(11);
        table2_cell12.setHeight(800);
        //第十三行单元格
        XWPFTableCell table2_cell13_1 = table2.getRow(12).getCell(0);
        XWPFTableRow table2_cell13 = table2.getRow(12);
        table2_cell13.setHeight(1200);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph13_1 =  table2_cell13_1.addParagraph();
        table2_paragraph13_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r13_1 = table2_paragraph13_1.createRun();
        table2_r13_1.setText("（身份证件复、影印件粘贴处，可另附页）");
        table2_r13_1.setFontFamily("宋体");
        table2_r13_1.setFontSize(12);

        //第十四行单元格
        XWPFTableCell table2_cell14_1 = table2.getRow(13).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph14_1 =  table2_cell14_1.addParagraph();
        table2_paragraph14_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r14_1 = table2_paragraph14_1.createRun();
        table2_r14_1.setText("□注销（仅注销登记填写）");
        table2_r14_1.setFontFamily("宋体");
        table2_r14_1.setFontSize(16);
        table2_r14_1.setBold(true);//字体加粗

        //第十五行单元格
        XWPFTableCell table2_cell15_1 = table2.getRow(14).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph15_1 =  table2_cell15_1.addParagraph();
        table2_paragraph15_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r15_1 = table2_paragraph15_1.createRun();
        table2_r15_1.setText("注销方式");
        table2_r15_1.setFontFamily("宋体");
        table2_r15_1.setFontSize(12);

        XWPFTableCell table2_cell15_2 = table2.getRow(14).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph15_2 =  table2_cell15_2.addParagraph();
        table2_paragraph15_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r15_2 = table2_paragraph15_2.createRun();
        table2_r15_2.setText("□普通注销      □简易注销");
        table2_r15_2.setFontFamily("宋体");
        table2_r15_2.setFontSize(12);

        //第十六行单元格
        XWPFTableCell table2_cell16_1 = table2.getRow(15).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph16_1 =  table2_cell16_1.addParagraph();
        table2_paragraph16_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r16_1 = table2_paragraph16_1.createRun();
        table2_r16_1.setText("注销原因");
        table2_r16_1.setFontFamily("宋体");
        table2_r16_1.setFontSize(12);

        XWPFTableCell table2_cell16_2 = table2.getRow(15).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph16_2 =  table2_cell16_2.addParagraph();
        table2_paragraph16_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r16_2 = table2_paragraph16_2.createRun();
        table2_r16_2.setText("□停止经营      □转型升级为企业   □其他_________");
        table2_r16_2.setFontFamily("宋体");
        table2_r16_2.setFontSize(12);

        //第十七行单元格
        XWPFTableCell table2_cell17_1 = table2.getRow(16).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph17_1 =  table2_cell17_1.addParagraph();
        table2_paragraph17_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r17_1 = table2_paragraph17_1.createRun();
        table2_r17_1.setText("清税情况");
        table2_r17_1.setFontFamily("宋体");
        table2_r17_1.setFontSize(12);

        XWPFTableCell table2_cell17_2 = table2.getRow(16).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table2_paragraph17_2 =  table2_cell17_2.addParagraph();
        table2_paragraph17_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table2_r17_2 = table2_paragraph17_2.createRun();
        table2_r17_2.setText("□已清理完毕    □未涉及纳税义务");
        table2_r17_2.setFontFamily("宋体");
        table2_r17_2.setFontSize(12);

        doc.createParagraph().createRun().addBreak(BreakType.PAGE);

        //创建表格 17行*12列(创建table 时，会有一个默认一行一列的表格)
        XWPFTable table3 = doc.createTable(6,6);
        TableTools.borderTable(table3,14);//表格边框粗细
        // 设置表格宽度为A4纸最大宽度
//        TableTools.widthTable(table3, MiniTableRenderData.WIDTH_A4_FULL, 20);
        float[] colWidths3={3,3,3,3,3,3};
        TableTools.widthTable(table3,colWidths3);
        // 设置表格居中
        TableStyle style3 = new TableStyle();
        style3.setAlign(STJc.CENTER);
        TableTools.styleTable(table3, style3);
        //合并单元格
        TableTools.mergeCellsHorizonal(table3, 0, 0, 5);
        TableTools.mergeCellsHorizonal(table3, 1, 1, 5);
        TableTools.mergeCellsHorizonal(table3, 3, 0, 5);
        TableTools.mergeCellsHorizonal(table3, 4, 0, 5);
        TableTools.mergeCellsHorizonal(table3, 5, 0, 5);

        //第一行单元格
        XWPFTableCell table3_cell1_1 = table3.getRow(0).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph1_1 =  table3_cell1_1.addParagraph();
        table3_paragraph1_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r1_1 = table3_paragraph1_1.createRun();
        table3_r1_1.setText("□指定代表/委托代理人信息（必填项）");
        table3_r1_1.setFontFamily("宋体");
        table3_r1_1.setFontSize(16);
        table3_r1_1.setBold(true);//字体加粗

        //第二行单元格
        XWPFTableCell table3_cell2_1 = table3.getRow(1).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph2_1 =  table3_cell2_1.addParagraph();
        table3_paragraph2_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r2_1 = table3_paragraph2_1.createRun();
        table3_r2_1.setText("委托权限");
        table3_r2_1.setFontFamily("宋体");
        table3_r2_1.setFontSize(12);

        XWPFTableCell table3_cell2_2= table3.getRow(1).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph2_2 =  table3_cell2_2.addParagraph();
        table3_paragraph2_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r2_2 = table3_paragraph2_2.createRun();
        table3_r2_2.setText("1、同意不同意□核对登记材料中的复印件并签署核对意见；");
        table3_r2_2.setFontFamily("宋体");
        table3_r2_2.setFontSize(12);

        XWPFParagraph table3_paragraph2_2_1 =  table3_cell2_2.addParagraph();
        table3_paragraph2_2_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r2_2_1 = table3_paragraph2_2_1.createRun();
        table3_r2_2_1.setText("2、同意不同意□修改企业自备文件的错误；");
        table3_r2_2_1.setFontFamily("宋体");
        table3_r2_2_1.setFontSize(12);

        XWPFParagraph table3_paragraph2_2_2 =  table3_cell2_2.addParagraph();
        table3_paragraph2_2_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r2_2_2 = table3_paragraph2_2_2.createRun();
        table3_r2_2_2.setText("3、同意不同意□修改有关表格的填写错误；");
        table3_r2_2_2.setFontFamily("宋体");
        table3_r2_2_2.setFontSize(12);

        XWPFParagraph table3_paragraph2_2_3 =  table3_cell2_2.addParagraph();
        table3_paragraph2_2_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r2_2_3 = table3_paragraph2_2_3.createRun();
        table3_r2_2_3.setText("4、同意不同意□领取营业执照和有关文书。");
        table3_r2_2_3.setFontFamily("宋体");
        table3_r2_2_3.setFontSize(12);

        //第三行单元格
        XWPFTableCell table3_cell3_1 = table3.getRow(2).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph3_1 =  table3_cell3_1.addParagraph();
        table3_paragraph3_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r3_1 = table3_paragraph3_1.createRun();
        table3_r3_1.setText("固定电话");
        table3_r3_1.setFontFamily("宋体");
        table3_r3_1.setFontSize(12);

        XWPFTableCell table3_cell3_2 = table3.getRow(2).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph3_2 =  table3_cell3_2.addParagraph();
        table3_paragraph3_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r3_2 = table3_paragraph3_2.createRun();
        table3_r3_2.setText("无");
        table3_r3_2.setFontFamily("宋体");
        table3_r3_2.setFontSize(12);

        XWPFTableCell table3_cell3_3 = table3.getRow(2).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph3_3 =  table3_cell3_3.addParagraph();
        table3_paragraph3_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r3_3 = table3_paragraph3_3.createRun();
        table3_r3_3.setText("移动电话");
        table3_r3_3.setFontFamily("宋体");
        table3_r3_3.setFontSize(12);

        XWPFTableCell table3_cell3_4 = table3.getRow(2).getCell(3);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph3_4 =  table3_cell3_4.addParagraph();
        table3_paragraph3_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r3_4 = table3_paragraph3_4.createRun();
        table3_r3_4.setText(selfEmployed.getApplyPhone());
        table3_r3_4.setFontFamily("宋体");
        table3_r3_4.setFontSize(12);

        XWPFTableCell table3_cell3_5 = table3.getRow(2).getCell(4);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph3_5 =  table3_cell3_5.addParagraph();
        table3_paragraph3_5.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r3_5 = table3_paragraph3_5.createRun();
        table3_r3_5.setText("指定代表/委托代理人签字");
        table3_r3_5.setFontFamily("宋体");
        table3_r3_5.setFontSize(12);

        XWPFTableCell table3_cell3_6 = table3.getRow(2).getCell(5);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph3_6 =  table3_cell3_6.addParagraph();
        table3_paragraph3_6.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r3_6 = table3_paragraph3_6.createRun();
//        table3_r3_6.setText("委托权限");
//        table3_r3_6.setFontFamily("宋体");
//        table3_r3_6.setFontSize(12);

        //第四行单元格
        XWPFTableCell table3_cell4_1 = table3.getRow(3).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph4_1 =  table3_cell4_1.addParagraph();
        table3_paragraph4_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r4_1 = table3_paragraph4_1.createRun();
        table3_r4_1.setText("（指定代表或者委托代理人身份证件复、影印件粘贴处）");
        table3_r4_1.setFontFamily("宋体");
        table3_r4_1.setFontSize(12);

        //第五行单元格
        XWPFTableCell table3_cell5_1 = table3.getRow(4).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph5_1 =  table3_cell5_1.addParagraph();
        table3_paragraph5_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table3_r5_1 = table3_paragraph5_1.createRun();
        table3_r5_1.setText("□申请人签署（必填项）");
        table3_r5_1.setFontFamily("宋体");
        table3_r5_1.setFontSize(16);
        table3_r5_1.setBold(true);

        //第六行单元格
        XWPFTableCell table3_cell6_1 = table3.getRow(5).getCell(0);
        XWPFTableRow table3_cell6 = table3.getRow(5);
        table3_cell6.setHeight(4600);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table3_paragraph6_1 =  table3_cell6_1.addParagraph();
        table3_paragraph6_1.setAlignment(ParagraphAlignment.CENTER);//对齐方式
        XWPFRun table3_r6_1 = table3_paragraph6_1.createRun();
        table3_r6_1.setText("本申请人和签字人承诺如下，并承担相应的法律责任。");
        table3_r6_1.setFontFamily("宋体");
        table3_r6_1.setFontSize(12);
        table3_r6_1.setBold(true);

        XWPFParagraph table3_paragraph6_1_1 =  table3_cell6_1.addParagraph();
        table3_paragraph6_1_1.setAlignment(ParagraphAlignment.LEFT);
        table3_paragraph6_1_1.setFirstLineIndent(400);//首行缩进
        XWPFRun table3_r6_1_1 = table3_paragraph6_1_1.createRun();
        table3_r6_1_1.setText("（一）填报的信息及提交的材料真实、准确、有效、完整。");
        table3_r6_1_1.setFontFamily("宋体");
        table3_r6_1_1.setFontSize(12);

        XWPFParagraph table3_paragraph6_1_2 =  table3_cell6_1.addParagraph();
        table3_paragraph6_1_2.setAlignment(ParagraphAlignment.LEFT);
        table3_paragraph6_1_2.setFirstLineIndent(400);//首行缩进
        XWPFRun table3_r6_1_2 = table3_paragraph6_1_2.createRun();
        table3_r6_1_2.setText("（二）使用的名称符合市场主体名称有关要求，不含有损国家、社会公共利益或违背公序良俗及有其他不良影响的内容；名称与他人使用的名称近似侵犯他人合法权益的，依法承担法律责任；如使用的名称被登记机关认定为不适宜名称，将主动配合登记机关进行纠正。");
        table3_r6_1_2.setFontFamily("宋体");
        table3_r6_1_2.setFontSize(12);

        XWPFParagraph table3_paragraph6_1_3 =  table3_cell6_1.addParagraph();
        table3_paragraph6_1_3.setAlignment(ParagraphAlignment.LEFT);
        table3_paragraph6_1_3.setFirstLineIndent(400);//首行缩进
        XWPFRun table3_r6_1_3 = table3_paragraph6_1_3.createRun();
        table3_r6_1_3.setText("（三）已依法取得住所（经营场所）使用权，申请登记的住所（经营场所）信息与实际一致。以网络经营场所作为经营场所登记的，仅通过互联网开展经营活动，不擅自改变其住宅房屋用途用于从事线下生产经营活动。");
        table3_r6_1_3.setFontFamily("宋体");
        table3_r6_1_3.setFontSize(12);

        XWPFParagraph table3_paragraph6_1_4 =  table3_cell6_1.addParagraph();
        table3_paragraph6_1_4.setAlignment(ParagraphAlignment.LEFT);
        table3_paragraph6_1_4.setFirstLineIndent(400);//首行缩进
        XWPFRun table3_r6_1_4 = table3_paragraph6_1_4.createRun();
        table3_r6_1_4.setText("（四）经营范围涉及法律、行政法规、国务院决定规定、地方行政法规和地方规章规定，需要办理许可的，在取得相关部门批准前，不从事相关经营活动。");
        table3_r6_1_4.setFontFamily("宋体");
        table3_r6_1_4.setFontSize(12);

        table3_r6_1_4.addBreak();

        XWPFParagraph table3_paragraph6_1_5 =  table3_cell6_1.addParagraph();
        table3_paragraph6_1_5.setAlignment(ParagraphAlignment.LEFT);
        table3_paragraph6_1_5.setFirstLineIndent(400);//首行缩进
        XWPFRun table3_r6_1_5 = table3_paragraph6_1_5.createRun();
        table3_r6_1_5.setText("经营者签字：");
        table3_r6_1_5.setFontFamily("宋体");
        table3_r6_1_5.setFontSize(12);

        XWPFParagraph table3_paragraph6_1_6 =  table3_cell6_1.addParagraph();
        table3_paragraph6_1_6.setAlignment(ParagraphAlignment.LEFT);
        table3_paragraph6_1_6.setFirstLineIndent(400);//首行缩进
        XWPFRun table3_r6_1_6 = table3_paragraph6_1_6.createRun();
        table3_r6_1_6.setText("参加经营的家庭成员签字（仅家庭经营）：\t");
        table3_r6_1_6.setFontFamily("宋体");
        table3_r6_1_6.setFontSize(12);

        XWPFParagraph table3_paragraph6_1_7 =  table3_cell6_1.addParagraph();
        table3_paragraph6_1_7.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun table3_r6_1_7 = table3_paragraph6_1_7.createRun();
        table3_r6_1_7.setText("年    月    日  ");
        table3_r6_1_7.setFontFamily("宋体");
        table3_r6_1_7.setFontSize(12);

        XWPFParagraph p20= doc.createParagraph();

        XWPFParagraph p9= doc.createParagraph();
        p9.setAlignment(ParagraphAlignment.LEFT);
        p9.setFirstLineIndent(400);//首行缩进
        XWPFRun run9 =  p9.createRun();
        run9.setText("注：1、申请登记为家庭经营的，以主持经营者作为经营者登记，由全体参加经营家庭成员在“申请人签署”中签字予以确认。");

        XWPFParagraph p10= doc.createParagraph();
        p10.setAlignment(ParagraphAlignment.LEFT);
        p10.setFirstLineIndent(400);//首行缩进
        XWPFRun run10 =  p10.createRun();
        run10.setText("2、香港、澳门、台湾居民个体工商户或台湾农民个体工商户不填写本申请书“经营者”一栏内容，分别填写“个体工商户经营者（港澳居民）登记表”或“个体工商户经营者（台湾居民、农民）登记表”。港、澳居民个体工商户和台湾农民个体工商户应当注明经营场所的面积和从业人数");

        doc.createParagraph().createRun().addBreak(BreakType.PAGE);
        //创建段落(标题)
        XWPFParagraph title_p2 = doc.createParagraph();
        title_p2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runTitle2 = title_p2.createRun();
        runTitle2.setText("联络员信息");
        runTitle2.setBold(true);
        runTitle2.setFontSize(24);
        runTitle2.setFontFamily("宋体");
        runTitle2.addCarriageReturn();//回车键
        runTitle2.setKerning(30);

        //创建表格 4行*4列(创建table 时，会有一个默认一行一列的表格)
        XWPFTable table4 = doc.createTable(4,4);
        TableTools.borderTable(table4,14);//表格边框粗细
        // 设置表格宽度为A4纸最大宽度
//        TableTools.widthTable(table4, MiniTableRenderData.WIDTH_A4_FULL, 20);
        float[] colWidths4={5,4,5,4};
        TableTools.widthTable(table4,colWidths4);
        // 设置表格居中
        TableStyle style4 = new TableStyle();
        style4.setAlign(STJc.CENTER);
        TableTools.styleTable(table4, style4);
        //合并单元格
        TableTools.mergeCellsHorizonal(table4, 3, 0, 3);

        //第一行单元格
        XWPFTableCell table4_cell1_1 = table4.getRow(0).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph1_1 =  table4_cell1_1.addParagraph();
        table4_paragraph1_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r1_1 = table4_paragraph1_1.createRun();
        table4_r1_1.setText("姓    名");
        table4_r1_1.setFontFamily("宋体");
        table4_r1_1.setFontSize(12);

        XWPFTableCell table4_cell1_2 = table4.getRow(0).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph1_2 =  table4_cell1_2.addParagraph();
        table4_paragraph1_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r1_2 = table4_paragraph1_2.createRun();
        table4_r1_2.setText(selfEmployed.getContactName());
        table4_r1_2.setFontFamily("宋体");
        table4_r1_2.setFontSize(12);

        XWPFTableCell table4_cell1_3 = table4.getRow(0).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph1_3 =  table4_cell1_3.addParagraph();
        table4_paragraph1_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r1_3 = table4_paragraph1_3.createRun();
        table4_r1_3.setText("固定电话");
        table4_r1_3.setFontFamily("宋体");
        table4_r1_3.setFontSize(12);

        XWPFTableCell table4_cell1_4 = table4.getRow(0).getCell(3);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph1_4 =  table4_cell1_4.addParagraph();
        table4_paragraph1_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r1_4 = table4_paragraph1_4.createRun();
        table4_r1_4.setText("无");
        table4_r1_4.setFontFamily("宋体");
        table4_r1_4.setFontSize(12);

        //第二行单元格
        XWPFTableCell table4_cell2_1 = table4.getRow(1).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph2_1 =  table4_cell2_1.addParagraph();
        table4_paragraph2_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r2_1 = table4_paragraph2_1.createRun();
        table4_r2_1.setText("移动电话");
        table4_r2_1.setFontFamily("宋体");
        table4_r2_1.setFontSize(12);

        XWPFTableCell table4_cell2_2 = table4.getRow(1).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph2_2 =  table4_cell2_2.addParagraph();
        table4_paragraph2_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r2_2 = table4_paragraph2_2.createRun();
        table4_r2_2.setText(selfEmployed.getContactPhone());
        table4_r2_2.setFontFamily("宋体");
        table4_r2_2.setFontSize(12);

        XWPFTableCell table4_cell2_3 = table4.getRow(1).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph2_3 =  table4_cell2_3.addParagraph();
        table4_paragraph2_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r2_3 = table4_paragraph2_3.createRun();
        table4_r2_3.setText("电子邮箱");
        table4_r2_3.setFontFamily("宋体");
        table4_r2_3.setFontSize(12);

        XWPFTableCell table4_cell2_4 = table4.getRow(1).getCell(3);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph2_4 =  table4_cell2_4.addParagraph();
        table4_paragraph2_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r2_4 = table4_paragraph2_4.createRun();
        table4_r2_4.setText(selfEmployed.getMail());
        table4_r2_4.setFontFamily("宋体");
        table4_r2_4.setFontSize(12);

        //第三行单元格
        XWPFTableCell table4_cell3_1 = table4.getRow(2).getCell(0);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph3_1 =  table4_cell3_1.addParagraph();
        table4_paragraph3_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r3_1 = table4_paragraph3_1.createRun();
        table4_r3_1.setText("身份证件类型");
        table4_r3_1.setFontFamily("宋体");
        table4_r3_1.setFontSize(12);

        XWPFTableCell table4_cell3_2 = table4.getRow(2).getCell(1);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph3_2 =  table4_cell3_2.addParagraph();
        table4_paragraph3_2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r3_2 = table4_paragraph3_2.createRun();
        table4_r3_2.setText("居民身份证");
        table4_r3_2.setFontFamily("宋体");
        table4_r3_2.setFontSize(12);

        XWPFTableCell table4_cell3_3 = table4.getRow(2).getCell(2);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph3_3 =  table4_cell3_3.addParagraph();
        table4_paragraph3_3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r3_3 = table4_paragraph3_3.createRun();
        table4_r3_3.setText("身份证件号码");
        table4_r3_3.setFontFamily("宋体");
        table4_r3_3.setFontSize(12);

        XWPFTableCell table4_cell3_4 = table4.getRow(2).getCell(3);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph3_4 =  table4_cell3_4.addParagraph();
        table4_paragraph3_4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r3_4 = table4_paragraph3_4.createRun();
        table4_r3_4.setText(selfEmployed.getContactIdNum());
        table4_r3_4.setFontFamily("宋体");
        table4_r3_4.setFontSize(12);

        //第四行单元格
        XWPFTableCell table4_cell4_1 = table4.getRow(3).getCell(0);
        XWPFTableRow table4_cell4 = table4.getRow(3);
        table4_cell4.setHeight(3200);
        // 获取单元格段落后设置对齐方式
        XWPFParagraph table4_paragraph4_1 =  table4_cell4_1.addParagraph();
        table4_paragraph4_1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun table4_r4_1 = table4_paragraph4_1.createRun();
        table4_r4_1.setText("（身份证件复、影印件粘贴处，可另附）");
        table4_r4_1.setFontFamily("宋体");
        table4_r4_1.setFontSize(12);

        XWPFParagraph p21= doc.createParagraph();

        XWPFParagraph p14= doc.createParagraph();
        p14.setFirstLineIndent(400);//首行缩进
        XWPFRun run14 =  p14.createRun();
        run14.setText("注：1、联络员主要负责本企业与企业登记机关的联系沟通，以本人个人信息登录国家企业信用信息公示系统依法向社会公示本企业有关信息等。联络员应了解企业登记相关法规和企业信息公示有关规定。");

        XWPFParagraph p15= doc.createParagraph();
        p15.setAlignment(ParagraphAlignment.LEFT);
        p15.setFirstLineIndent(400);//首行缩进
        XWPFRun run15 =  p15.createRun();
        run15.setText("2、《联络员信息》未变更的不需重填。");

        //把word写到本地
        try (FileOutputStream out = new FileOutputStream(file)) {
            doc.write(out);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        String pdfFileName=nowDate+guid+"转换结果.pdf";

        byte[] result=   docxToPdf(new FileInputStream(file),configProps.getName()+pdfFileName);
        System.out.println("result=="+result);

//        FileInputStream fileInputStream = null;
//        FileOutputStream  fileOutputStream=null;
//        XWPFDocument doc1 =new XWPFDocument();
//        try {
//            // 读取docx文件
//            fileInputStream = new FileInputStream(file);
//            doc1 = new XWPFDocument(fileInputStream);
//            doc1.createStyles();
//            PdfOptions pdfOptions = PdfOptions.create();
//            // 输出路径
//            fileOutputStream= new FileOutputStream(new File(configProps.getName()+nowDate+guid+"转换结果.pdf"));
//            // 调用转换
//            PdfConverter.getInstance().convert(doc1, fileOutputStream, pdfOptions);
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }finally {
//            doc1.close();
//            fileInputStream.close();
//            fileOutputStream.close();
//        }

        //word转html
//        String sourceFileName = configProps.getName()+fileName;
//        String targetFileName = configProps.getName()+nowDate+guid+"转换结果.html";
//        String imagePathStr = configProps.getName();
//        OutputStreamWriter outputStreamWriter = null;
//        try {
//            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
//            document.createStyles();
//            XHTMLOptions options = XHTMLOptions.create();
//            // 存放图片的文件夹
//            options.setExtractor(new FileImageExtractor(new File(imagePathStr)));
//            // html中图片的路径
//            options.URIResolver(new BasicURIResolver("image"));
//            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
//            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
//            xhtmlConverter.convert(document, outputStreamWriter, options);
//        } finally {
//            if (outputStreamWriter != null) {
//                outputStreamWriter.close();
//            }
//
//        }

        //插入文件名到数据库
        SelfEmployed selfEmployed2=new SelfEmployed();
        selfEmployed2.setFileName8(pdfFileName);
        selfEmployed2.setSelfId(selfId);
        selfEmployedService.updateSelfEmployed(selfEmployed2);
        List<SelfEmployedVo> selfEmployedVos2= selfEmployedService.selectEmployedJoinReview(null,selfEmployedVo);
        return selfEmployedVos2;

    }

}
