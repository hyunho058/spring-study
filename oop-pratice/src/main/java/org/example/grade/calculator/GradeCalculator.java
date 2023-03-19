package org.example.grade.calculator;

import java.util.List;

public class GradeCalculator {

    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public double calculatorGrade() {
        double multiplyCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();
        int totalCompletedCredit = courses.calculateTotalCompletedCredit();

        return multiplyCreditAndCourseGrade / totalCompletedCredit;
    }
}
