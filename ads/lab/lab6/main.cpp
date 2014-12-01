
/*
 * Main.cpp
 *
 *  Created on: Nov 4, 2013
 *      Author: Vlad
 */

#include <cstdlib>
#include "src/RBNode.h"
#include "src/IODialog.h"
#include "src/Student.h"

void startProcessing(RBTree*);

int main() {
    RBTree* RB = new RBTree();
    startProcessing(RB);
    delete RB;
}

void perform(RBTree*, int);

void startProcessing(RBTree* RB) {
    int o;
    while (true) {
        o = IODialog::getOperations();
        perform(RB, o);
    }
}

void perform(RBTree* RB, int o) {
    list<Student*> students;
    RBNode* rbn;
    Student* student;

    switch (o) {
    case 1: // Add students
        IODialog::getStudents(students);
        for (list<Student*>::iterator it = students.begin();
                it != students.end(); it++)
            RB->insert(RB->createNode(*it));
        break;
    case 2: // Delete student
        student = IODialog::getNodeKey();
        rbn = RB->search(RB->root, student);
        if (!RB->isNil(rbn))
            RB->del(rbn);
        else
            cout << "RB: Student not found" << endl;
        break;
    case 3: // Get student with minimum grade
        rbn = RB->minimum(RB->root);
        if (RB->isNil(rbn) ) cout << "RB: Minimum not found" << endl;
        else cout << "RB: Minimum is: " << rbn->toString() << endl;
        break;
    case 4: // Get maximum node
        rbn = RB->maximum(RB->root); // RB->maximum(RBNode*) must be implemented!
        if (RB->isNil(rbn) ) cout << "RB: Maximum not found" << endl;
        else cout << "RB: Maximum is: " << rbn->toString() << endl;
        break;
    case 5: // Get successor of specified node
        student = IODialog::getNodeKey();
        rbn = RB->search(RB->root, student);
        cout<<rbn;
        if (!RB->isNil(rbn)) {
            cout<<"not nill";
            rbn = RB->successor(rbn);
            if (RB->isNil(rbn))
                cout << "RB: Node has no successor.\n";
            else cout << "RB: Successor is: " << rbn->toString() << '\n';
        } else cout << "RB: Node not found.\n";
        break;
    case 6: // Get predecessor of specified node
        student = IODialog::getNodeKey();
        rbn = RB->search(RB->root, student);
        if (!RB->isNil(rbn)) {
            rbn = RB->predecessor(rbn); // RB->predecessor(RBNode*) must be implemented!
            if (RB->isNil(rbn)) cout << "RB: Node has no predecessor.\n";
            else cout << "RB: Predecessor is: " << rbn->toString() << '\n';
        }
        else cout << "RB: Node not found.\n";
        break;
    case 7: // Show tree RB
        RB->indentedDisplay();
        break;
    case 8:
        cout << "\nInorder traversal of RB tree yields" << endl << ' ';
        RB->inorder();
        cout << endl;
        break;
    case 9: // show black-height of RB
        cout << "The black-height of the RB tree is " << RB->bh() << endl;
        break;
    case 10: // show the maximum key of a black node of RB
        cout << "The max key of a black node in the RB tree is " << RB->maxBlack() << endl;
        break;
    case 11: // Compute and show tree depth of RB
        cout << "The tree depth of RB is " << RB->depth() << endl;
        break;
    case 12: // Exit
        delete RB;
        cout << "Bye!" << endl;
        exit (0);
    }
}
