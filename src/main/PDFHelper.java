package main;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import interfaces.Lorder;
import util.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

/**
 * Created by huan on 2017/11/28.
 */
public class PDFHelper {


    private static PDFHelper instance;

    public static PDFHelper getInstance() {
        if(instance==null){
            instance = new PDFHelper();
        }
        return instance;
    }

    /**
     * 创建一个简单的pdf文件
     */
    public static void makePDF01(String filePath) {
        try {

            // 1.新建document对象
            Document document = new Document();

            // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
            // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // 3.打开文档
            document.open();

            // 4.添加一个内容段落
            document.add(new Paragraph("Hello World!"));

            // 5.关闭文档
            document.close();

        } catch (Exception e) {
            System.out.println("pdf error:" + e.getMessage());
        }
        System.out.println("pdf end");
    }


    /**
     * PDF文件设置文件属性
     */
    public static void makePDF02(String filePath) {
        try {
           //创建文件
            Document document = new Document();


            //建立一个书写器
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            //打开文件
            document.open();
            //添加内容
            document.add(new Paragraph("Some content here"));

            //设置属性
            //标题
            document.addTitle("this is a title");
            //作者
            document.addAuthor("H__D");
            //主题
            document.addSubject("this is subject");
            //关键字
            document.addKeywords("Keywords");
            //创建时间
            document.addCreationDate();
            //应用程序
            document.addCreator("hd.com");

            //关闭文档
            document.close();
            //关闭书写器
            writer.close();
        } catch (Exception e) {
            System.out.println("pdf error:" + e.getMessage());
        }
        System.out.println("pdf end");
    }

    /**
     * PDF中添加图片
     */
    public static void makePDF03(String filePath,String imgPath ,String imgUrl) {
        try {
            //创建文件
                    Document document = new Document();
                    //建立一个书写器
                     PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
                     //打开文件
                     document.open();
                     //添加内容
                     document.add(new Paragraph("HD content here"));

                     //图片1
                     Image image1 = Image.getInstance(imgPath);
                     //设置图片位置的x轴和y周
                     image1.setAbsolutePosition(100f, 550f);
                     //设置图片的宽度和高度
                     image1.scaleAbsolute(200, 200);
                     //将图片1添加到pdf文件中
                     document.add(image1);

                     //图片2
                     Image image2 = Image.getInstance(new URL(imgUrl));
                     //将图片2添加到pdf文件中
                     document.add(image2);

                    //关闭文档
                     document.close();
                     //关闭书写器
                     writer.close();
        } catch (Exception e) {
            System.out.println("pdf error:" + e.getMessage());
        }
        System.out.println("pdf end");
    }

    /**
     * PDF中创建表格
     * @param filePath
     */
    public static void makePDF04(String filePath) {
        try {
                //创建文件
                Document document = new Document();
                //建立一个书写器
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
                //打开文件
                document.open();
                //添加内容
                document.add(new Paragraph("HD content here"));

                // 3列的表.
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100); // 宽度100%填充
                table.setSpacingBefore(10f); // 前间距
                table.setSpacingAfter(10f); // 后间距

            List<PdfPRow> listRow = table.getRows();
                //设置列宽
                float[] columnWidths = { 1f, 2f, 3f };
                table.setWidths(columnWidths);

                //行1
                PdfPCell cells1[]= new PdfPCell[3];
                PdfPRow row1 = new PdfPRow(cells1);

                //单元格
                cells1[0] = new PdfPCell(new Paragraph("111"));//单元格内容
                cells1[0].setBorderColor(Color.cyan);//边框验证
                cells1[0].setPaddingLeft(20);//左填充20
                cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

                cells1[1] = new PdfPCell(new Paragraph("222"));
                cells1[2] = new PdfPCell(new Paragraph("333"));

                //行2
                PdfPCell cells2[]= new PdfPCell[3];
                PdfPRow row2 = new PdfPRow(cells2);
                cells2[0] = new PdfPCell(new Paragraph("444"));

                //把第一行添加到集合
                listRow.add(row1);
                listRow.add(row2);
                //把表格添加到文件中
                document.add(table);

                //关闭文档
                document.close();
                //关闭书写器
                writer.close();
        } catch (Exception e) {
            System.out.println("pdf error:" + e.getMessage());
        }
        System.out.println("pdf end");
    }

    //PDF中创建列表
    public static void makePDF05(String filePath) {
        try {
            //创建文件
            Document document = new Document();
            //建立一个书写器
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            //打开文件
            document.open();
            //添加内容
            document.add(new Paragraph("HD content here"));

            //添加有序列表
            com.lowagie.text.List orderedList = new com.lowagie.text.List(com.lowagie.text.List.ORDERED);
            orderedList.add(new ListItem("Item one"));
            orderedList.add(new ListItem("Item two"));
            orderedList.add(new ListItem("Item three"));
            document.add(orderedList);

            //关闭文档
            document.close();
            //关闭书写器
            writer.close();
        } catch (Exception e) {
            System.out.println("pdf error:" + e.getMessage());
        }
        System.out.println("pdf end");
    }

    public static void makePDF06(String filePath) {
        try {
                //创建文件
                Document document = new Document();
                //建立一个书写器
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
                //打开文件
                document.open();

                //中文字体,解决中文不能显示问题
                BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

                //蓝色字体
                Font blueFont = new Font(bfChinese);
                blueFont.setColor(Color.BLUE);
                //段落文本
                Paragraph paragraphBlue = new Paragraph("paragraphOne blue front", blueFont);
                document.add(paragraphBlue);

                //绿色字体
                Font greenFont = new Font(bfChinese);
                greenFont.setColor(Color.GREEN);
                //创建章节
                Paragraph chapterTitle = new Paragraph("段落标题xxxx", greenFont);
                Chapter chapter1 = new Chapter(chapterTitle, 1);
                chapter1.setNumberDepth(0);

                Paragraph sectionTitle = new Paragraph("部分标题", greenFont);
                Section section1 = chapter1.addSection(sectionTitle);

                Paragraph sectionContent = new Paragraph("部分内容", blueFont);
                section1.add(sectionContent);

                //将章节添加到文章中
                document.add(chapter1);

                //关闭文档
                document.close();
                //关闭书写器
                writer.close();

        } catch (Exception e) {
            System.out.println("pdf error:" + e.getMessage());
        }
        System.out.println("pdf end");
    }

    public static void makePDF0400(String filePath) {
        try {

        } catch (Exception e) {
            System.out.println("pdf error:" + e.getMessage());
        }
        System.out.println("pdf end");
    }


}
