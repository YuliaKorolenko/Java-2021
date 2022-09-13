package expression.generic;

public class GeneratorDouble implements Generator<Double> {

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double unaryminus(Double a) {
        return a;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double translate(String a) {
        return Double.parseDouble(a);
    }

    @Override
    public Double translate(int a) {
        return (double) a;
    }
}
