package com.backend.educonsultancy_backend.service;

import com.backend.educonsultancy_backend.entities.UserOrder;
import com.backend.educonsultancy_backend.repositories.UserOrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;

    @Value("${razorpay.key.id}")
    private String razorPayKey;

    @Value("${razorpay.secret.key}")
    private String razorPaySecret;

    private RazorpayClient client;


    public List<UserOrder> getAllOrders() {
        return userOrderRepository.findAll();
    }

    public UserOrder getOrderById(Integer id) {
        return userOrderRepository.findById(id).orElse(null);
    }

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

    @Transactional
    public UserOrder updateOrder(Map<String, String> responsePayLoad) {
        String razorPayOrderId = responsePayLoad.get("razorpay_order_id");

        // Retrieve the order using the razorpay_order_id
        UserOrder order = userOrderRepository.findByRazorpayOrderId(razorPayOrderId);

        if (order == null) {
            System.out.println("Order not found with Razorpay order ID: " + razorPayOrderId);
            return null; // Or throw an exception
        }

        // Update the order status to "COMPLETED"
        order.setOrderStatus("COMPLETED");

        // Save the updated order
        UserOrder updatedOrder = userOrderRepository.save(order);

        System.out.println("Order updated: " + updatedOrder);

        // Optionally send email (if required)

        return updatedOrder;
    }

//    public UserOrder updateOrder(Map<String,String> responsePayLoad){
//        String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
//
//        UserOrder order = userOrderRepository.findByRazorpayOrderId(razorPayOrderId);
//
//        order.setOrderStatus("COMPLETED");
//
//        UserOrder updatedOrder = userOrderRepository.save(order);
//
//        // SEND EMAIL
//
//        return updatedOrder;
//    }
}
