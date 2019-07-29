package com.example.springboot.aopcontroller;

import com.example.springboot.utils.VelocityUtils;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author wusha
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@RestController
@RequestMapping(value = "/aop")
public class AopController {


    @ResponseBody
    @GetMapping(value = "/hello")
    public  String hello(@RequestParam String name){
        return "hello";
    }

    /**
     * 导出excel报表
     * @param response 页面请求
     */
    @PostMapping(value = "attendanceReport/getlist")
    public void attendanceReport(HttpServletResponse response) {
        try {
            //获取数据
            Map<String, Object> paramMap = new HashMap<>();
            Map<String,String> param = new HashMap<>();
            param.put("name","wu");
            param.put("age","11");
            param.put("height","170");
            param.put("weight","160");
            List<Object> list = new ArrayList<>();
            list.add(param);
            String ss=" <NumberFormat ss:Format=\"_ &quot;￥&quot;* #,##0.00_ ;_ &quot;￥&quot;* \\-#,##0.00_ ;_ &quot;￥&quot;* &quot;-&quot;??_ ;_ @_ \"/>";
            paramMap.put("ss", ss);
            paramMap.put("summaryList",list);
            paramMap.put("time","2019-01-01");
            response.setContentType("application/vnd.ms-excel");
            VelocityUtils.exportTemplate(response.getOutputStream(), paramMap, "excelmodel/summarycount.xml");
        } catch (Exception e) {
            try {
                response.getWriter().write("export excel error");
            } catch (IOException e1) {
                Logger.logMsg(1,e1.getLocalizedMessage());
            }
        }
    }
}
