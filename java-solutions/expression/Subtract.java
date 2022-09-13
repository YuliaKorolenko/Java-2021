package expression;

import expression.generic.Generator;
import expression.generic.TripleExpression;

public class Subtract<T> extends BinaryExpression<T> {

    public Subtract(Generator<T> generator, TripleExpression<T> variableleft, TripleExpression<T> variableright) {
        super(generator, variableleft, variableright, "-");
    }

    @Override
    public T calc(T a, T b) {
        return generator.subtract(a,b);
    }
}
