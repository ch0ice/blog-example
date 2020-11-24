
package cn.com.onlinetool.protocol.http.xml.pojo;


public class OrderFactory {

    public static Order create(long orderId) {
	Order order = new Order();
	order.setOrderNumber(orderId);
	order.setTotal(9999.999f);
	Address address = new Address();
	address.setCountry("中国");
	address.setState("北京");
	address.setCity("北京市");
	address.setPostCode("100010");
	address.setStreet1("龙眠大道");
	order.setBillTo(address);
	Customer customer = new Customer();
	customer.setCustomerNumber(orderId);
	customer.setLastName("Liu");
	customer.setFirstName("Choice");
	order.setCustomer(customer);
	order.setShipping(Shipping.INTERNATIONAL_MAIL);
	order.setShipTo(address);
	return order;
    }
}
