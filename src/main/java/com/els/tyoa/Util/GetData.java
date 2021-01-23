package com.els.tyoa.Util;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetData {
    public String getData(HttpServletRequest req){
        String result = null;
        try {
            //包装request的输入流
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
            //缓冲字符
            StringBuffer sb=new StringBuffer("");
            String line;
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            br.close();//关闭缓冲流
            result=sb.toString();//转换成字符
            System.out.println("result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
