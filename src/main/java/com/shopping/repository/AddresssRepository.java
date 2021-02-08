package com.shopping.repository;

import com.shopping.models.Addresss;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddresssRepository extends JpaRepository<Addresss, Long> {

    List<Addresss> findAllByCustomerId(Long id);
}
