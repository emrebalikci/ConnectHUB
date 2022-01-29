package com.emrebalikci.connecthub.business.concretes;

import com.emrebalikci.connecthub.business.abstracts.SkillService;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.core.utilities.results.SuccessDataResult;
import com.emrebalikci.connecthub.dataAccess.abstracts.SkillDao;
import com.emrebalikci.connecthub.entities.concretes.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillManager implements SkillService {

    private final SkillDao skillDao;

    @Override
    public DataResult<List<Skill>> getAll() {
        return new SuccessDataResult<>(skillDao.findAll());
    }

    @Override
    public Result add(String skillName) {
        Skill savedSkill = skillDao.save(new Skill(skillName));
        return new SuccessDataResult<>(Msg.SAVED.get(), savedSkill);
    }
}
