package com.emrebalikci.connecthub.entities.abstracts;

import com.emrebalikci.connecthub.core.entities.BaseEntity;
import com.emrebalikci.connecthub.entities.concretes.Candidate;

public interface CvProp extends BaseEntity<Integer> {

    Candidate getCandidate();

    void setCandidate(Candidate candidate);

}
