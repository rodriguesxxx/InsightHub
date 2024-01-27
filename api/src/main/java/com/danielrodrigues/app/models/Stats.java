package com.danielrodrigues.app.models;

import java.io.Serializable;

public class Stats implements Serializable {
    private int commits;
    private int issues;
    private int pullRequests;

    public Stats() {}

    public Stats(int commits, int issues, int pullRequests) {
        this.commits = commits;
        this.issues = issues;
        this.pullRequests = pullRequests;
    }


    public int getCommits() {
        return this.commits;
    }

    public void setCommits(int commits) {
        this.commits = commits;
    }

    public int getIssues() {
        return this.issues;
    }

    public void setIssues(int issues) {
        this.issues = issues;
    }

    public int getPullRequests() {
        return this.pullRequests;
    }

    public void setPullRequests(int pullRequests) {
        this.pullRequests = pullRequests;
    }


    @Override
    public String toString() {
        return "{" +
            " commits='" + getCommits() + "'" +
            ", issues='" + getIssues() + "'" +
            ", pullRequests='" + getPullRequests() + "'" +
            "}";
    }



}
