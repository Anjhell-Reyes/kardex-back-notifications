package com.kardex.infrastructure.out.repository;

import com.kardex.infrastructure.out.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeRepository extends JpaRepository<TypeEntity, Long> {
}
