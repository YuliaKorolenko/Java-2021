package expression;

import expression.generic.TripleExpression;

public class Variable<T> implements TripleExpression<T> {

    private final String lilvariable;

    public Variable(final String lilvariable) {
        this.lilvariable = lilvariable;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (lilvariable) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new AssertionError("Name variable is incorrect");
    }

    public String toString() {
        return lilvariable;
    }
}
