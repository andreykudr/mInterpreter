package org.kudryashov.comliper.lexical;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.context.StringLexicalContext;
import org.kudryashov.comliper.lexical.nodes.Node;
import org.kudryashov.comliper.lexical.nodes.StartNode;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

public class LexicalAnalyzer {

    private Node rootNode = new StartNode();

    public List<ElementName> analyze(String text) {
        LexicalContext context = new StringLexicalContext(text);
        applyRules(context, rootNode);
        return context.getOutput();
    }

    private void applyRules(LexicalContext context, Node node) {
        for (LexicalRule rule : node.getRules()) {
            if (rule.satisfied(context)) {
                applyRuleForNode(context, rule);
                break;
            }
        }
    }

    private void applyRuleForNode(LexicalContext context, LexicalRule rule) {
        Node newNode = rule.apply(context);
        if (!newNode.isEnd()) {
            applyRules(context, newNode);
        }
    }

}
