package com.github.appreciated.example.notification;

public class TestSearchResult {
    private final String header;
    private final String description;

    public TestSearchResult(String header, String description) {
        this.header = header;
        this.description = description;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }
}
