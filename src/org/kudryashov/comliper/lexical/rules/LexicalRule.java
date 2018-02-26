package org.kudryashov.comliper.lexical.rules;

import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.nodes.Node;

public interface LexicalRule {

    boolean satisfied(LexicalContext context);

    Node apply(LexicalContext context);
}
