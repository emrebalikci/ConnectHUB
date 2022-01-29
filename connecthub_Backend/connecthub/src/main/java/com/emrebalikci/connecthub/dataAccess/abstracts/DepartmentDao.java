package com.emrebalikci.connecthub.dataAccess.abstracts;

import com.emrebalikci.connecthub.entities.concretes.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Short> {

}
