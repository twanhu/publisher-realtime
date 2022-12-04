package com.gmall.publisherrealtime.controller;

import com.gmall.publisherrealtime.bean.NameValue;
import com.gmall.publisherrealtime.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 控制层
 */
@RestController
public class PublisherContoller {

    @Autowired
    PublisherService publisherService;

    /**
     * 日活分析
     *
     * http://bigdata.gmall.com/dauRealtime?td=2022-01-01
     */
    @GetMapping("dauRealtime")
    public Map<String, Object> dauRealtime(@RequestParam("td") String td) {

        Map<String, Object> results = publisherService.doDauRealtime(td);

        return results;
    }

    /**
     * 交易分析 - 按照类别（年龄、性别）统计
     * <p>
     * http://bigdata.gmall.com/statsByItem?itemName=小米手机&date=2021-02-02&t=gender
     * http://bigdata.gmall.com/statsByItem?itemName=小米手机&date=2021-02-02&t=age
     */
    @GetMapping("statsByItem")
    public List<NameValue> statsByItem(
            @RequestParam("itemName") String itemName,
            @RequestParam("date") String date,
            @RequestParam("t") String t) {
        List<NameValue> results = publisherService.doStatsByItem(itemName, date, t);
        return results;
    }

    /**
     * 交易分析 - 明细
     * http://bigdata.gmall.com/detailByItem?date=2021-02-02&itemName=小米手机&pageNo=1&pageSize=20
     */
    @GetMapping("detailByItem")
    public Map<String, Object> detailByItem(@RequestParam("date") String date,
                                            @RequestParam("itemName") String itemName,
                                            //显示第一页，如果不传入参数则使用默认的
                                            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                            //每页显示20条数据，如果不传入参数则使用默认的
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        Map<String, Object> results = publisherService.doDetailByItem(date, itemName, pageNo, pageSize);
        return results;
    }

}
