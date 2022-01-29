package com.emrebalikci.connecthub.business.abstracts.check;

import com.emrebalikci.connecthub.entities.concretes.JobAdvertisement;
import com.emrebalikci.connecthub.entities.concretes.JobAdvertisementUpdate;
import com.emrebalikci.connecthub.entities.concretes.dtos.JobAdvertisementDto;




import java.util.Map;

public interface JobAdvertisementCheckService extends BaseCheckService {

    Map<String, String> getErrors();

    void existsJobAdvertById(Integer jobAdvertId);

    void checkIfViolatesUkUpdate(JobAdvertisementDto jobAdvertDto, JobAdvertisementUpdate jobAdvertUpdate);

  
    void checkIfViolatesUkAdd(JobAdvertisementDto jobAdvDto);

    boolean changed(JobAdvertisement jobAdvert, JobAdvertisementUpdate jobAdvertUpdate);

}
