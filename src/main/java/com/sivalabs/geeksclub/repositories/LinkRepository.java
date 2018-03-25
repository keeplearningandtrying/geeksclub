package com.sivalabs.geeksclub.repositories;

import com.sivalabs.geeksclub.entities.Link;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByTitleContainingIgnoreCase(String q, Sort sort);
    List<Link> findByCreatedById(Long userId);
}
