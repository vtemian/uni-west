#include <fstream>
#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <string>
#include <list>
#include <sstream>

#include "student.h"

using namespace std;

Student StudentNull;

bool compareByName(Student firstStudent, Student secondStudent) {
    string firstName = firstStudent.getName();
    string secondName = secondStudent.getName();
    unsigned int i=0;

    while ( (i < firstName.length()) && (i < secondName.length()) ){
        if (tolower(firstName[i]) < tolower(secondName[i])) return true;
        else if (tolower(firstName[i]) > tolower(secondName[i])) return false;
        ++i;
    }

    return firstName.length() < secondName.length();
}

list<Student> readFromFile(string fileName) {
    list<Student> students;
    double practicalExamGrade, examGrade, numberOfAbsences;

    ifstream is(fileName.c_str());

    istream_iterator <string> isi(is);
    istream_iterator <string> eos;

    vector <string> v(isi, eos);

    for(vector<string>::iterator it=v.begin(); it != v.end(); it++) {
        string buf, name;
        stringstream ss(*it);

        ss >> name;

        ss >> buf;
        practicalExamGrade = strtod(buf.c_str(), NULL);

        ss >> buf;
        examGrade = strtod(buf.c_str(), NULL);

        ss >> buf;
        numberOfAbsences = atoi(buf.c_str());

        students.push_back(Student(name, practicalExamGrade, examGrade, numberOfAbsences));
    }

    return students;
}

void saveStudentsToFile(list<Student> students, string fileName) {
    ofstream os(fileName.c_str());

    for(list<students>::iterator it=students.begin(); it != students.end(); it++) {
        os << it->getName() << endl;
    }
}

list<Student> sortStudentsByName(list<Student> students) {
    students.sort(compareByName);
    return students;
}

Student searchByName(list<Student> students, string searchFor) {
    for(list<Student>::iterator it=students.begin(); it != students.end(); it++) {
        if (it->getName() == searchFor)
            return *it;
    }

    return StudentNull;
}

void displayStudentsWithGradeBetween(list<Student> students, double firstGrade, double secondGrade) {
    for(list<Student>::iterator it=students.begin(); it != students.end(); it++) {
        double studentGrade = it->getGrade();
        if (studentGrade >= firstGrade && studentGrade <= secondGrade) {
            cout << it->toString();
        }
    }
}

void displayStudentsWithLessPresences(list<Student> students, int presenceNumber) {
    for(list<Student>::iterator it=students.begin(); it != students.end(); it++) {
        if (it->getNumberOfAbsences() <= presenceNumber) {
            cout << it->toString();
        }
    }
}

void showMenu() {
    int choice, x, y;
    string fileName, searchFor;
    Student student;
    list<Student> students;

    while (1) {
        cout << "Execute one of the following:" << endl;
        cout << "\t1. Read students from a file." << endl;
        cout << "\t2. Save students into a file." << endl;
        cout << "\t3. Sort students by names." << endl;
        cout << "\t4. Search by name for a student in the list." << endl;
        cout << "\t5. Display all students which obtained a grade between x and y." << endl;
        cout << "\t6. Display all students which have less than 12 presences." << endl;

        cin >> choice;

        switch(choice) {
            case 1:
                cout << "The name of the file which is read: "; cin >> fileName;
                students = readFromFile(fileName);
                break;

            case 2:
                cout << "The name of the file which is written: "; cin >> fileName;
                saveStudentsToFile(students, fileName);
                break;

            case 3:
                students = sortStudentsByName(students);
                break;

            case 4:
                cout << "The name of the student which needs to be found: "; cin >> searchFor;
                student = searchByName(students, searchFor);
                if (&student != &StudentNull) {
                    cout << "The student was found in list";
                } else {
                    cout << "The student was not found in list";
                }
                break;

            case 5:
                cout << "x = "; cin >> x;
                cout << "y = "; cin >> y;
                displayStudentsWithGradeBetween(students, x, y);
                break;

            case 6:
                displayStudentsWithLessPresences(students, 12);
                break;
        }
    }
}

int main() {
    showMenu();
    return 0;
}
