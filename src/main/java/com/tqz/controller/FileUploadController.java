package com.tqz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tqz.util.CommonResult;
import com.tqz.util.CommonResultEmnu;
import com.tqz.util.ResourceUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.AttributedString;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: tian
 * @Date: 2020/9/18 16:21
 * @Desc:
 */
@Slf4j
@RestController
public class FileUploadController {

    @Value("${fileBasePath}")
    private String fileBasePath;

    @ApiOperation(value = "批量上传文件", notes = "传0不做任何处理，不传-如果为图片，则按照宽750，质量0.7f压缩")
    @RequestMapping(value = "/file/fileUpload", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "watermark", value = "1 加水印", paramType = "query")
    })
    public CommonResult upload(HttpServletRequest request, Integer watermark, String address) {
        String host = getHost(request);
//        String host = "https://xc.szxx2018.com:443";
        if(host.indexOf(":443")>-1){
            host = host.substring(0, host.length()-4);
        }
        ArrayList<String> imglist = new ArrayList<String>() {{
            add(".png");
            add(".jpg");
            add(".jpeg");
            add(".gif");
            add(".bmp");
        }};

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        String names = "";
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                String filePath = System.currentTimeMillis() + suffix;
                File f = new File(fileBasePath);
                if (!f.exists()) {
                    f.mkdirs();
                }
                try {
                    byte[] bytes = file.getBytes();
                    //是图片，切需要加水印
                    if (imglist.indexOf(suffix) > -1 && (watermark !=null && watermark == 1)) {
                        bytes = watermark(bytes, suffix, address);
                    }
                    stream = new BufferedOutputStream(new FileOutputStream(new File(fileBasePath + File.separator + filePath)));
                    stream.write(bytes);
                    stream.close();
                    if (imglist.indexOf(suffix) > -1) {//如果是图片 那就压缩一波
                        Thumbnails.of(fileBasePath + File.separator + filePath).scale(1f).toFile(fileBasePath + File.separator + "small" + filePath); //等比例压缩
                        filePath = "small" + filePath;
                    }
                    if (names != "") {
                        names += ",";
                    }
                    names += host + "/" + filePath;
//                    redisService.set("file:" + filePath, fileName, 30l, TimeUnit.MINUTES);//以路径为key将名称储存值redis 30分钟过期
                } catch (Exception e) {
                    stream = null;
                    e.printStackTrace();
                    return new CommonResult(CommonResultEmnu.AUTHENTICATION_ERR, "系统异常:文件上传失败!");
                }
            }
        }
        return new CommonResult(names);
    }

    @ApiOperation(value = "批量上传文件", notes = "传0不做任何处理，不传-如果为图片，则按照宽750，质量0.7f压缩")
    @RequestMapping(value = "/file/fileUpload2", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "watermark", value = "1 加水印", paramType = "query")
    })
    public CommonResult upload2(HttpServletRequest request, Integer watermark, String address) {
        String host = getHost(request);
//        String host = "https://xc.szxx2018.com:443";
        if(host.indexOf(":443")>-1){
            host = host.substring(0, host.length()-4);
        }
        ArrayList<String> imglist = new ArrayList<String>() {{
            add("png");
            add("jpg");
            add("jpeg");
            add("gif");
            add("bmp");
        }};

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        String names = "";
        JSONArray ja = new JSONArray();
        JSONObject jb = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
                String filePath = ResourceUtil.createFileName(suffix);
//                File f = new File(fileBasePath);
                File f = new File(fileBasePath  + filePath);
                if (!f.exists()) {
                    f.mkdirs();
                }
                try {
                    byte[] bytes = file.getBytes();
                    //是图片，切需要加水印
                    stream = new BufferedOutputStream(new FileOutputStream(new File(fileBasePath  + filePath)));
                    stream.write(bytes);
                    stream.close();
                    if (imglist.indexOf(suffix) > -1) {//如果是图片 那就压缩一波
                        Thumbnails.of(fileBasePath + File.separator + filePath).scale(1f).toFile(fileBasePath + File.separator + "small" + filePath); //等比例压缩
                        filePath = "small" + filePath;
                    }
                    if (names != "") {
                        names += ",";
                    }
                    names += host + "/" + filePath;
                    jb = new JSONObject();
                    jb.put("name", fileName);
                    jb.put("url",names);
                    jb.put("path",filePath );
                    ja.add(jb);
                } catch (Exception e) {
                    stream = null;
                    e.printStackTrace();
                    return new CommonResult(CommonResultEmnu.AUTHENTICATION_ERR, "系统异常:文件上传失败!");
                }
            }
        }
        return new CommonResult(ja);
    }

    private String getHost(HttpServletRequest request) {
        String scheme = request.getHeader("X-Forwarded-Scheme");
        if (scheme == null) {
            scheme = request.getScheme();
        }
        String port = request.getHeader("X-Forwarded-port");
        if (port == null) {
            port = request.getServerPort() + "";
        }
        return scheme + "://" + request.getServerName() + ":" + port;//+request.getRequestURI();
    }

    private static byte[] watermark(byte[] bytes, String suffix, String address) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
            int x = 20, y = 30;
            //地址
            if (address != null) {
                image = overlapFont(image, x, y, address, 20, new Color(255, 255, 255), Font.PLAIN, false, new Color(153, 153, 153));
                y+=30;
            }
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = sdf.format(date);

            image = overlapFont(image, x, y, d, 20, new Color(255, 255, 255), Font.PLAIN, false, new Color(153, 153, 153));
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ImageIO.write(image, suffix.substring(1), b);
            return b.toByteArray();
        } catch (IOException e) {
            log.error("图片加水印异常");
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage overlapFont(BufferedImage big, int x, int y, String s, int size, Color color, int style, boolean strikethrough, Color background) {
        try {
            //得到Graphics2D 对象
            Graphics2D g2d = big.createGraphics();
            //设置颜色和画笔粗细
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(3));
            Font plainFont = new Font("微软雅黑", style, size);
            g2d.setBackground(Color.BLUE);
            AttributedString as = new AttributedString(s);
            as.addAttribute(TextAttribute.FONT, plainFont);
            if (strikethrough) { // 是否带删除线
                as.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            }
            if (background != null) {
                as.addAttribute(TextAttribute.BACKGROUND, background);
            }
            //绘制图案或文字
            g2d.drawString(as.getIterator(), x, y);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return big;
    }
}
