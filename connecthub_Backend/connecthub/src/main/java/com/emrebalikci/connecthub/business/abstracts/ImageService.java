package com.emrebalikci.connecthub.business.abstracts;

import com.emrebalikci.connecthub.core.utilities.results.DataResult;
import com.emrebalikci.connecthub.core.utilities.results.Result;
import com.emrebalikci.connecthub.entities.concretes.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    DataResult<Image> getById(int imgId);

    DataResult<Image> getByPublicId(String publicId);

    Result upload(MultipartFile multipartFile, int userId);

    Result deleteById(int imgId);

    Result deleteByPublicId(String publicId);

}
