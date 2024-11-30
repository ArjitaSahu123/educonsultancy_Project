package com.backend.educonsultancy_backend.repositories;

import com.backend.educonsultancy_backend.entities.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder,Integer> {

}
