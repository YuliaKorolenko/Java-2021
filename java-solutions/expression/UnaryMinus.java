package expression;

import expression.generic.Generator;
import expression.generic.TripleExpression;

public class UnaryMinus<T> implements TripleExpression<T> {

    private TripleExpression<T> expression;
    private Generator<T> generator;

    public UnaryMinus(Generator<T> generator, TripleExpression<T> expression) {
        this.expression = expression;
        this.generator = generator;
    }

    @Override
    public String toString() {
        return "-" + expression.toString();
    }


    @Override
    public T evaluate(T x, T y, T z) {
        return generator.unaryminus(expression.evaluate(x, y, z));
    }
}
