package com.egreenfox.trialexam.services;

import com.egreenfox.trialexam.models.SecretNumber;
import org.springframework.stereotype.Service;

@Service
public interface SecretNumberService {

    void saveSecretNumber (SecretNumber secretNumber);

    String generateRandomSecretNumber ();

    SecretNumber findSecretNumberById (Integer id);
}
