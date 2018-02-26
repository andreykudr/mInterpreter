package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;

public class SeparatorNode implements Node {

    private final List<LexicalRule> rules = fillRules();

    @Override
    public List<LexicalRule> getRules() {
        return rules;
    }

    private abstract class SeparatorRule implements LexicalRule {

        abstract Separator.Name getSeparatorName();

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == getSeparatorName().getValue().charAt(0);
        }

        @Override
        public Node apply(LexicalContext context) {
            context.putOutputElement(getSeparatorName());
            context.readNextCharacter();
            return new StartNode();
        }
    }

    private class DotRule extends SeparatorRule {

        @Override
        Separator.Name getSeparatorName() {
            return DOT;
        }

        @Override
        public Node apply(LexicalContext context) {
            context.putOutputElement(DOT);
            return new EndNode();
        }
    }

    private List<LexicalRule> fillRules() {
        return asList(
                new DotRule(),
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return COMMA;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return SEMICOLON;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return COLON;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return LEFTBRACE;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return RIGHTBRACE;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return PLUS;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return MINUS;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return MULTIPLY;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return DIVIDE;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return LOGIC_OR;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return LOGIC_AND;
                    }
                },
                new SeparatorRule() {
                    @Override
                    Separator.Name getSeparatorName() {
                        return EQUAL;
                    }
                });
    }
}
