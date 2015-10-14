package com.appio.poemgen;

import com.appio.poemgen.model.Grammar;
import com.appio.poemgen.model.Rule;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by khadka-home on 10/13/2015.
 */
public class RandomPoemGenTest extends TestCase {

    private static final String RULE_KEY = "RULE";
    private static final String RULE_NAME = "<RULE>";
    private static final String SUB_RULE_KEY = "SUBRULE";
    private static final String SUB_RULE_NAME = "<SUBRULE>";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Mock
    private Grammar grammar;

    private List<List<String>> evaluables;
    private List<String> words;
    private List<String> references;
    private Map<String, Rule> rules;

    @Override
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));

        words = new ArrayList<String>();
        references = new ArrayList<String>();
        evaluables = new ArrayList<List<String>>();

        Collections.addAll(words, "first", "second");
        Collections.addAll(references, SUB_RULE_NAME);
        Collections.addAll(evaluables, words, references);

        grammar = new Grammar(RULE_KEY);
        Rule rule = new Rule(RULE_KEY);
        rule.setEvaluables(evaluables);
        grammar.addRules(RULE_KEY, rule);

        Rule subRule = new Rule(SUB_RULE_KEY);

        List<String> words1 = new ArrayList<String>();
        List<List<String>> evaluables1 = new ArrayList<List<String>>();

        Collections.addAll(words1, "one", "two");
        Collections.addAll(evaluables1, words1);
        subRule.setEvaluables(evaluables1);
        grammar.addRules(SUB_RULE_KEY, subRule);
    }

    @Override
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    /**
     * This test method ensures that words and referenced words are in the generated content using Mock Grammar
     */
    @Test
    public final void testGeneratePoem() {
        RandomPoemGen poemGen = new RandomPoemGen();
        poemGen.generatePoem(grammar);

        assertTrue(outContent != null && (outContent.toString().indexOf("first") >-1 || outContent.toString().indexOf("second") >-1));
    }

}