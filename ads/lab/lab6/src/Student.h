/*
 * RBNode.h
 *
 *  Created on: Nov 4, 2013
 *      Author: Vlad Temian
 */

#ifndef STUDENT_H_
#define STUDENT_H_

#include <string>
#include <iostream>
#include <sstream>

using namespace std;

struct Student {
    string firstName;
    string lastName;
    float lpGrade;
    float osGrade;
    float gtcGrade;

    float getMean() {
        return (lpGrade + osGrade + gtcGrade) / 3;
    }

    string toString() {
        ostringstream os;
        os << firstName << " " << lastName;
        return os.str();
    }
};

bool operator<(const Student& lhs, const Student& rhs)
{
    float lhs_mean, rhs_mean;
    lhs_mean = (lhs.lpGrade + lhs.osGrade + lhs.gtcGrade) / 3;
    rhs_mean = (rhs.lpGrade + rhs.osGrade + rhs.gtcGrade) / 3;
    return lhs_mean < rhs_mean;
}

bool operator>(const Student& lhs, const Student& rhs)
{
    float lhs_mean, rhs_mean;
    lhs_mean = (lhs.lpGrade + lhs.osGrade + lhs.gtcGrade) / 3;
    rhs_mean = (rhs.lpGrade + rhs.osGrade + rhs.gtcGrade) / 3;
    return lhs_mean > rhs_mean;
}

bool operator==(const Student& lhs, const Student& rhs)
{
    return (lhs.firstName == rhs.firstName && lhs.lastName == rhs.lastName);
}

#endif /* STUDENT_H_ */
