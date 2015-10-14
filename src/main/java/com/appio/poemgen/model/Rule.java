package com.appio.poemgen.model;

import java.util.List;

/**
 * Rule Domain, holds ruleName & list of list of evaluables (word/reference/keyword)
 * Created by khadka-home on 10/12/2015.
 */
public class Rule {

    private String name;
    private List<List<String>> evaluables;

    public Rule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<List<String>> getEvaluables() {
        return evaluables;
    }

    public void setEvaluables(List<List<String>> evaluables) {
        this.evaluables = evaluables;
    }
}
