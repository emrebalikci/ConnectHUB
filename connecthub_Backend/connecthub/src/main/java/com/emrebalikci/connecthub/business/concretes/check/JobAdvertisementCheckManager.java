package com.emrebalikci.connecthub.business.concretes.check;

import com.emrebalikci.connecthub.business.abstracts.check.BaseCheckManager;
import com.emrebalikci.connecthub.business.abstracts.check.JobAdvertisementCheckService;
import com.emrebalikci.connecthub.core.utilities.CheckUtils;
import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.EntityNotExistsException;
import com.emrebalikci.connecthub.core.utilities.exception.exceptions.UKViolationException;
import com.emrebalikci.connecthub.dataAccess.abstracts.JobAdvertisementDao;
import com.emrebalikci.connecthub.dataAccess.abstracts.JobAdvertisementUpdateDao;
import com.emrebalikci.connecthub.entities.concretes.JobAdvertisement;
import com.emrebalikci.connecthub.entities.concretes.JobAdvertisementUpdate;
import com.emrebalikci.connecthub.entities.concretes.dtos.JobAdvertisementDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobAdvertisementCheckManager extends BaseCheckManager implements JobAdvertisementCheckService {

    private final JobAdvertisementDao jobAdvertisementDao;
    private final JobAdvertisementUpdateDao jobAdvertisementUpdateDao;

    @SneakyThrows
    @Override
    public void existsJobAdvertById(Integer jobAdvertId) {
        if (CheckUtils.notExistsById(jobAdvertisementDao, jobAdvertId))
            throw new EntityNotExistsException(Msg.NOT_EXIST.get("Job Advertisement"));
    }

    @SneakyThrows
    @Override
    public void checkIfViolatesUkUpdate(JobAdvertisementDto jobAdvertDto, JobAdvertisementUpdate jobAdvUpd) {
        boolean ukFieldsChanged = !(jobAdvUpd != null &&
                CheckUtils.equals(jobAdvUpd.getPosition().getId(), jobAdvertDto.getPositionId()) &&
                CheckUtils.equals(jobAdvUpd.getCity().getId(), jobAdvertDto.getCityId()) &&
                jobAdvUpd.getJobDescription().equals(jobAdvertDto.getJobDescription()) &&
                CheckUtils.equals(jobAdvUpd.getEmployer().getId(), jobAdvertDto.getEmployerId()));
        if (ukFieldsChanged && jobAdvertisementUpdateDao.existsByCity_IdAndPosition_IdAndEmployer_IdAndJobDescription
                (jobAdvertDto.getCityId(), jobAdvertDto.getPositionId(), jobAdvertDto.getEmployerId(), jobAdvertDto.getJobDescription()))
            throw new UKViolationException(Msg.UK_JOB_ADV_UPD.get());
    }

    @SneakyThrows
    @Override
    public void checkIfViolatesUkAdd(JobAdvertisementDto jobAdvertDto) {
        if (jobAdvertisementDao.existsByCity_IdAndPosition_IdAndEmployer_IdAndJobDescription
                (jobAdvertDto.getCityId(), jobAdvertDto.getPositionId(), jobAdvertDto.getEmployerId(), jobAdvertDto.getJobDescription()))
            throw new UKViolationException(Msg.UK_JOB_ADV_ADD.get());
    }

    @Override
    public boolean changed(JobAdvertisement jobAdvert, JobAdvertisementUpdate jobAdvertUpdate) {
        jobAdvertUpdate = jobAdvertUpdate != null ? jobAdvertUpdate : jobAdvert.getJobAdvertisementUpdate();
        return !(CheckUtils.equals(jobAdvert.getPosition().getId(), jobAdvertUpdate.getPosition().getId()) &&
                CheckUtils.equals(jobAdvert.getCity().getId(), jobAdvertUpdate.getCity().getId()) &&
                CheckUtils.equals(jobAdvert.getMinSalary(), jobAdvertUpdate.getMinSalary()) &&
                CheckUtils.equals(jobAdvert.getMaxSalary(), jobAdvertUpdate.getMaxSalary()) &&
                jobAdvert.getWorkModel().equals(jobAdvertUpdate.getWorkModel()) &&
                jobAdvert.getWorkTime().equals(jobAdvertUpdate.getWorkTime()) &&
                CheckUtils.equals(jobAdvert.getOpenPositions(), jobAdvertUpdate.getOpenPositions()) &&
                CheckUtils.equals(jobAdvert.getDeadline(), jobAdvertUpdate.getDeadline()) &&
                jobAdvert.getJobDescription().equals(jobAdvertUpdate.getJobDescription()));
    }

}
