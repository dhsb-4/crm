package com.t248.crm.repository;

import com.t248.crm.entity.Right;
import com.t248.crm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RightRepository extends JpaRepository<Right, String>, JpaSpecificationExecutor<Right> {
}
