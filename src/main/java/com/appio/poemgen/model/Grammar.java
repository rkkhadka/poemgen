package com.appio.poemgen.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Grammar model, holds
 * main Poem root,
 * mapping of ruleName & List of rules,
 * enum of keywords (LINEBREAK & END)
 *
 * Created by khadka-home on 10/12/2015.
 */
public class Grammar {
    //  main poem root
    private String rootRuleName;
    //  mapping of rule key and Rule based on the definition
    private Map<String, Rule> rules;

    public Grammar(String rootRuleName) {
        this.rootRuleName = rootRuleName;
        rules = new HashMap<String, Rule>();
    }

    public String getRootRuleName() {
        return rootRuleName;
    }

    public void addRules(String ruleName, Rule rule) {
        rules.put(ruleName, rule);
    }

    public Map<String, Rule> getRules() {
        return rules;
    }

    public enum Keywords {
        $END("\r"), $LINEBREAK("\n");

        private String value;

        private Keywords(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }
}
