package com.ajustadoati.backend.adapter.rest.repository;

import com.ajustadoati.backend.domain.Location;
import com.ajustadoati.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByUser(User user);
}
