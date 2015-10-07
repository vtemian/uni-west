/*
 * RBNode.h
 *
 *  Created on: Nov 4, 2013
 *      Author: Vlad
 */

#ifndef RBNODE_H_
#define RBNODE_H_

#include <string>
#include <iostream>
#include "Student.h"

using namespace std;

struct RBNode {
    Student *student;       // key
    RBNode *p;     // pointer to parent
    RBNode *left;  // pointer to left child
    RBNode *right; // pointer to right child
    enum color { RED, BLACK };
    color col;

    RBNode(Student *student, RBNode *l = NULL, RBNode *r = NULL, RBNode *parent = NULL, color c = RBNode::BLACK) :
        student(student), p(parent), left(l), right(r), col(c) {}

    string toString() {
        ostringstream os;
        os << student->toString() << ((col == RBNode::RED) ? ":r" : ":b");
        return os.str();
    }

    ~RBNode() {
        delete left;
        delete right;
    }
};

bool operator==(const RBNode& lhs, const RBNode& rhs)
{
    return lhs.student->firstName == rhs.student->firstName;
}

struct RBTree {
    RBNode* root;

    void LeftRotate(RBNode* x) {
        // Set y
        RBNode* y = x->right;
        // Turn y's left subtree into x's right subtree
        x->right = y->left;
        if (y->left != NULL)
            y->left->p = x;
        // link x's parent to y
        y->p = x->p;
        if (x->p == NULL)
            root = y;
        else if (x == x->p->left)
            x->p->left = y;
        else
            x->p->right = y;
        // Put x on y's left
        y->left = x;
        x->p = y;
    }

    /**
     * RightRotate(y) also assumes that y is the right child of
     * an RBNode x
     */

    void RightRotate(RBNode* y) {
        // Set x
        RBNode* x = y->left;
        // Turn x's right subtree into y's left subtree
        y->left = x->right;
        if (x->right != NULL)
            x->right->p = y;
        // link y's parent to x
        x->p = y->p;
        if (y->p == NULL)
            root = x;
        else if (y == y->p->left)
            y->p->left = x;
        else
            y->p->right = x;
        // Put y on x's right
        x->right = y;
        y->p = x;
    }

    RBTree() { root = NULL; }
    ~RBTree() { delete root; }

    RBNode* createNode(Student* student) {
        return new RBNode(student);
    }

    bool isNil(RBNode* n) { return (n == NULL); }

    RBNode* search(RBNode* w, Student* student) {
        if (w == NULL || w->student == student)
            return w;
        return search( (student < w->student) ? w->left : w->right, student);
    }

    RBNode* minimum(RBNode* w) {
        RBNode* x = w;
        while (x->left != NULL)
            x = x->left;
        return x;
    }

    RBNode* maximum(RBNode* w) {
        RBNode* x = w;
        while (x->right != NULL)
            x = x->right;
        return x;
    }

    RBNode* successor(RBNode* w) {
        RBNode* x = w;
        if (x->right != NULL) return minimum(x->right);
        if (x->p == NULL) return NULL; // x has no successor
        RBNode* y = x->p;
        while (y != NULL && x == y->right) {
            x = y;
            y = y->p;
        }
        return y;
    }

    RBNode* predecessor(RBNode* w) {
        RBNode* x = w;
        if (x->left != NULL) return maximum(x->left);
        if (x->left == NULL || x->right == NULL) return NULL; // x has no predecessor
        RBNode* y = x->p;
        while (y != NULL && x == y->left) {
            x = y;
            y = y->p;
        }
        return y;
    }

    /*
     * insert like in a binary tree
     */
    void insertB(RBNode* z) {
        RBNode *y = NULL;
        RBNode *x = root;
        while (x != NULL) {
            y = x;
            x = (z->student < x->student) ? x->left : x->right;
        }
        z->p = y;
        if (y == NULL)
            root = z;
        else if (z->student < y->student)
            y->left = z;
        else
            y->right = z;
    }

    RBNode* del(RBNode* z) {
        RBNode *y = (z->left == NULL || z->right == NULL) ? z : successor(z);
        RBNode *x = (y->left != NULL) ? y->left : y->right;
        if (x != NULL) {
            x->p = y->p;
            y->p = NULL;
            root = x;
        } else if (y == y->p->left)
            y->p->left = x;
        else
            y->p->right = z;
        if (y != z) {
            z->student = y->student;
        }
        return y;
    }

    void insert(RBNode* x) {
        // insert n like in a binary tree
        insertB(x);
        // perform the proper changes
        x->col = RBNode::RED;
        RBNode *y;
        while (x != root && x->p->col == RBNode::RED) {
            if (x->p == x->p->p->left) {
                y = x->p->p->right;
                if ((y != NULL) && y->col == RBNode::RED) {
                    // Case 1
                    x->p->col = RBNode::BLACK;
                    y->col = RBNode::BLACK;
                    x->p->p->col = RBNode::RED;
                    x = x->p->p;
                } else {
                    if (x == x->p->right) {
                        // Case 2
                        x = x->p;
                        LeftRotate(x);
                    }
                    // Case 3
                    x->p->col = RBNode::BLACK;
                    x->p->p->col = RBNode::RED;
                    RightRotate(x->p->p);
                }
            } else {
                // same as the "then" clause with "left" and "right" exchanged
                y = x->p->p->left;
                if ((y != NULL) && y->col == RBNode::RED) {
                    // Case 1
                    x->p->col = RBNode::BLACK;
                    y->col = RBNode::BLACK;
                    x->p->p->col = RBNode::RED;
                    x = x->p->p;
                } else {
                    if (x == x->p->right) {
                        // Case 2
                        x = x->p;
                        LeftRotate(x);
                    }
                    // Case 3
                    x->p->col = RBNode::BLACK;
                    x->p->p->col = RBNode::RED;
                    LeftRotate(x->p->p);
                }
            }
        }
        root->col = RBNode::BLACK;
    }

    void inorder(RBNode* T) {
        if (T != NULL) {
            inorder(T->left);
            cout << T->toString() << ' ';
            inorder(T->right);
        }
    }

    void inorder() { inorder(root); }

    void display(RBNode* w, int indent) {
        if (w != NULL) {
            display(w->left, indent + 2);
            for (int i = 0; i < indent; i++) cout << ' ';
            cout << w->toString() << '\n';
            display(w->right, indent + 2);
        }
    }

    void indentedDisplay() {
        cout << "The RB tree is" <<endl;
        display(root, 2);
    }

    /**
     * Compute the black-height of this red-black tree
     */
    int bh() {
        return bh(root);
    }

    int bh(RBNode *node) {
        int left_height, right_height;
        if(node == NULL) return 0;
        left_height = bh(node->left);
        right_height = bh(node->right);
        if(left_height < 0 || right_height < 0 || left_height != right_height) return -1;
        if(node->col == RBNode::RED)
            return left_height;
        else
            return left_height + 1;
    }

    /**
     * Compute the depth of this red-black tree
     */
    int depth() {
        return depth(root);
    }
    int depth(RBNode *node){
        int left, right;

        if(node == NULL) return 0;
        left = depth(node->left);
        right = depth(node->right);

        return (left > right) ? left + 1: right + 1;
    }

    /**
     * Compute the max key of a black node in this red-black tree
     */
    int maxBlack() {
        return maxBlack(root);
    }
    int maxBlack(RBNode *node){
        int left_max=0, right_max=0, current=0;

        if(node == NULL) return 0;

        left_max = maxBlack(node->left);
        right_max = maxBlack(node->right);

        if(node->col == RBNode::BLACK)
            current = node->student->getMean();

        if(current >= left_max && current >= right_max)
            return current;
        return (left_max >= right_max) ? left_max : right_max;
    }
};


struct RBNil : public RBNode {
    RBNil() : RBNode(0, 0, 0, 0) { }

    string toString() {
        return "nil:b";
    }
};

#endif /* RBNODE_H_ */
