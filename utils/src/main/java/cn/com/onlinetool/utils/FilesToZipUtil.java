package cn.com.onlinetool.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author choice
 * @date 2020-12-22 17:08
 */
@Slf4j
public class FilesToZipUtil {
    private static final int FILE_BUFFER_SIZE = 1024;
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String URL_PATTERN = "^(((ht|f)tps?)://)?[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$";

    public static void filesToZip(List<Map<String, String>> files, OutputStream os) throws IOException {
        filesToZip(files, os, FILE_BUFFER_SIZE);
    }

    public static void filesToZip(List<Map<String, String>> files, OutputStream os, int bufferSize) throws IOException {
        filesToZip(files, os, bufferSize, CHARSET);
    }

    public static void filesToZip(List<Map<String, String>> files, OutputStream os, int bufferSize, Charset charset) throws IOException {
        filesToZip(files, os, 0, bufferSize, charset);
    }

    /**
     * @param files      文件列表
     * @param os         输出流
     * @param level      压缩级别 0-9
     * @param bufferSize 缓冲大小
     * @param charset    字符集
     * @throws IOException
     * @author choice
     */
    private static void filesToZip(List<Map<String, String>> files, OutputStream os, int level, int bufferSize, Charset charset) throws IOException {
        try (ZipOutputStream zipOS = new ZipOutputStream(os, charset)) {
            zipOS.setLevel(level);
            for (Map<String, String> file : files) {
                String fileName = file.get("fileName");
                String fileUrl = file.get("fileUrl");
                if (fileName == null || "".equals(fileName) || fileUrl == null || "".equals(fileUrl)) {
                    log.error("文件名或文件路径为空：fileName：{}，fileUrl：{}", fileName, fileUrl);
                    continue;
                }
                InputStream fis;
                try {
                    if (Pattern.matches(URL_PATTERN, fileUrl)) {
                        fis = new URL(fileUrl).openStream();
                    } else {
                        fis = new FileInputStream(new File(fileUrl));
                    }
                } catch (FileNotFoundException fe) {
                    log.error("文件加载失败：fileName：{}，fileUrl：{}", fileName, fileUrl, fe);
                    continue;
                }
                BufferedInputStream bis = new BufferedInputStream(fis);
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOS.putNextEntry(zipEntry);
                int count;
                byte[] data = new byte[bufferSize];
                while ((count = bis.read(data, 0, bufferSize)) != -1) {
                    zipOS.write(data, 0, count);
                }
                bis.close();
            }
            zipOS.flush();
        }
    }


    /**
     * test
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            long sTime = System.currentTimeMillis();
            final String baseUrl = "/Users/admin/Downloads/test/";
            File file = new File(baseUrl + "test" + i + ".zip");
            FileOutputStream fos = new FileOutputStream(file);
            List<Map<String, String>> files = new ArrayList<>();
            Map<String, String> file1 = new HashMap<>();
            file1.put("fileName", "test.png");
            file1.put("fileUrl", baseUrl + "test.png");
            Map<String, String> file2 = new HashMap<>();
            file2.put("fileName", "test.xlsx");
            file2.put("fileUrl", baseUrl + "test.xlsx");
            Map<String, String> file3 = new HashMap<>();
            file3.put("fileName", "test.pkg");
            file3.put("fileUrl", baseUrl + "test.pkg");
            Map<String, String> file4 = new HashMap<>();
            file4.put("fileName", "bpic22053.rar");
            file4.put("fileUrl", "https://lib.tjutcm.edu.cn/20150624tjutcm_book_sheet.rar");
            files.add(file1);
            files.add(file2);
            files.add(file3);
            files.add(file4);
            filesToZip(files, fos, i, FILE_BUFFER_SIZE, CHARSET);
            log.info("压缩级别为：{}，耗时为：{}毫秒，压缩后的文件大小：{} Byte", i, System.currentTimeMillis() - sTime, file.length());

        }
    }

}
