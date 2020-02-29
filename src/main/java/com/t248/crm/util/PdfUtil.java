package com.t248.crm.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.t248.crm.entity.Customer;
import com.t248.crm.entity.Orders;
import com.t248.crm.entity.OrdersLine;
import com.t248.crm.repository.OrdersRepository;
import com.t248.crm.service.orders.OrdersService;
import com.t248.crm.vo.Custinit;
import com.t248.crm.vo.Dictinit;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfUtil {

    @Resource
    private OrdersService ordersService;


    public void createPDF(Document document, PdfWriter writer, List<Object> products) throws IOException {
        //Document document = new Document(PageSize.A4);
        try {
            document.addTitle("sheet of product");
            document.addAuthor("scurry");
            document.addSubject("product sheet.");
            document.addKeywords("product.");
            document.open();
            PdfPTable table = createTable(writer, products);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static PdfPTable createTable(PdfWriter writer, List<Object> products) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(3);//生成一个两列的表格
        PdfPCell cell = null;
        int size = 20;
        Font font = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED));

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) instanceof Custinit) {
                Custinit customer = (Custinit) products.get(i);

                cell = new PdfPCell(new Phrase(customer.getId() + "", font));//产品编号
                cell.setFixedHeight(size);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(customer.getName(), font));//产品编号
                cell.setFixedHeight(size);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(customer.getJe() + "", font));//产品编号
                cell.setFixedHeight(size);
                table.addCell(cell);
            } else {
                Dictinit customer = (Dictinit) products.get(i);

                cell = new PdfPCell(new Phrase(customer.getId() + "", font));//产品编号
                cell.setFixedHeight(size);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(customer.getName(), font));//产品编号
                cell.setFixedHeight(size);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(customer.getCount() + "", font));//产品编号
                cell.setFixedHeight(size);
                table.addCell(cell);
            }
        }
        return table;
    }
}

