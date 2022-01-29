package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.CandidateSchool;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateSchoolAddDto;

import java.util.List;

public interface CandidateSchoolService {

    DataResult<List<CandidateSchool>> getAll();

    DataResult<List<CandidateSchool>> getByGradYear(Short sortDirection);

    Result add(CandidateSchoolAddDto candidateSchoolAddDto);

    Result deleteById(int candSchId);

    Result updateSchool(int schoolId, int candSchId);

    Result updateDepartment(short departmentId, int candSchId);

    Result updateStartYear(short schoolStartYear, int candSchId);

    Result updateGradYear(Short graduationYear, int candSchId);

}
