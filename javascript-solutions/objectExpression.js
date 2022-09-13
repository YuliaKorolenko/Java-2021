"use strict";

function Operation(args, c) {
    this.c = c;
    this.args = args;
}

Operation.prototype.evaluate = function (calc) {
    return function (x, y, z) {
        let elements = [];
        for (const l of this.args) {
            elements.push(l.evaluate(x, y, z));
        }
        return calc(...elements);
    }
}
Operation.prototype.toString = function () {
    let s = "";
    for (const l of this.args) {
        s += l.toString() + " ";
    }
    return s + this.c;
}
Operation.prototype.prefix = function () {
    let s = "(" + this.c;
    for (const l of this.args) {
        s += " " + l.prefix();
    }
    return s + ")";
}


function Add(...args) {
    Operation.call(this, args, "+");
}

Add.prototype = Object.create(Operation.prototype);
Add.prototype.evaluate = Operation.prototype.evaluate((a, b) => a + b);

function Divide(...args) {
    Operation.call(this, args, "/");
}

Divide.prototype = Object.create(Operation.prototype);
Divide.prototype.evaluate = Operation.prototype.evaluate((a, b) => a / b);

function Multiply(...args) {
    Operation.call(this, args, "*");
}

Multiply.prototype = Object.create(Operation.prototype);
Multiply.prototype.evaluate = Operation.prototype.evaluate((a, b) => a * b);


function Subtract(...args) {
    Operation.call(this, args, "-");
}

Subtract.prototype = Object.create(Operation.prototype);
Subtract.prototype.evaluate = Operation.prototype.evaluate((a, b) => a - b);

function Negate(...args) {
    Operation.call(this, args, "negate");
}


Negate.prototype = Object.create(Operation.prototype);
Negate.prototype.evaluate = Operation.prototype.evaluate((a) => -a);


function Avg5(...args) {
    Operation.call(this, args, "avg5");
}

Avg5.prototype = Object.create(Operation.prototype);
Avg5.prototype.evaluate = Operation.prototype.evaluate((a, b, c, d, e) => (a + b + c + d + e) / 5);

function Med3(...args) {
    Operation.call(this, args, "med3");
}

Med3.prototype = Object.create(Operation.prototype);
Med3.prototype.evaluate = Operation.prototype.evaluate((a, b, c) => Math.max(Math.max(Math.min(b, a), Math.min(b, c)), Math.min(a, c)));

function ArithMean(...args) {
    Operation.call(this, args, "arith-mean");
}

ArithMean.prototype = Object.create(Operation.prototype);
ArithMean.prototype.evaluate = Operation.prototype.evaluate((...args) => {
    let res = 0;
    for (const l of args) {
        res += l;
    }
    return res / args.length;
});

function GeomMean(...args) {
    Operation.call(this, args, "geom-mean");
}

GeomMean.prototype = Object.create(Operation.prototype);
GeomMean.prototype.evaluate = Operation.prototype.evaluate((...args) => {
        let res = 1;
        for (const l of args) {
            res *= Math.abs(l);
        }
        return Math.pow(res, 1 / args.length);
    }
)

function HarmMean(...args) {
    Operation.call(this, args, "harm-mean");
}

HarmMean.prototype = Object.create(Operation.prototype);
HarmMean.prototype.evaluate = Operation.prototype.evaluate((...args) => {
    let res = 0;
    for (const l of args) {
        res += 1 / l;
    }
    return args.length / res;
})


function Const(a) {
    this.a = a;
    this.evaluate = function (x, y, z) {
        return this.a
    };
    this.toString = function () {
        return String(this.a);
    }
    this.prefix = function () {
        return "" + a;
    }
}

function Variable(name) {
    this.name = name;
    this.evaluate = function (x, y, z) {
        if (this.name === "x") {
            return x;
        }
        if (this.name === "y") {
            return y;
        } else {
            return z;
        }
    }
    this.toString = function () {
        return this.name
    };
    this.prefix = function () {
        return name;
    }
}


function parsePrefix(expression) {
    let pos = 0;
    let res = parseAddSubstructMultiplyDivide();
    skipWhiteSpaces();
    if (!checkEnd()) {
        throw new ExpectedEndOfFile("Error! Expected end of input. Actual: " + validChar());
    }

    return res;

    function nextChar() {
        pos++;
    }

    function expected(ch) {
        if (validChar() === ch) {
            nextChar();
            return true;
        } else {
            throw new ExpectedSymbol("Error! Expected : " + ch + ". Actual : " + validChar());
        }
    }

    function checkEnd() {
        if (pos === expression.length) {
            return true;
        } else return false;
    }

    function validChar() {
        return expression[pos]
    }

    function skipWhiteSpaces() {
        while (/\s/.test(validChar())) {
            nextChar();
        }
    }

    function WrongVariety(message) {
        this.message = message;
    };
    WrongVariety.prototype = Object.create(Error.prototype);

    function WrongOperator(message) {
        this.message = message
    };
    WrongOperator.prototype = Object.create(Error.prototype);

    function ExpectedEndOfFile(message) {
        this.message = message
    };
    ExpectedEndOfFile.prototype = Object.create(Error.prototype);

    function ExpectedSymbol(message) {
        this.message = message
    };
    ExpectedSymbol.prototype = Object.create(Error.prototype);


    function parseOperator() {
        skipWhiteSpaces();
        let s = "";
        while (pos < expression.length && /\S/.test(validChar()) && validChar() !== "(") {
            s += validChar();
            nextChar();
        }
        return s;
    }


    function parseAddSubstructMultiplyDivide() {
        skipWhiteSpaces();
        let firstExpression;
        if (validChar() === "(") {
            nextChar();
            skipWhiteSpaces();
            let operator = parseOperator();
            skipWhiteSpaces();
            let expressions = [];
            while (validChar() !== ")") {
                expressions.push(parseAddSubstructMultiplyDivide());
                skipWhiteSpaces();
            }
            if (operator === "negate" && expressions.length === 1) {
                firstExpression = new Negate(...expressions);
            } else if (operator === "+" && expressions.length === 2) {
                firstExpression = new Add(...expressions);
            } else if (operator === "-" && expressions.length === 2) {
                firstExpression = new Subtract(...expressions);
            } else if (operator === "*" && expressions.length === 2) {
                firstExpression = new Multiply(...expressions);
            } else if (operator === "/" && expressions.length === 2) {
                firstExpression = new Divide(...expressions);
            } else if (operator === "arith-mean") {
                firstExpression = new ArithMean(...expressions);
            } else if (operator === "geom-mean") {
                firstExpression = new GeomMean(...expressions);
            } else if (operator === "harm-mean") {
                firstExpression = new HarmMean(...expressions);
            } else throw new WrongOperator("Error! It's wrong operation. Actual operator : '" + operator
                + "' . Actual number of arguments : " + expressions.length);
            skipWhiteSpaces();
            expected(")");
        } else {
            if (('0' <= validChar() && validChar() <= '9') || (validChar() === "-")) {
                firstExpression = parseConst();
            } else if (/\S/.test(validChar())) {
                firstExpression = parseVariety();
            }
        }
        return firstExpression;
    }

    function parseConst() {
        skipWhiteSpaces();
        let s = "";
        while (('0' <= validChar() && validChar() <= '9') || (validChar() === "-")) {
            s += validChar();
            nextChar();
        }
        return new Const(Number.parseInt(s));
    }

    
    function parseVariety() {
        skipWhiteSpaces();
        let s = "";
        while (pos < expression.length && /\S/.test(validChar()) && validChar() !== ")" && validChar() !== "(") {
            s += validChar();
            nextChar();
        }
        if (s !== "x" && s !== "y" && s !== "z") {
            throw new WrongVariety("Error! It's wrong variety. Expected : 'x' , 'y', 'z'. Actual : " + s);
        }
        return new Variable(s);
    }
}

