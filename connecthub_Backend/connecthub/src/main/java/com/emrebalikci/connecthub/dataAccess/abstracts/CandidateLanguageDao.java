package com.emrebalikci.connecthub.dataAccess.abstracts;

import com.emrebalikci.connecthub.entities.concretes.CandidateLanguage;
import com.emrebalikci.connecthub.entities.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CandidateLanguageDao extends JpaRepository<CandidateLanguage, Integer> {

    boolean existsByLanguage_IdAndCandidate_Id(Short languageId, Integer candidateId);

    @Modifying
    @Query("update CandidateLanguage cL set cL.language = :language where cL.id = :id")
    void updateLanguage(@Param(value = "language") Language language, @Param(value = "id") Integer id);

    @Modifying
    @Query("update CandidateLanguage cL set cL.languageLevel = :languageLevel where cL.id = :id")
    void updateLanguageLevel(@Param(value = "languageLevel") String languageLevel, @Param(value = "id") Integer id);

}
