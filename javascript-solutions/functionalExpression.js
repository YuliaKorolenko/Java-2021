"use strict";

//+
let unaryExpression = f => {
    return (a) => (x,y,z) => {
        return f(a(x,y,z));
    }
}
let binaryExpression = f => {
    return (a, b) => (x,y,z) => {
        return f(a(x,y,z), b(x,y,z));
    }
}

let add = binaryExpression((a, b) => (a + b));

let multiply = binaryExpression((a, b) => (a * b));

let divide = binaryExpression((a, b) => (a / b));

let subtract = binaryExpression((a, b) => (a - b));

let cnst = (a) => (x,y,z) => {
    return a;
};

const two = cnst(2);
const one = cnst(1);

let negate = unaryExpression((a) => (-a));

let variable = (name) => (x, y, z) => {
    if (name === "x") {
        return x;
    } else if (name === "y") {
        return y;
    } else {
        return z;
    }
}


//let polinom = add(
//    subtract(
//        multiply(variable("x"), variable("x")),
//        multiply(cnst(2), variable("x"))
//    )
//    , cnst(1)
//)
//for (let i = 0; i <= 10; i++) {
//    println(polinom(i));
//}
