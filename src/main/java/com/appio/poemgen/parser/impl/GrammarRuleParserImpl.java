package com.appio.poemgen.parser.impl;

import com.appio.poemgen.GrammarParserException;
import com.appio.poemgen.model.Grammar;
import com.appio.poemgen.model.Rule;
import com.appio.poemgen.parser.GrammarRuleParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads a file and converts file into Grammar object using following logic:
 * 1. Rule name and definitions are separated by :
 * 2. words & references are separated by " ",
 * 3. create list of evaluates using |, create list of words/references and add into word/reference List
 * Created by khadka-home on 10/12/2015.
 */
public class GrammarRuleParserImpl implements GrammarRuleParser {

    private static final String RULE_NAME_VAL_SPLITTER = ":";
    private static final String WORD_REF_SPLITTER = " ";
    private static final String WORD_OR_REFERENCE_SPLITTER = "\\|";

    public Grammar parseGrammerRule(String grammarRuleFileName) throws IOException, GrammarParserException {
        int i = 0;
        BufferedReader reader = null;
        String line = null;
        Grammar grammar = null;

        try {
            reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(grammarRuleFileName)));

            while((line = reader.readLine()) != null) {
                String ruleNameVal[] = line.split(RULE_NAME_VAL_SPLITTER);
                if (ruleNameVal.length != 2) {
                    System.out.println("Problem occured while parsing message" + line);
                    throw new GrammarParserException("Invalid rule given, check grammar rule "+line);
                }

                if (i == 0) {
                    grammar = new Grammar(ruleNameVal[0].trim());
                    i++;
                }
                grammar.addRules(ruleNameVal[0].trim(), generateRule(ruleNameVal[0].trim(), ruleNameVal[1].trim()));
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return grammar;
    }

    private Rule generateRule(String ruleName, String ruleDefs) throws GrammarParserException {
        Rule rule = new Rule(ruleName);
        String[] ruleDefEvals = ruleDefs.split(WORD_REF_SPLITTER);

        List<List<String>> evalList = new ArrayList<List<String>>(ruleDefEvals.length);
        for (String evaluable : ruleDefEvals) {

            String[] wordRefs = evaluable.trim().split(WORD_OR_REFERENCE_SPLITTER);
            List<String> wordRefList = new ArrayList<String>(wordRefs.length);

            for (String wordRef : wordRefs) {
                wordRefList.add(wordRef);
            }

            evalList.add(wordRefList);
        }

        rule.setEvaluables(evalList);

        return rule;
    }

    public static void main(String[] args) throws Exception {
//        GrammarRuleParser parser = new GrammarRuleParserImpl();
//        Grammar grammar = parser.parseGrammerRule("poem_grammar.txt");
        String name = "<NAME>";

        System.out.println(name.replaceAll("[<>]",""));
        //System.out.println(grammar.getRootRuleName()+" - "+grammar.getRules().size());
    }
}
