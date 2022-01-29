package com.emrebalikci.connecthub.core.adapters.concretes;

import com.emrebalikci.connecthub.core.adapters.abstracts.MernisService;
import com.emrebalikci.connecthub.entities.concretes.dtos.CandidateAddDto;
import org.springframework.stereotype.Component;

@Component
public class MernisServiceAdapter implements MernisService {

    //I removed mernis for now because it causes an error while deploying the app
    @Override
    public boolean realPerson(CandidateAddDto dto) {

        return true;

    }

    /*public boolean realPerson(String tcno, String name, String surname, short birthYear) {

        try {
            return new KPSPublicLocator().getKPSPublicSoap().TCKimlikNoDogrula(Long.parseLong(tcno), name, surname, birthYear);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

    }*/

}
