package cn.com.onlinetool.codec.serializable.test.performance;

import cn.com.onlinetool.codec.serializable.pojo.SerializableTestPojo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Java原生序列化性能测试
 *
 * @author choice
 * @date create in 2020/4/4 00:48
 */
@Slf4j
public class JavaNativePerformanceTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableTestPojo pojo = new SerializableTestPojo("choice", 23);
        final int count = 1000000;

        long sTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            byte[] bytes = javaNativeEncoder(pojo);
            SerializableTestPojo pojo1 = javaNativeDecoder(bytes);
        }
        long eTime = System.currentTimeMillis();
        log.info("java.io.Serializable编解码100w次耗时：" + (eTime - sTime) + "ms");

        sTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            byte[] bytes = byteBufferEncoder(pojo);
            SerializableTestPojo pojo1 = byteBufferDecoder(bytes);
        }
        eTime = System.currentTimeMillis();
        log.info("java.nio.ByteBuffer编解码100w次耗时：" + (eTime - sTime) + "ms");
    }

    private static byte[] javaNativeEncoder(SerializableTestPojo pojo) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(pojo);
        oos.flush();
        oos.close();
        return bos.toByteArray();
    }

    private static SerializableTestPojo javaNativeDecoder(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (SerializableTestPojo) ois.readObject();
    }

    private static byte[] byteBufferEncoder(SerializableTestPojo pojo) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(pojo.getName().length());
        buffer.put(pojo.getName().getBytes());
        buffer.putInt(pojo.getAge());
        buffer.flip();
        byte[] res = new byte[buffer.remaining()];
        buffer.get(res);
        return res;
    }

    private static SerializableTestPojo byteBufferDecoder(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        SerializableTestPojo pojo = new SerializableTestPojo();
        byte[] usernameBytes = new byte[buffer.getInt()];
        buffer.get(usernameBytes);
        pojo.setName(new String(usernameBytes, StandardCharsets.UTF_8));
        pojo.setAge(buffer.getInt());
        return pojo;
    }
}
