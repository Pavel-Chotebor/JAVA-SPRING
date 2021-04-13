package com.greenfox.restapi.models;


public class LogsResult {

    private Integer entryCount;
    private String entries;

    public LogsResult(Integer entryCount, String entries) {
        this.entryCount = entryCount;
        this.entries = entries;
    }

    public LogsResult() {
    }

    public Integer getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Integer entryCount) {
        this.entryCount = entryCount;
    }

    public String getEntries() {
        return entries;
    }

    public void setEntries(String entries) {
        this.entries = entries;
    }
}
