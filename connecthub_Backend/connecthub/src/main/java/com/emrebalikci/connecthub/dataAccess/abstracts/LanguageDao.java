package com.emrebalikci.connecthub.dataAccess.abstracts;

import com.emrebalikci.connecthub.entities.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageDao extends JpaRepository<Language, Short> {

}
