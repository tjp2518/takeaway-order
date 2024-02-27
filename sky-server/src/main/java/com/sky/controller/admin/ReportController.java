package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestController
@RequestMapping("/admin/report")
@Api(tags = "数据统计相关接口")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;
    /**
     * 营业额统计
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return
     */

    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("营业额数据统计：{},{}",begin,end);
        return Result.success(reportService.getTurnoverStatistics(begin, end));
    }
    /**
     * 用户数据统计
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return
     */

    @GetMapping("/userStatistics")
    @ApiOperation("用户数据统计")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("用户数据统计：{},{}",begin,end);
        return Result.success(reportService.getUserStatistics(begin, end));
    }

    /**
     * 订单数据统计
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return
     */

    @GetMapping("/ordersStatistics")
    @ApiOperation("订单数据统计")
    public Result<OrderReportVO> ordersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("订单数据统计：{},{}",begin,end);
        return Result.success(reportService.getOrderStatistics(begin, end));
    }

    /**
     * 销售额top10统计
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return
     */
    @GetMapping("/top10")
    @ApiOperation("销售top10统计")
    public Result<SalesTop10ReportVO> salesTop10Statistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("销售量top10统计：{},{}",begin,end);
        return Result.success(reportService.getSalesTop10Statistics(begin, end));
    }

    /**
     * 导出运营数据报表
     *
     * @param response
     */
    @GetMapping("/export")
    @ApiOperation("导出运营数据报表")
    public void export(HttpServletResponse response){

        reportService.exportBusinessData(response);

    }

}
