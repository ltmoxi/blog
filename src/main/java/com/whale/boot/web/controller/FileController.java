package com.whale.boot.web.controller;


import cn.hutool.core.lang.UUID;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.whale.boot.web.utils.image.ImageUploadUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final String path = "http://localhost/upfile/";
    @Value("${windowsPath}")
    public String windowsPath;
    @Resource
    private FastFileStorageClient fastFileStorageClient;

//    /**
//     * 文件上传
//     * @return result
//     */
//    @ResponseBody
//    @PostMapping("/upload")
//    public HashMap<String, Object> uploadImageByCover(MultipartFile file){
//        HashMap<String,Object> result = new HashMap<>();
//        try {
//            // 获取文件名称
//            String originalFileName = file.getOriginalFilename();
//            //fastDfs返回的路径名称
//            String fastDfsGroup = redisTemplate.opsForValue().get(originalFileName);
//
//            if(StringUtils.isNotBlank(fastDfsGroup)){
//                result.put("msg",fastDfsGroup);
//            }else {
//                //上传
//                StorePath path = fastFileStorageClient.
//                        uploadFile(file.getInputStream(),file.getSize(),
//                                FilenameUtils.getExtension(file.getOriginalFilename()),null);
//                //获取路径加名称
//                String picName = path.getFullPath();
//                //把名称，路径存入redis
//                redisTemplate.opsForValue().set(originalFileName,picName);
//
//                result.put("msg",picName);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    @ResponseBody
    @PostMapping("/upload")
    public HashMap<String, Object> uploadImageByCover(MultipartFile file) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            // 获取文件名称
            String s = ImageUploadUtil.copyToApachePath(file, windowsPath);
            result.put("msg", path + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 文件删除
     *
     * @param path
     * @return
     */
    @DeleteMapping("/delete")
    public HashMap<String, Object> delete(@RequestParam String path) {
        HashMap<String, Object> result = new HashMap<>();
        // 第一种删除：参数：完整地址
        fastFileStorageClient.deleteFile(path);
        result.put("msg", "恭喜恭喜，删除成功！");
        // 第二种删除：参数：组名加文件路径
        // fastFileStorageClient.deleteFile(group,path);
        return result;
    }

    /**
     * 文件下载
     *
     * @param url 路径
     * @return
     */
    @GetMapping("/download")
    public void downLoad(@RequestParam String url, HttpServletResponse response) throws IOException {
        String group = url.substring(0, url.indexOf("/"));
        String path = url.substring(url.indexOf("/") + 1);
        //文件后缀
        String substring = url.substring(url.lastIndexOf(".") + 1);
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, new DownloadByteArray());

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(UUID.randomUUID().toString() + "." + substring, "UTF-8"));

        // 写出
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.write(bytes, outputStream);
    }
}
