package com.atstudy.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/edit")
public class EditController {




    @ResponseBody
    @RequestMapping("/config")
    public String config(){
        /* 上传图片配置项 */
        String conf = "    {\"imageActionName\": \"uploadimage\", /* 执行上传图片的action名称 */\n" +
                "    \"imageFieldName\": \"upfile\", /* 提交的图片表单名称 */\n" +
                "    \"imageMaxSize\": 2048000, /* 上传大小限制，单位B */\n" +
                "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], /* 上传图片格式显示 */\n" +
                "    \"imageCompressEnable\": true, /* 是否压缩图片,默认是true */\n" +
                "    \"imageCompressBorder\": 1600, /* 图片压缩最长边限制 */\n" +
                "    \"imageInsertAlign\": \"none\", /* 插入的图片浮动方式 */\n" +
                "    \"imageUrlPrefix\": \"\", /* 图片访问路径前缀 */\n" +
                "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"}";
        return conf;
    }


    @ResponseBody
    @RequestMapping("/upload")
    public Map<String,Object> uploadimage(MultipartFile upfile){
        // 响应报文
        Map<String,Object> params = new HashMap<>();
        // 为上传过来的文件生成一个唯一的文件名
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString() + upfile.getOriginalFilename().substring(
                upfile.getOriginalFilename().lastIndexOf(".")
        );
        try {
            // 文件要放到服务器的哪个地方
            File target = new File(
                    ResourceUtils.getURL("classpath: static/img/").getPath() + fileName
            );
            // 保存文件
            upfile.transferTo(target);

            // 按照一定格式封装响应报文
            params.put("state","SUCCESS");
            // 设置上传文件的url路径·
            params.put("url","/img/"+fileName);
            // 设置上传文件的大小
            params.put("size",upfile.getSize());
            // 设置上传文件的源文件名
            params.put("original",upfile.getOriginalFilename());
            // 设置上传文件的类型
            params.put("type",upfile.getContentType());
        }catch (Exception e){
            params.put("state","ERROR");
        }
        return params;
    }
}
