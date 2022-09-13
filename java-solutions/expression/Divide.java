package expression;

import expression.generic.Generator;
import expression.generic.TripleExpression;

public class Divide<T> extends BinaryExpression<T> {

    public Divide(Generator<T> generator, TripleExpression<T> variableleft, TripleExpression<T> variableright) {
        super(generator, variableleft, variableright, "/");
    }

    @Override
    public T calc(T a, T b) {
        return generator.divide(a,b);
    }
}
