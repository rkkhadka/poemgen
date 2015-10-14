package com.appio.poemgen.parser.impl;

import com.appio.poemgen.GrammarParserException;
import com.appio.poemgen.model.Grammar;
import com.appio.poemgen.model.Rule;
import com.appio.poemgen.parser.GrammarRuleParser;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by khadka-home on 10/12/2015.
 */
public class GrammarRuleParserImplTest extends TestCase {
    private GrammarRuleParser parser = new GrammarRuleParserImpl();
    private static final int RULE_COUNT = 7;
    private static String BAD_TEST_FILE = "bad_grammar.txt";
    private static String TEST_FILE = "grammar.txt";

    @Test
    public void testParseGrammerException() throws IOException {
        List<String> ruleNames = new ArrayList<String>();
        Collections.addAll(ruleNames, "POEM", "LINE", "ADJECTIVE", "NOUN", "PRONOUN", "VERB", "PREPOSITION");

        String message = null;
        Grammar grammar = null;
        try {
            grammar = parser.parseGrammerRule(BAD_TEST_FILE);
        } catch(GrammarParserException ex) {
            message = ex.getMessage();
            assertTrue(ex != null);
        }

        assertTrue(message.equals("Invalid rule given, check grammar rule PREPOSITION"));
    }

    @Test
    public void testParseIoException() {
        Grammar grammar = null;
        try {
            grammar = parser.parseGrammerRule("FILE_DOESNT_EXIST");
        } catch(Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void testParse() {
        List<String> ruleNames = new ArrayList<String>();
        Collections.addAll(ruleNames, "POEM", "LINE", "ADJECTIVE", "NOUN", "PRONOUN", "VERB", "PREPOSITION");

        List<String> ruleValues = new ArrayList<String>();
        Collections.addAll(ruleValues,
                "<LINE> <LINE>",
                "<NOUN>|<PREPOSITION> $LINEBREAK",
                "black|white|dark <NOUN>|<ADJECTIVE>|$END",
                "heart|flower|field|meadow|pasture|harvest <VERB>|<PREPOSITION>|$END",
                "my|your|his|her <NOUN>|<ADJECTIVE>",
                "runs|walks|stands <PREPOSITION>|<PRONOUN>|$END",
                "above|across|against|along <NOUN>|<PRONOUN>|<ADJECTIVE>");

        String message = null;
        Grammar grammar = null;
        try {
            grammar = parser.parseGrammerRule(TEST_FILE);
        } catch(Exception ex) {

        }

        Map<String, Rule> rules = grammar.getRules();
        assertEquals(RULE_COUNT, rules.size());

        for (String rule : rules.keySet()) {
            assert(ruleNames.contains(rule));
        }
    }
}