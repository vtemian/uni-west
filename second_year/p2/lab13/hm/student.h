#include <string>


class Student {
    std::string name;
    double practicalExamGrade;
    double examGrade;
    int numberOfAbsences;
    public:
        explicit Student(std::string name="", double practialExamGrade=0.0, double examGrade=0.0, int numberOfAbsences=0);
        double getPracticalExamGrade();
        double getExamGrade();
        double getGrade();
        int getNumberOfAbsences();
        std::string getName();
        std::string toString();
};
extern Student StudentNull;
