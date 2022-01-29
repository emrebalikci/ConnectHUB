package com.emrebalikci.connecthub.dataAccess.abstracts;

import com.emrebalikci.connecthub.entities.concretes.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDao extends JpaRepository<School, Integer> {

}
