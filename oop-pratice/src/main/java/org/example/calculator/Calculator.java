package org.example.calculator;

import org.example.calculator.calculate.*;

import java.util.List;

public class Calculator {
    /**
     * enum 사용
     */
    //    public static int calculator(int operand1, String operator, int operand2) {
    //        return ArithmeticOperator.calculator(operand1, operator, operand2);
    //    }


    /**
     * interface 사용
     */
    private static final List<NewArithmeticOperator> arithmeticOperator = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());

    public static int calculator(int operand1, String operator, int operand2) {
        return arithmeticOperator.stream()
            .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
            .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}
