package com.gbq.boot.web.utils.image;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.ResourceBundle;

/**
 * https://blog.csdn.net/saytime/article/details/51416411
 * https://blog.csdn.net/niuch1029291561/article/details/17377903
 * @ClassName: ImageUploadUtil
 * @Description: 图片上传工具类
 */
public class ImageUploadUtil {
    private static final Logger logger = LoggerFactory.getLogger(ServletContextListener.class);

    // 文件上传
    public static String copyToApachePath(MultipartFile attach,String apacheFilePath) {
        InputStream input = null;
        FileOutputStream output = null;
        String extension = "";


        // 文件转MD5
        String md5Code = null;
        try {
            md5Code = DigestUtils.md5Hex(attach.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取文件名称
        String originalFileName = attach.getOriginalFilename();
        try {
            // 判断文件名
            if (originalFileName == null || "".equals(originalFileName)) {
                return null;
            }
            // 获取文件后缀
            if (originalFileName.contains(".")) {
                extension = originalFileName.substring(originalFileName.lastIndexOf('.'), originalFileName.length());
            } else {
                return null;
            }
            // 构建指定文件
            File source = new File(apacheFilePath + md5Code + extension);
            // 查询文件是否存在 存在直接返回文件名 不存在创建文件 再返回文件名
            if (source.exists()) {
                return md5Code + extension;
            }
            output = new FileOutputStream(source);
            input = attach.getInputStream();
            byte[] buf = new byte[1024 * 1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (IOException e) {
            logger.error("error", e);
        } finally {
            // 关闭流
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    logger.error("error", e);
                }
            }

            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("error",e);
                }
            }
        }
        // 打印文件名称
        logger.error("out " + md5Code + extension);
        return md5Code + extension;
    }

    // 获取properties文件内容
    public static String getPropertieString(String file, String str) {
        ResourceBundle rb = ResourceBundle.getBundle(file);
        try {
            return new String(rb.getString(str).getBytes("ISO-8859-1"),"UTF-8");//中文乱码的问题，jdbc.properties需要另存为UTF-8格式,如果没有中文 直接
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//中文乱码的问题，jdbc.properties需要另存为UTF-8格式,如果没有中文 直接
        return null;
        //return rb.getString(str);
    }
}