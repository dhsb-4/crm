package com.t248.crm.config;


import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.t248.crm.util.PdfUtil;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ViewPDF extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        String fileName = new Date().getTime() + "_quotation.pdf"; // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "filename=" + new String(fileName.getBytes(), "iso8859-1"));
        List<Object> products = (List<Object>) model.get("sheet");
        PdfUtil pdfUtil = new PdfUtil();
        pdfUtil.createPDF(document, writer, products);
    }
}

