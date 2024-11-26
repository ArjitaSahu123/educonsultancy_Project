package com.backend.educonsultancy_backend.repositories;

import com.backend.educonsultancy_backend.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
