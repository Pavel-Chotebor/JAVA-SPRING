package com.egreenfox.trialexam.services;

import com.egreenfox.trialexam.models.SecretNumber;
import com.egreenfox.trialexam.repositories.SecretNumberRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SecretNumberServiceImpl implements SecretNumberService {

    private SecretNumberRepository secretNumberRepository;

    public SecretNumberServiceImpl(SecretNumberRepository secretNumberRepository) {
        this.secretNumberRepository = secretNumberRepository;
    }

    @Override
    public void saveSecretNumber(SecretNumber secretNumber) {
        secretNumberRepository.save(secretNumber);
    }

    @Override
    public String generateRandomSecretNumber() {
        Random random = new Random();
        String randomSecretNumber = String.format("%04d", random.nextInt(10000));

        return randomSecretNumber;
    }

    @Override
    public SecretNumber findSecretNumberById(Integer id) {
        return secretNumberRepository.findById(id).orElse(null);
    }
}
