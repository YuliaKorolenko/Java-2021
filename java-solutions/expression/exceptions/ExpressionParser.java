package expression.exceptions;

import expression.*;
import expression.generic.Generator;
import expression.generic.TripleExpression;
import expression.parser.BaseParser;
import expression.parser.StringSource;

public class ExpressionParser<T> extends BaseParser {
    
    protected Generator<T> generator;
    
    public ExpressionParser(Generator<T> generator){
        this.generator = generator;
    }

    public TripleExpression<T> parse(String partsOfExpression) {
        this.source = new StringSource(partsOfExpression);
        nextChar();
        TripleExpression<T> expression = parseSubstractAdd();
        if (!eof()) {
            throw new MissEndOfFileException(source.buildMes("Expected end of input, actual: " + "'" + ch + "'"));
        }
        return expression;
    }

    private TripleExpression<T> parseSubstractAdd() {
        TripleExpression<T> firstExpression = parseMultiplyDivide();
        char symbolForOperation;
        while (!eof()) {
            skipWhitespaces();
            symbolForOperation = ch;
            if (ch != '+' && ch != '-') {
                break;
            }
            nextChar();
            TripleExpression<T> secondExpression = parseMultiplyDivide();
            if (symbolForOperation == '+') {
                firstExpression = new Add<T>(generator, firstExpression, secondExpression);
            } else {
                firstExpression = new Subtract<T>(generator, firstExpression, secondExpression);
            }
        }
        return firstExpression;
    }

    private TripleExpression<T> parseMultiplyDivide() {
        TripleExpression<T> firstExpression = WayToLittle();
        char symbolForOperation;
        while (!eof()) {
            skipWhitespaces();
            symbolForOperation = ch;
            if (ch != '*' && ch != '/') {
                break;
            }
            nextChar();
            TripleExpression<T> secondExpression = WayToLittle();
            if (symbolForOperation == '*') {
                firstExpression = new Multiply<T>(generator, firstExpression, secondExpression);
            } else {
                firstExpression = new Divide<T>(generator, firstExpression, secondExpression);
            }
        }
        return firstExpression;
    }

    private TripleExpression<T> WayToLittle() {
        skipWhitespaces();
        if (test('(')) {
            skipWhitespaces();
            TripleExpression<T> expression = parseSubstractAdd();
            expect(')');
            return expression;
        } else if ('0' <= ch && ch <= '9') {
            return parseConst(false);
        } else if (ch == '-') {
            return parseUnaryMinys();
        } else if (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z')) {
            return parseVariety();
        }
        throw new MissVarorBrascketsException(source.buildMes("Expected constant, var or brackets, actual: '" + ch + "'"));
    }

    private TripleExpression<T> parseUnaryMinys() {
        expect('-');
        skipWhitespaces();
        if (test('(')) {
            TripleExpression<T> expression = new UnaryMinus<T>(generator, parseSubstractAdd());
            expect(')');
            return expression;
        } else if ('0' <= ch && ch <= '9') {
            return parseConst(true);
        } else if (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z')) {
            return new UnaryMinus<T>(generator,parseVariety());
        } else if (ch == '-') {
            return new UnaryMinus<T>(generator, parseUnaryMinys());
        }
        throw new MissVarorBrascketsException(source.buildMes("Expected constant, var or brackets, actual: "+ "'" + ch + "'"));
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }

    private TripleExpression<T> parseConst(boolean needMinus) {
        skipWhitespaces();
        StringBuilder sb = new StringBuilder();
        if (needMinus) {
            sb.append('-');
        }
        while ('0' <= ch && ch <= '9') {
            sb.append(ch);
            nextChar();
        }
        return new Const<T>(generator.translate(sb.toString()));
    }

    private TripleExpression<T> parseVariety() {
        skipWhitespaces();
        StringBuilder sb = new StringBuilder();
        while (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z')) {
            sb.append(ch);
            nextChar();
        }
        if (sb.toString().equals("x") || sb.toString().equals("y") || sb.toString().equals("z")) {
            return new Variable<>(sb.toString());
        } else {
            throw new MissXYZorFunctionException(source.buildMes("Expected var or function, actual: " + "'" + sb.toString() + "'"));
        }
    }
}
