package cn.com.onlinetool.protocol.http.xml.util;

import cn.com.onlinetool.protocol.http.xml.pojo.*;
import com.thoughtworks.xstream.XStream;

/**
 * @author choice
 * @date create in 2020/5/26 17:16
 */
public class XStreamUtil {
    private static XStream xStream = new XStream();

    static {

        xStream.processAnnotations(new Class[]{Address.class, Customer.class, Order.class, Shipping.class});
        xStream.allowTypesByWildcard(new String[] {
                "cn.com.onlinetool.protocol.http.xml.pojo.**"
        });
    }

    public static Object xml2obj(String xml){
        return xStream.fromXML(xml);
    }

    public static String obj2xml(Object obj){
        return xStream.toXML(obj);
    }

    /**
     * test
     * @author choice
     * @date 2020/5/26 17:23
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        System.out.println(xStream.toXML(OrderFactory.create(1)));
    }
}

