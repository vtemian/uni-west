#include <iostream>
#include <string>

#include "student.h"

using namespace std;

Student::Student(string name, double practicalExamGrade, double examGrade, int numberOfAbsences) {
    this->name = name;
    this->practicalExamGrade = practicalExamGrade;
    this->examGrade = examGrade;
    this->numberOfAbsences = numberOfAbsences;
}

double Student::getPracticalExamGrade() {
    return this->practicalExamGrade;
}

double Student::getExamGrade() {
    return this->examGrade;
}

double Student::getGrade() {
    return (this->examGrade + this->practicalExamGrade) / 2;
}


int Student::getNumberOfAbsences() {
    return this->numberOfAbsences;
}

string Student::getName() {
    return this->name;
}

string Student::toString() {
    return this->name;
}
