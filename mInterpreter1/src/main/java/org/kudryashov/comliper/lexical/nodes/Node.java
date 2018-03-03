package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

public interface Node {

    List<LexicalRule> getRules();

    default boolean isEnd() {
        return false;
    }
}
