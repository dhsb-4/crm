package com.t248.crm.service.rpt;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface RptService {

    public XSSFWorkbook showKH();

    public XSSFWorkbook showGC();

    public XSSFWorkbook showFW();
}
