package com.example.springboot.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @Author wusha
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class VelocityUtils {
    /**
     * velocity引擎
     */
    private static VelocityEngine engine;
    public static  final String VM_LOADPATH_KEY = "file.resource.loader.class";
    public static  final String VM_LOADPATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    public static void exportTemplate(OutputStream outputStream, Map<String, Object> paramsMap,
                                      String templateFile) throws IOException {
        // 初始化模板引擎
        VelocityEngine velocity =  getVelocityEngine();
        // 获取模板文件
        Template template = velocity.getTemplate(templateFile, "UTF-8");
        // 设置变量
        VelocityContext context = new VelocityContext();

        for(Map.Entry<String, Object> entry : paramsMap.entrySet()){
            context.put(entry.getKey(), entry.getValue());
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        template.merge(context, writer);
        writer.close();
    }


    /**
     * 设置模版引擎，主要指向获取模版路径
     */
    private static VelocityEngine getVelocityEngine() {
        if (engine == null) {
            Properties p = new Properties();
            p.setProperty(VM_LOADPATH_KEY, VM_LOADPATH_VALUE);
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
            p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
           // p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
           // p.setProperty(Velocity.EVENTHANDLER_NULLSET, "");
            p.setProperty("file.resource.loader.unicode", "true");
            engine = new VelocityEngine(p);
        }
        return engine;
    }
}
