package cn.com.onlinetool.utils;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class CleanCommonsUtil {
    private static final Pattern PATTERN = Pattern.compile("/\\*.+?\\*/", Pattern.DOTALL);
    private static final String CHARSET = StandardCharsets.UTF_8.name();

    public static void main(String[] args) throws IOException {
        String projectBaseDir = "/Users/edy/workspace/beytaiProjects/code/btgbox_new_clean_commons";
        Collection<File> files = FileUtils.listFiles(new File(projectBaseDir), new String[]{"java"}, true);
        for (File file : files) {
            String content = cleanCommons(file);
//            String content = FileUtils.readFileToString(file, charsets);
            FileUtils.writeStringToFile(file, cleanCommons(file.getPath(), content), CHARSET);
        }
    }

    public static String cleanCommons(String filePath, String content) {
        System.out.println("删除" + filePath + "文件的文档注释'/** */'");
        content.replaceAll("//.+\\r\\n", "");
        return PATTERN.matcher(content).replaceAll("");
    }

    public static String cleanCommons(File file) throws IOException {
        System.out.println("删除" + file.getPath() + "文件的单行注释'//'");
        StringBuilder sb = new StringBuilder();
        List<String> lines = FileUtils.readLines(file, CHARSET);
        for (String line : lines) {
            if (line.contains("; //") || line.contains(";//") || line.contains("//")) {
                line = line.substring(0, line.indexOf("//"));
            }
            sb.append(line).append("\r\n");
        }

        return sb.toString();
    }
}