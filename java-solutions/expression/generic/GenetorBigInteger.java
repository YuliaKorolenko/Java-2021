package expression.generic;

import java.math.BigInteger;

public class GenetorBigInteger implements Generator<BigInteger> {

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger unaryminus(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public BigInteger translate(String a) {
        return new BigInteger(a);
    }

    @Override
    public BigInteger translate(int a) {
        return BigInteger.valueOf(a);
    }

}
