package expression.generic;

public class GeneratorShort implements Generator<Short> {

    @Override
    public Short add(Short a, Short b) {
        return (short) (a + b);
    }

    @Override
    public Short multiply(Short a, Short b) {
        return (short) (a * b);
    }

    @Override
    public Short subtract(Short a, Short b) {
        return (short) (a - b);
    }

    @Override
    public Short unaryminus(Short a) {
        return (short) (-a);
    }

    @Override
    public Short divide(Short a, Short b) {
        return (short) (a / b);
    }

    @Override
    public Short translate(String a) {
        return Short.parseShort(a);
    }

    @Override
    public Short translate(int a) {
        return (short) a;
    }
}
