package cn.com.onlinetool.protocol.http.xml.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@XStreamAlias("Order")
@Data
public class Order {

    private long orderNumber;

    private Customer customer;

    private Address billTo;

    private Shipping shipping;

    private Address shipTo;

    private Float total;


}