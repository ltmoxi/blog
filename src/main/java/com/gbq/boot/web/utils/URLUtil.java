package com.gbq.boot.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class URLUtil {
    public static String SendGET(String url) {
        String result = "";// 访问返回结果
        BufferedReader read = null;// 读取访问结果
        try {
            // 创建url
            URL realurl = new URL(url);
            // 打开连接
            URLConnection connection = realurl.openConnection();
            
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;// 循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {// 关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    
    public static void main(String[] args) {
        String validatePhone="1562397271";//接口入参
        String res;
        try {
           JSONObject jsonObject= new JSONObject();//new一个json对象
           jsonObject.put("phoneNum",validatePhone);//将入参添加进去，多个参数就put多个
            System.out.println(jsonObject.toString());//将json对象转换成字符串
            System.out.println("URL: "+FrmsUtils.URL+ FrmsUtils.QUERY_WHITELIST_URL);//打印组合后的路径；
            res = URLUtil.SendGET(FrmsUtils.URL+ FrmsUtils.QUERY_WHITELIST_URL);//调用封装的请求方法，实现接口请求
            System.out.println(res);//打印接口返回结果
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public class FrmsUtils {
        public final static String URL = "http://www.baidu.com/";
         public final static String QUERY_WHITELIST_URL = "whiteList";//白名单接口
     }
}
