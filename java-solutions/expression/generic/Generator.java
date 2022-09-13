package expression.generic;

public interface Generator<T> {
    T add(T a, T b);
    T multiply(T a, T b);
    T subtract(T a, T b);
    T unaryminus(T a);
    T divide(T a, T b);
    T translate(String a);
    T translate(int a);
}
