package org.example.grade.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * 평균학점 계산 방법 -> (학점수*교과목 평점)의 합계/수강신청 총학점 수
 * 일급 컬렉션 사용
 */
class GradeCalculatorTest {
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(
            new Course("OOP", 3, "A+"),
            new Course("자료구조", 3, "A+"));

        GradeCalculator gradeCalculator = new GradeCalculator(courses);
        double gradeResult = gradeCalculator.calculatorGrade();

        Assertions.assertEquals(4.5, gradeResult);
    }
}