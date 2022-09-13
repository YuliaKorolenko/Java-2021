package expression;

import expression.generic.TripleExpression;

public class Const<T> implements TripleExpression<T> {

    private final T value;

    public Const(final T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }
}
