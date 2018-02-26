package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

import static java.util.Collections.emptyList;

public class EndNode implements Node {

    @Override
    public List<LexicalRule> getRules() {
        return emptyList();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
