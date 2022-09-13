package expression;

import expression.generic.Generator;
import expression.generic.TripleExpression;

public abstract class BinaryExpression<T> implements TripleExpression<T> {
    protected Generator<T> generator;
    protected TripleExpression<T> variableleft;
    protected TripleExpression<T> variableright;
    private final String typeOfOperation;

    public BinaryExpression(Generator<T> generator, TripleExpression<T> variableleft, TripleExpression<T> variableright, String typeOfOperation) {
        this.generator = generator;
        this.variableleft = variableleft;
        this.variableright = variableright;
        this.typeOfOperation = typeOfOperation;
    }

    public abstract T calc(T a, T b);

    public T evaluate(T x, T y, T z) {
        return calc(variableleft.evaluate(x,y,z), variableright.evaluate(x,y,z));
    }


}
