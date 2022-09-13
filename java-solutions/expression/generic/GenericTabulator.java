package expression.generic;

import expression.exceptions.ExpressionParser;


public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return call(translateMode(mode), x1, x2, y1, y2, z1, z2, expression);
    }

    public static void main(String[] args) {
        GenericTabulator tabulator = new GenericTabulator();
        try {
            Object[][][] massiv = tabulator.tabulate(args[0].substring(1, args[0].length()), args[1], -2, 2, -2, 2, -2, 2);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        System.out.print(massiv[i][j][k] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error! Exception!");
        }
    }


    private <T> Object[][][] call(Generator<T> generator, int x1, int x2, int y1, int y2, int z1, int z2, String expression) {
        ExpressionParser<T> l = new ExpressionParser<>(generator);
        TripleExpression<T> parsing = l.parse(expression);
        Object[][][] massiv = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        massiv[i - x1][j - y1][k - z1] = parsing.evaluate(generator.translate(i), generator.translate(j), generator.translate(k));
                    } catch (ArithmeticException ignored){

                    }
                }

            }
        }
        return massiv;
    }

    private Generator<?> translateMode(String mode) {
        switch (mode) {
            case "i":
                return new GeneratorInt();
            case "d":
                return new GeneratorDouble();
            case "bi":
                return new GenetorBigInteger();
            case "u":
                return new GeneratorIntNoOverflow();
            case "l":
                return new GeneratorLong();
            case "s":
                return new GeneratorShort();
            default:
                return null;
        }
    }
}
