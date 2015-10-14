package com.appio.poemgen;

import com.appio.poemgen.model.Grammar;
import com.appio.poemgen.model.Rule;
import com.appio.poemgen.parser.GrammarRuleParser;
import com.appio.poemgen.parser.impl.GrammarRuleParserImpl;

import java.util.List;
import java.util.Random;

/**
 * Created by khadka-home on 10/13/2015.
 */
public class RandomPoemGen {

    private GrammarRuleParser parser;

    static final String REFERENCE_PATTERN = "<[A-Z]+>";
    static final String KEYWORD_PATTERN = "\\$[A-Z]+";
    static final String REPLACE_PATTERN = "[<>]";
    static final String SPACE = " ";

    public void generatePoem(Grammar grammar) {
        printWordKeyWord(grammar.getRules().get(grammar.getRootRuleName()), grammar);
    }

    public void printWordKeyWord(Rule rule, Grammar grammar) {
        StringBuffer buffer = new StringBuffer();
        for (List<String> evaluables  : rule.getEvaluables()) {
            int random = randomIndex(0, evaluables.size());
            String evaluable = evaluables.get(random);

            if (evaluable.matches(REFERENCE_PATTERN)) {
                String subRuleName = evaluable.replaceAll(REPLACE_PATTERN,"");
                Rule subRule = grammar.getRules().get(subRuleName);
                printWordKeyWord(subRule, grammar);
            } else if (evaluable.matches(KEYWORD_PATTERN)) {
                buffer.append(Grammar.Keywords.valueOf(evaluable));
            } else {
                buffer.append(evaluable);
                buffer.append(SPACE);
            }
        }

        System.out.print(buffer.toString());
    }

    private int randomIndex(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void main(String[] args) throws Exception {
        RandomPoemGen poemGen = new RandomPoemGen();
        poemGen.parser = new GrammarRuleParserImpl();
        poemGen.generatePoem(poemGen.parser.parseGrammerRule("poem_grammar.txt"));
    }
}
