package com.greenfox.restapi.services;

import com.greenfox.restapi.models.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {

    void saveLog(Log log);

    List<Log> getAllLogs();

    String getAllLogsLikeString();


}
