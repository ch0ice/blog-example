package cn.com.onlinetool.protocol.http.xml.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

@XStreamAlias("Customer")
@Data
public class Customer {
    private long customerNumber;

    private String lastName;

    private String firstName;

    private List<String> middleNames;


}