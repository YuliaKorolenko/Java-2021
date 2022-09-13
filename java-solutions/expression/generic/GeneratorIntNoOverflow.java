package expression.generic;

public class GeneratorIntNoOverflow implements Generator<Integer> {

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer unaryminus(Integer a) {
        return -a;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public Integer translate(String a) {
        return Integer.parseInt(a);
    }

    @Override
    public Integer translate(int a) {
        return a;
    }
}
