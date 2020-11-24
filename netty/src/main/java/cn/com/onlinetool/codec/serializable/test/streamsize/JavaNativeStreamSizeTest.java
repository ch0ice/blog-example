package cn.com.onlinetool.codec.serializable.test.streamsize;

import cn.com.onlinetool.codec.protobuf.proto.PbMessage;
import cn.com.onlinetool.codec.serializable.pojo.SerializableTestPojo;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * Java原生序列化框架 与 java.nio.ByteBuffer 序列化码流大小对比测试
 *
 * @author choice
 * @date create in 2020/4/4 00:07
 */
@Slf4j
public class JavaNativeStreamSizeTest {


    public static void main(String[] args) throws IOException {
        PbMessage.TimeServerMessage.Builder pbMessage = PbMessage.TimeServerMessage.newBuilder();
            pbMessage.setId(23).setUsername("choice");
        SerializableTestPojo pojo = new SerializableTestPojo("choice", 23);
        log.info("java.io.Serializable字节数：" + javaNativeEncoder(pojo).length);
        log.info("java.nio.ByteBuffer字节数：" + byteBufferEncoder(pojo).length);
        log.info("protobuf字节数:" + pbMessage.build().toByteArray().length);
    }

    private static byte[] javaNativeEncoder(SerializableTestPojo pojo) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(pojo);
        oos.flush();
        oos.close();
        return bos.toByteArray();
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

}
