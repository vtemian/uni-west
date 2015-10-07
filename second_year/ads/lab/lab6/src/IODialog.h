/*
 * IODialog.h
 *
 *  Created on: Nov 6, 2012
 *      Author: mincea
 */

#ifndef IODIALOG_H_
#define IODIALOG_H_

#include <iostream>
#include <sstream>
#include <list>

using namespace std;

namespace IODialog {


void showOperations() {
    cout << " 1. Add student" << endl
         << " 2. Delete student" << endl
         << " 3. Get student with  minimum grade " << endl
         << " 4. Get student with greatest grade" << endl
         << " 5. Get successor of a student" << endl
         << " 6. Get predecessor of a student" << endl
         << " 7. Show tree" << endl
         << " 8. Show inorder traversal" << endl
         << " 9. Show black-height of the tree" << endl
         << "10. Show the maximum key of a black node of the tree (TODO)" << endl
         << "11. Show tree depth (TODO)" << endl
         << "12. Exit" << endl
         << "Enter your choice (1-12): ";
}

int getOperations() {
    int option = 0;
    while (true) {
        showOperations();
        if (!(cin >> option)) {
            cout << "Unknown option. Try again." << endl;
            cin.clear();
            cin.ignore(10000, '\n');
        } else if (option < 1 || option > 12) {
            cout << "Unknown option. Try again." << endl;
        } else {
            cout << "Proceed with selection " << option << " .." << endl;
            cin.ignore(10000, '\n');
            break;
        }
    }
    return option;
}


bool wantToAddNewStudent() {
    string response;

    cout<<"Do you want to add a new student? [Y/n] :";
    cin>>response;

    return response == "Y" || response == "y";
}

Student* addNewStudent() {
    Student* student = new Student();

    cout<<"First name: "; cin>>student->firstName;
    cout<<"Last name: "; cin>>student->lastName;
    cout<<"LP grade: "; cin>>student->lpGrade;
    cout<<"OS grade: "; cin>>student->osGrade;
    cout<<"GCT grade: "; cin>>student->gtcGrade;

    return student;
}

void getStudents(list<Student*>& lStudent) {
    while(wantToAddNewStudent()){
        lStudent.push_back(addNewStudent());
    }
}

Student* getNodeKey() {
    Student* student;
    cout << "Type in the first name of the student: ";
    cin >> student->firstName;
    cout << "Type in the last name of the student: ";
    cin >> student->lastName;
    return student;
}
}

#endif /* IODIALOG_H_ */
