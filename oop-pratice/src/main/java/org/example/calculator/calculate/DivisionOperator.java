package org.example.calculator.calculate;

public class DivisionOperator implements NewArithmeticOperator{
    @Override
    public boolean supports(String operator) {
        return operator.equals("/");
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
//        if (operand2.toInt() == 0){
//            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
//        }  // => PositiveNumber를 사용하면 해당 validation을 하지 않아도 된다.
        return operand1.toInt() / operand2.toInt();
    }
}
