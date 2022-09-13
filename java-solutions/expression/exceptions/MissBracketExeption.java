package expression.exceptions;

import expression.parser.ParseException;

public class MissBracketExeption  extends ParseException {
    public MissBracketExeption(String message) {
        super(message);
    }
}
