package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.Skill;

import java.util.List;

public interface SkillService {

    DataResult<List<Skill>> getAll();

    Result add(String skillName);

}
