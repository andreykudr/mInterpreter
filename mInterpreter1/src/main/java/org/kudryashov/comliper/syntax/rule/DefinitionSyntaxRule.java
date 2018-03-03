package org.kudryashov.comliper.syntax.rule;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes;
import org.kudryashov.comliper.syntax.Context;

import static org.kudryashov.comliper.Instances.IDENTIFIERS;
import static org.kudryashov.comliper.Instances.VARIABLE_TYPES;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;

public class DefinitionSyntaxRule implements SyntaxRule<Identifier> {

    private final Repository<Identifier> identifiers = Instances.get(IDENTIFIERS);
    private final Repository<VariableType> types = Instances.get(VARIABLE_TYPES);

    @Override
    public Identifier apply(Context lexemes) {
        ElementName type = lexemes.pop();
        if (check(type, INT, FLOAT, BOOL)) {
            ElementName name = lexemes.pop();
            if (checkIdentifier(name)) {
                Identifier identifier = identifiers.get(name);
                identifier.define(types.get(type));
                return identifier;
            }
        }
        return null;
    }

    private boolean checkIdentifier(ElementName element) {
        return element instanceof Identifier.Name;
    }
}
