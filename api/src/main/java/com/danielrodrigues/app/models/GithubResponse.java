package com.danielrodrigues.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubResponse {
    @JsonProperty("total_count")
    private int totalCount;

    public int getTotalCount() {
        return this.totalCount;
    }
}
