package cn.com.onlinetool.codec.marshalling.factory;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.serial.SerialMarshallerFactory;

/**
 * @author choice
 * @date create in 2020/4/8 15:37
 */
public final class MarshallingCodecFactory {


    public static MarshallingDecoder buildMarshallingDecoder(){
        final MarshallerFactory marshallerFactory = new SerialMarshallerFactory();
        //new SerialMarshallerFactory(); == Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        //解码可有可无，因为用不到version
        configuration.setVersion(5);
        UnmarshallerProvider unmarshallerProvider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        return new MarshallingDecoder(unmarshallerProvider);
    }


    public static MarshallingEncoder buildMarshallingEncoder(){
        final MarshallerFactory marshallerFactory = new SerialMarshallerFactory();
        //new SerialMarshallerFactory(); == Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        //不设置或必须设置为5
        configuration.setVersion(5);
        MarshallerProvider marshallerProvider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        return new MarshallingEncoder(marshallerProvider);
    }
}
