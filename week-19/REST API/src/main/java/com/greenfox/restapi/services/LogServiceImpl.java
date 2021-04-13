package com.greenfox.restapi.services;

import com.greenfox.restapi.models.Log;
import com.greenfox.restapi.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void saveLog(Log log) {
        logRepository.save(log);
    }

    @Override
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    @Override
    public String getAllLogsLikeString() {
        List<Log> logs = logRepository.findAll();
        String allLogsLikeString = "";

        for (int i = 0; i< logRepository.findAll().size(); i++) {
            allLogsLikeString +=  logs.get(i).getId() + "," + logs.get(i).getData() + "," +
                    logs.get(i).getEndpoint();
        } return allLogsLikeString;

    }
}
