package com.tqz.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: tian
 * @Date: 2020/9/18 17:01
 * @Desc:
 */
public class SftpUtil {

    public static Properties getFtpProp() {
        Properties props = new Properties();
        InputStream in = null;
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            in = resourceLoader.getResource("sftp.properties").getInputStream();
            props.load(in);
            return props;
        } catch (IOException e) {
            e.printStackTrace();
            return props;
        } finally {
            ResourceUtil.safeClose(in);
        }
    }

    public static Sftp getSftp() {
        Properties props = getFtpProp();
        String url = props.getProperty("ftp.url");
        String username = props.getProperty("ftp.username");
        String mixpd = props.getProperty("ftp.mixpd");
        return JschUtil.createSftp(url, 22, username, mixpd);
    }

    public static void upload(Sftp sftp, String sourceFilePath, String targetFilePath) {
        String targetPath = targetFilePath.substring(0, targetFilePath.lastIndexOf("/"));
        sftp.mkDirs(targetPath);
        sftp.put(sourceFilePath, targetFilePath);
        FileUtil.del(sourceFilePath);
    }

    public static void upload(Sftp sftp, String FileName, InputStream inputStream, String targetPath) {
        if (FileName.lastIndexOf(".") <= -1) {
            return;
        }
        String ext = FileName.substring(FileName.lastIndexOf(".") + 1);
        String sourceFilePath = System.getProperty("user.dir") + ResourceUtil.createFileName(ext);
        File tmpFile = FileUtil.touch(sourceFilePath);
        FileUtil.writeFromStream(inputStream, tmpFile.getPath());
        System.out.println("targetPath" + targetPath);
        upload(sftp, sourceFilePath, targetPath + FileName);
    }

    public static void upload(Sftp sftp, String FileName, BufferedImage bufferedImage, String targetPath)
            throws IOException {
        if (FileName.lastIndexOf(".") <= -1) {
            return;
        }
        String ext = FileName.substring(FileName.lastIndexOf(".") + 1);
        String sourceFilePath = System.getProperty("user.dir") + ResourceUtil.createFileName(ext);
        File tmpFile = FileUtil.touch(sourceFilePath);
        ImageIO.write(bufferedImage, "png", tmpFile);
        System.out.println("targetPath" + targetPath);
        upload(sftp, sourceFilePath, targetPath + FileName);
    }

    public static void download(Sftp sftp, String sourceFilePath, String targetFilePath) {
        sftp.download(sourceFilePath, FileUtil.file(targetFilePath));
    }

    public static void close(Sftp sftp) {
        if (sftp != null) {
            try {
                sftp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
