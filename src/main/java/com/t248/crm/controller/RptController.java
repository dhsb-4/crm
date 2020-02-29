package com.t248.crm.controller;

import com.github.abel533.echarts.*;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.t248.crm.config.ViewPDF;
import com.t248.crm.entity.*;
import com.t248.crm.service.customer.CustomerService;
import com.t248.crm.service.dict.DictService;
import com.t248.crm.service.lost.LostService;
import com.t248.crm.service.orders.OrdersService;
import com.t248.crm.service.product.ProductService;
import com.t248.crm.service.rpt.RptService;
import com.t248.crm.service.storage.StorageService;
import com.t248.crm.vo.Custinit;
import com.t248.crm.vo.Dictinit;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class RptController {

    @Resource
    private LostService lostService;

    @Resource
    private CustomerService customerService;

    @Resource
    private OrdersService ordersService;

    @Resource
    private DictService dictService;

    @Resource
    private RptService rptService;

    @Resource
    private ProductService productService;

    @Resource
    private StorageService storageService;


    @RequestMapping(value = "contrRpt/list")
    public String contrRpt(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex,
                           @RequestParam(required = false) String svrCustName) {

        Map<String, Double> map = new HashMap<String, Double>();
        Pageable pageable = PageRequest.of(pageIndex - 1, 3);
        Page<Customer> pager = customerService.findo(svrCustName, pageable);
        for (Customer customer : pager.getContent()) {
            Double sum = 0.0;
            for (Orders o : ordersService.findByOdrCustomerNo(customer.getCustNo())) {
                for (OrdersLine ordersLine : o.getOrdersLines()) {
                    sum += ordersLine.getOddPrice();
                }
            }

            map.put(customer.getCustId() + "", sum);
        }
        model.addAttribute("pager", pager);
        model.addAttribute("map", map);
        model.addAttribute("svrCustName", svrCustName);


        return "rpt/contrRptList";
    }

    @RequestMapping(value = "lostRpt/list")
    public String lostRpt(@RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String lstCustName
            , @RequestParam(required = false) String lstCustManagerName
            , Model model) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 2);  //查询5条数据
        Page<Lost> pager = lostService.findr(lstCustName, lstCustManagerName, pageable);

        model.addAttribute("lstCustName", lstCustName);
        model.addAttribute("lstCustManagerName", lstCustManagerName);
        model.addAttribute("pager", pager);
        return "rpt/lostRptList";
    }

    @RequestMapping("/analyze/tub")
    @ResponseBody
    public Option getEchartsPieOption() {
        List<Customer> list = customerService.findList();
        Option option = new Option();
        //标题
        Title title = new Title();
        title.setText("客户贡献图");
        title.setX("center");
        option.setTitle(title);

        //提示框
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.item);
        tooltip.formatter("{a} <br/>{b} : {c} ({d}%)");
        option.setTooltip(tooltip);

        //Legend
        Legend legend = new Legend();
        legend.setOrient(Orient.vertical);
        legend.setLeft("left");


        List<String> l = new ArrayList<>();

        //饼状图
        Pie pie = new Pie();
        //对数据进行简单处理
        List<Map> mapList = new ArrayList<>();
        for (Customer customer : list) {
            Double ds = new Double(0);
            Map<String, String> addMap = new HashMap<>();
            addMap.put("name", customer.getCustName());
            for (Orders orders : ordersService.findByOdrCustomerNo(customer.getCustNo())) {
                for (OrdersLine line :
                        orders.getOrdersLines()) {
                    ds += line.getOddPrice();
                }
            }
            l.add(customer.getCustName());
            addMap.put("value", String.valueOf(ds));
            mapList.add(addMap);
        }
        legend.setData(l);
        option.setLegend(legend);
        //封装pie
        pie.setData(mapList);
        pie.setName("上映年代");
        pie.setRadius("55%");
        String[] centerArray = {"50%", "60%"};
        pie.setCenter(centerArray);

        option.series(pie);

        return option;
    }

    /*
     * 获取柱状图 json数据
     * */
    @RequestMapping("/analyze/tuz")
    @ResponseBody
    public Option getEchartsOption() {
        List<Dictinit> list = dictService.findLelList();
        //echarts option 对象
        Option option = new Option();
        option.title("客户构成分析").tooltip(Trigger.axis).legend("人数");
        //x轴为值轴
        option.yAxis(new ValueAxis().boundaryGap(0d, 1));

        //y轴为类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("人数");
        //循环数据
        for (Dictinit d : list) {
            //设置类目
            category.data(d.getName());
            //类目对应的柱状图
            bar.data(d.getCount());
        }

        Grid grid = new Grid();
        grid.setLeft("20%");
        grid.setWidth("60%");
        option.setGrid(grid);
        option.xAxis(category);
        option.series(bar);

        return option;
    }

    /*
     * 获取柱状图 json数据
     * */
    @RequestMapping("/analyze/tuf")
    @ResponseBody
    public Option getEchartsOptionf() {
        List<Dictinit> list = dictService.findFwList();
        //echarts option 对象
        Option option = new Option();
        option.title("客户服务分析").tooltip(Trigger.axis).legend("服务数量");
        //x轴为值轴
        option.yAxis(new ValueAxis().boundaryGap(0d, 1));

        //y轴为类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("服务数量");
        //循环数据
        for (Dictinit d : list) {
            //设置类目
            category.data(d.getName());
            //类目对应的柱状图
            bar.data(d.getCount());
        }

        Grid grid = new Grid();
        grid.setLeft("20%");
        grid.setWidth("60%");
        option.setGrid(grid);
        option.xAxis(category);
        option.series(bar);

        return option;
    }

    @RequestMapping(value = "/consRpt/list")
    public String consRpt(Model model) {

        model.addAttribute("dict", dictService.findLelList());

        return "rpt/consRptList";
    }

    @RequestMapping(value = "/svrRpt/list")
    public String svrRpt(Model model) {

        model.addAttribute("dict", dictService.findFwList());

        return "rpt/svrRptList";
    }

    @RequestMapping(value = "/excel/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void contrExcel(HttpServletResponse response, @PathVariable String type) {
        XSSFWorkbook wb = null;
        String fileName = null;
        if (type.equals("kh")) {
            wb = rptService.showKH();
            fileName = "客户贡献分析.xlsx";
        } else if (type.equals("gc")) {
            wb = rptService.showGC();
            fileName = "客户构成分析.xlsx";
        } else if (type.equals("fw")) {
            wb = rptService.showFW();
            fileName = "客户服务分析.xlsx";
        }

        OutputStream outputStream = null;
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/pdf/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ModelAndView printPdf(@PathVariable String type) throws Exception {
        Map<String, Object> model = new HashMap<>();
        if (type.equals("kh")) {
            List<Custinit> custinits = new ArrayList<>();
            for (Customer customer : customerService.findList()) {
                Double ds = new Double(0);
                for (Orders orders : ordersService.findByOdrCustomerNo(customer.getCustNo())) {
                    for (OrdersLine line :
                            orders.getOrdersLines()) {
                        ds += line.getOddPrice();
                    }
                }
                Custinit custinit = new Custinit();
                custinit.setId(customer.getCustId());
                custinit.setName(customer.getCustName());
                custinit.setJe(ds);
                custinits.add(custinit);
            }
            model.put("sheet", custinits);
        } else if (type.equals("gc")) {
            List<Dictinit> dictinits = dictService.findLelList();
            model.put("sheet", dictinits);
        } else if (type.equals("fw")) {
            List<Dictinit> dictinits = dictService.findFwList();
            model.put("sheet", dictinits);
        }

        return new ModelAndView(new ViewPDF(), model);
    }

    @RequestMapping(value = "/storage/list")
    public String storage(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String prodName, @RequestParam(required = false) String stkWarehouse) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 5);  //查询5条数据
        Page<Storage> pager = storageService.finds(prodName, stkWarehouse, pageable);
        model.addAttribute("prodName", prodName);
        model.addAttribute("stkWarehouse", stkWarehouse);
        model.addAttribute("pager", pager);
        return "dict/storage/list";
    }

    @RequestMapping(value = "product/list")
    public String product(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String prodName, @RequestParam(required = false) String prodType
            , @RequestParam(required = false) String prodBatch) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 5);  //查询5条数据
        Page<Product> pager = productService.finds(prodName, prodType, prodBatch, pageable);
        model.addAttribute("prodName", prodName);
        model.addAttribute("prodType", prodType);
        model.addAttribute("prodBatch", prodBatch);
        model.addAttribute("pager", pager);
        return "dict/product/list";
    }
}
