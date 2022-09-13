package expression.generic;

public class GeneratorInt implements Generator<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        int returnAdd = a + b;
        if (a > 0 && b > 0 && returnAdd <= 0) {
            throw new ArithmeticException("overflow");
        }
        if (a < 0 && b < 0 && returnAdd >= 0) {
            throw new ArithmeticException("overflow");
        }
        return returnAdd;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        int returnMultiply = a * b;
        if (a != 0 && b != 0 && (returnMultiply / b != a || returnMultiply / a != b)){
            throw new ArithmeticException("overflow");
        }
        return returnMultiply;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        int returnSubstract = a - b;
        if (a < 0 && b > 0 && returnSubstract >= 0) {
            throw new ArithmeticException("overflow");
        }
        if (a >= 0 && b < 0 && returnSubstract <= 0) {
            throw new ArithmeticException("overflow");
        }
        return returnSubstract;
    }

    @Override
    public Integer unaryminus(Integer a) {
        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow");
        }
        return -a;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (b == -1 && a == Integer.MIN_VALUE){
            throw new ArithmeticException("overflow");
        }
        if (b==0) {
            throw new ArithmeticException("Divide zero");
        }
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
