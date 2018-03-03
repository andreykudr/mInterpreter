package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.MORE;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.MORE_OR_EQUALS;

public class MoreNode implements Node {

    private Repository<Separator> separators = Instances.get(Instances.SEPARATORS);
    private List<LexicalRule> rules = asList(new MoreEqualRule(), new OtherRule());
    ;

    @Override
    public List<LexicalRule> getRules() {
        return rules;
    }

    private class MoreEqualRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == '=';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.readNextCharacter();
            context.putOutputElement(MORE_OR_EQUALS);
            return new StartNode();
        }
    }

    private class OtherRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return c != '=';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.putOutputElement(MORE);
            return new StartNode();
        }
    }
}
