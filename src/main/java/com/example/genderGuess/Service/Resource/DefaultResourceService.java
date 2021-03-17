package com.example.genderGuess.Service.Resource;

import com.example.genderGuess.Configuration.ResourcePathResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DefaultResourceService implements ResourceService {


    private ResourcePathResolver pathResolver;

    private final Resource femaleResource;
    private final Resource maleResource;

    public DefaultResourceService(@Autowired ResourcePathResolver pathResolver) {

        this.pathResolver = pathResolver;

        femaleResource = new ClassPathResource(pathResolver.getFemaleResourceLocation());
        maleResource = new ClassPathResource(pathResolver.getMaleResourceLocation());
    }


    @Override
    public Resource getFemaleResource() {
        return femaleResource;
    }

    @Override
    public Resource getMaleResource() {
        return maleResource;
    }
}
