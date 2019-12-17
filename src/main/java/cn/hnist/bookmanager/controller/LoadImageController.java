package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.utils.APIResult;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 将文件存到了static的uploader下(target里面)
 */
@Controller
@RequestMapping("/images")
public class LoadImageController {

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public APIResult upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        String property = System.getProperty("user.dir");
        System.out.println(property);
        String dir = property + "" + "\\target\\classes\\static\\upload";
//        String dir = "G:\\学习视频\\ssm-框架\\后台管理\\upload";
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String imgName = "";
        if ("jpg".equals(type)) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".jpg";
        } else if ("png".equals(type)) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".png";
        } else if ("jpeg".equals(type)) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".jpeg";
        } else if ("gif".equals(type)) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".gif";
        } else {
            return null;
        }

        FileUtils.writeByteArrayToFile(new File(dir, imgName), file.getBytes());
//        System.out.println("path imagecontroller：" + dir);
        APIResult apiResult = new APIResult(true);
        apiResult.setData( "/bookmanager/upload" + "/" + imgName);
//        System.out.println("return url :"+dir);
        System.out.println("resultUrL:"+apiResult.getData());
        return apiResult;
    }

}
