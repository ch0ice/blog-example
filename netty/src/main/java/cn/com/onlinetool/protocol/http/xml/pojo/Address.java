package cn.com.onlinetool.protocol.http.xml.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@XStreamAlias("Address")
@Data
public class Address {
    private String street1;

    private String street2;

    private String city;

    private String state;

    private String postCode;

    private String country;


}