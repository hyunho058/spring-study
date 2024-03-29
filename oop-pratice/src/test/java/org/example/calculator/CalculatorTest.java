package org.example.calculator;

import org.example.calculator.calculate.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * 간단한 사칙연산을 할 수 있다.
 * 양수로만 계산할 수 있다.
 * 나눗셈에서 0을 나누는 경우 IllegalArgument exception 발생
 * MVC패턴 기반으로 구현한다.
 */
public class CalculatorTest {
    @DisplayName("덧셈 연산을 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void additionTest(int operand1, String operator, int operand2, int result) {
        int calculatorResult = Calculator.calculator(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        assertEquals(result, calculatorResult);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
            arguments(1, "+", 2, 3),
            arguments(1, "-", 2, -1),
            arguments(4, "*", 2, 8),
            arguments(4, "/", 2, 2)
        );
    }

//    @Test
//    void calculatorExceptionTest() {
//        assertThatCode(() -> Calculator.calculator(new PositiveNumber(10), "/", new PositiveNumber(0)))
//            .isInstanceOf(IllegalArgumentException.class)
//            .hasMessage("0으로 나눌 수 없습니다.");
//    }
}
