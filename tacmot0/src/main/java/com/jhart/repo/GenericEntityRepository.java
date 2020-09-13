package com.jhart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhart.domain.GenericEntity;

public interface GenericEntityRepository extends JpaRepository<GenericEntity, Long>{

}

