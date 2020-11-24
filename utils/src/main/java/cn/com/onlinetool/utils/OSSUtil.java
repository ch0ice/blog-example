package cn.com.onlinetool.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author choice
 * @description: 对象存储工具类
 * @date 2018/10/24 下午5:03
 *
 */
public class OSSUtil {
    private static Logger logger = LoggerFactory.getLogger(OSSUtil.class);

    /** OSS客户端 */
    private static OSSClient ossClient;

    /** endpoint是访问OSS的域名 */
    private static String endpoint;

    /** OSS的访问id */
    private static String accessKeyId;

    /** OSS的访问密码 */
    private static String accessKeySecret;

    /** OSS存储空间的名字 */
    private static String bucketName;

    static {
        logger.info("初始化OSSClient");
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = OSSUtil.class.getClassLoader().getResourceAsStream("config/oss.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            logger.info("读取配置文件失败");
            e.printStackTrace();
        }
        endpoint = properties.getProperty("endpoint");
        accessKeyId = properties.getProperty("accessKeyId");
        accessKeySecret = properties.getProperty("accessKeySecret");
        bucketName = properties.getProperty("bucketName");
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        BucketInfo info = ossClient.getBucketInfo(bucketName);
        logger.info("Bucket " + bucketName + "的信息如下：");
        logger.info("\t数据中心：" + info.getBucket().getLocation());
        logger.info("\t创建时间：" + info.getBucket().getCreationDate());
        logger.info("\t用户标志：" + info.getBucket().getOwner());

    }

    /**
     * 获取OSS客户端
     * @return
     */
    public static OSSClient getOSSClient(){
        return ossClient;
    }

    /**
     * 上传文件
     * @param fileKey
     * @param file
     * @return
     */
    public static PutObjectResult putObject(String fileKey, File file){
        PutObjectResult putResult = null;
        try {
            putResult = getOSSClient().putObject(bucketName,fileKey,file);
        } catch (OSSException | ClientException oe) {
            logger.error("上传文件失败");
            oe.printStackTrace();
        }
        return putResult;
    }

    /**
     * 上传文件
     * @param fileKey
     * @param file
     * @return
     */
    public static PutObjectResult putObject(String fileKey, InputStream file){
        PutObjectResult putResult = null;
        try {
            putResult = getOSSClient().putObject(bucketName,fileKey,file);
        } catch (OSSException | ClientException oe) {
            logger.error("上传文件失败");
            oe.printStackTrace();
        }
        return putResult;
    }

    /**
     * 下载文件
     * @param fileKey
     * @return
     */
    public static OSSObject getObject(String fileKey){
        OSSObject ossObject = null;
        try {
            ossObject = getOSSClient().getObject(bucketName,fileKey);
        } catch (OSSException | ClientException oe) {
            logger.error("下载文件失败");
            oe.printStackTrace();
        }
        return ossObject;
    }

    /**
     * 列举bucket下的文件。最多列举100个文件
     * @return
     */
    public static ObjectListing listObjects(){
        ObjectListing objectListing = null;
        try {
            objectListing = getOSSClient().listObjects(bucketName);
        } catch (OSSException | ClientException oe) {
            logger.error("列举bucket下的文件失败");
            oe.printStackTrace();
        }
        return objectListing;
    }

    /**
     * 列举bucket下指定前缀的文件。最多列举100个文件。
     * @return
     */
    public static ObjectListing listObjects(String prefix){
        ObjectListing objectListing = null;
        try {
            objectListing = getOSSClient().listObjects(bucketName,prefix);
        } catch (OSSException | ClientException oe) {
            logger.error("列举bucket下的文件失败");
            oe.printStackTrace();
        }
        return objectListing;
    }

    /**
     * 删除文件
     * @param fileKey 文件在bucket中的key
     */
    public static void deleteObject(String fileKey){
        try {
            getOSSClient().deleteObject(bucketName,fileKey);
        } catch (OSSException | ClientException oe) {
            logger.error("删除文件失败");
            oe.printStackTrace();
        }

    }

    /**
     * 获取文件url
     * @param fileKey 文件key
     * @return
     */
    public static URL generatePresignedUrl(String fileKey,long expiration){
        URL url = null;
        try {
            url = getOSSClient().generatePresignedUrl(bucketName,fileKey,new Date(System.currentTimeMillis() + expiration * 1000));
        } catch (OSSException | ClientException oe) {
            logger.error("获取文件url失败");
            oe.printStackTrace();
        }
        return url;
    }

    /**
     * 获取文件url 默认10分钟内有效
     * @param fileKey 文件key
     * @return
     */
    public static URL generatePresignedUrl(String fileKey){
        return generatePresignedUrl(fileKey,600);
    }

    /**
     * 关闭OSS客户端连接
     */
    public static void shutdown(){
        getOSSClient().shutdown();
    }



    public static void main(String[] args) {
        String firstKey = "my-first-key";

        try {

            PutObjectResult putResult = putObject(firstKey, new File("/Users/choice/Downloads/blog-log.png"));

            OSSObject ossObject = getObject(firstKey);
            InputStream inputStream = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                objectContent.append(line);
            }
            inputStream.close();
            System.out.println("Object：" + firstKey + "的内容是：" + objectContent);

            ObjectListing objectListing = listObjects();
            List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
            System.out.println("您有以下Object：");
            for (OSSObjectSummary object : objectSummary) {
                System.out.println("\t" + object.getKey());
            }

//            deleteObject(firstKey);

        } catch (OSSException | ClientException oe) {
            oe.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

}
