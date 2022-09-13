package expression.generic;

public class GeneratorLong implements Generator<Long> {
    @Override
    public Long add(Long a, Long b) {
        return a + b;
    }

    @Override
    public Long multiply(Long a, Long b) {
        return a * b;
    }

    @Override
    public Long subtract(Long a, Long b) {
        return a - b;
    }

    @Override
    public Long unaryminus(Long a) {
        return -a;
    }

    @Override
    public Long divide(Long a, Long b) {
        return a / b;
    }

    @Override
    public Long translate(String a) {
        return Long.parseLong(a);
    }

    @Override
    public Long translate(int a) {
        return (long) a;
    }
}
