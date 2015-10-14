package com.appio.poemgen.parser;

import com.appio.poemgen.GrammarParserException;
import com.appio.poemgen.model.Grammar;

import java.io.IOException;

/**
 * Created by khadka-home on 10/12/2015.
 */
public interface GrammarRuleParser {

    public Grammar parseGrammerRule(String grammarRuleFileName) throws IOException, GrammarParserException;
}
