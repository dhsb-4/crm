package com.t248.crm.service.rpt.impl;

import com.t248.crm.entity.Customer;
import com.t248.crm.entity.Orders;
import com.t248.crm.entity.OrdersLine;
import com.t248.crm.service.customer.CustomerService;
import com.t248.crm.service.dict.DictService;
import com.t248.crm.service.orders.OrdersService;
import com.t248.crm.service.rpt.RptService;
import com.t248.crm.vo.Dictinit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RptServictImpl implements RptService {
    @Resource
    private CustomerService customerService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private DictService dictService;


    @Override
    public XSSFWorkbook showKH() {
        List<Customer> list = customerService.findList();//查出数据库数据
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("编号");//第一列
        titleRow.createCell(1).setCellValue("客户名称");
        titleRow.createCell(2).setCellValue("金额");
        int cell = 1;
        for (Customer customer : list) {
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(customer.getCustId());
            row.createCell(1).setCellValue(customer.getCustName());//将数据库的数据遍历出来
            Double sum = 0.0;
            for (Orders o: ordersService.findByOdrCustomerNo(customer.getCustNo())) {
                for (OrdersLine ordersLine:o.getOrdersLines()){
                    sum+=ordersLine.getOddPrice();
                }
            }
            row.createCell(2).setCellValue(sum);
            cell++;
        }
        return wb;
    }

    @Override
    public XSSFWorkbook showGC() {
        List<Dictinit> list = dictService.findLelList();;//查出数据库数据
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("编号");//第一列
        titleRow.createCell(1).setCellValue("等级");
        titleRow.createCell(2).setCellValue("数量");
        int cell = 1;
        for (Dictinit dictinit : list) {
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(dictinit.getId());
            row.createCell(1).setCellValue(dictinit.getName());//将数据库的数据遍历出来
            row.createCell(2).setCellValue(dictinit.getCount());
            cell++;
        }
        return wb;
    }

    @Override
    public XSSFWorkbook showFW() {
        List<Dictinit> list = dictService.findFwList();;//查出数据库数据
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("编号");//第一列
        titleRow.createCell(1).setCellValue("条目");
        titleRow.createCell(2).setCellValue("数量");
        int cell = 1;
        for (Dictinit dictinit : list) {
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(cell);
            row.createCell(1).setCellValue(dictinit.getName());//将数据库的数据遍历出来
            row.createCell(2).setCellValue(dictinit.getCount());
            cell++;
        }
        return wb;
    }
}
