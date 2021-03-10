package com.example.genderGuess.Controller;

import com.example.genderGuess.Dictionary;
import com.example.genderGuess.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("dictionary")
public class DictionaryController {

    @Autowired
    Dictionary dictionaryService;
    TreeMap<Character, Long> dictionary;

    @Autowired
    ResourceService resourceService;
    Resource resource;

    //dictionary?gender=male&range=ab
    @GetMapping(params = {"gender", "range"})
    public void getGenderNamesInRange(String gender, String range, HttpServletResponse response){

        gender = gender
                .toUpperCase(Locale.ROOT)
                .trim();
        range = range
                .toUpperCase(Locale.ROOT)
                .trim();

        if (gender.equals("FEMALE")){
            resource = resourceService.getFemaleResource();
            dictionary = dictionaryService.getFemaleDictionary();
        } else if (gender.equals("MALE")){
            resource = resourceService.getMaleResource();
            dictionary = dictionaryService.getMaleDictionary();
        }

        Character start = range.charAt(0);
        Character end = range.charAt(1);

        //TODO implement solution if user enter letters in reverse order like 'ba'
        Long startLine = dictionary.get(start);
        long iterationsCount = dictionary.get(end) - startLine;

        try (InputStream in = resource.getInputStream()) {
            OutputStream out = response.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            AtomicLong counter = new AtomicLong(0L);
            br.lines().skip(startLine).forEach(
                    l -> {
                        try {
                            out.write(l.getBytes(StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //TODO implement breaking of iteration (Spliterator?)
                        counter.getAndIncrement();
                    }
            );

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
