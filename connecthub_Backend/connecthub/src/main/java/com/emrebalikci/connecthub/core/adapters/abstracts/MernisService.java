package com.emrebalikci.connecthub.core.adapters.abstracts;

import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateAddDto;

public interface MernisService {

    boolean realPerson(CandidateAddDto dto);

}
