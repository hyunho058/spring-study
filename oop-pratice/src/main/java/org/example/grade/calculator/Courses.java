package org.example.grade.calculator;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
//        double multipleiedCreditAndCourseGrade = 0;
//        for (Course course : courses) {
//            multipleiedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();
//        }
//
//        return multipleiedCreditAndCourseGrade;

        return courses.stream()
            .mapToDouble(Course::multiplyCreditAndCourseGrade)
            .sum();
    }

    public int calculateTotalCompletedCredit() {
        //수강신청 총학점 수
        return courses.stream()
            .mapToInt(Course::getCredit)
            .sum();
    }
}
