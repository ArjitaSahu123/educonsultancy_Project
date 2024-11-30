package com.backend.educonsultancy_backend.service;

import com.backend.educonsultancy_backend.entities.UserOrder;
import com.backend.educonsultancy_backend.repositories.UserOrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;

    @Value("${razorpay.key.id}")
    private String razorPayKey;

    @Value("${razorpay.secret.key}")
    private String razorPaySecret;

    private RazorpayClient client;

    public UserOrder createOrder(UserOrder useOrder) throws RazorpayException {
        JSONObject orderReq = new JSONObject();
        orderReq.put("amount",useOrder.getAmount()*100);
        orderReq.put("currency","INR");
        orderReq.put("receipt",useOrder.getEmail());

        this.client = new RazorpayClient(razorPayKey,razorPaySecret);

        Order razorPayOrder = client.orders.create(orderReq);

        System.out.println(razorPayOrder);

        useOrder.setRazorpayOrderId(razorPayOrder.get("id"));
        useOrder.setOrderStatus(razorPayOrder.get("status"));

        userOrderRepository.save(useOrder);

        return useOrder;
    }
}
