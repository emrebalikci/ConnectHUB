package com.emrebalikci.connecthub.core.adapters.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface CloudinaryService {

    Map<?, ?> upload(MultipartFile multipartFile);

    Map<?, ?> delete(String id);

    File convert(MultipartFile multipartFile);

}
