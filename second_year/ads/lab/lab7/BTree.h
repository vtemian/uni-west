/*
 * BTree.h
 */

#ifndef BTREE_H_
#define BTREE_H_

#include <iostream>

using namespace std;

struct Node {
	typedef Node* NodePtr;
	int n;
	int* key;
	NodePtr* c;
	bool leaf;

	Node(const int t) {
		key = new int[2*t -1];
		c = new NodePtr[2*t];
		leaf = true;
		n = 0;
	}

	~Node() {
		if (!leaf)
			for (int i = 0; i < n; i++)
				delete c[i];
		delete[] c;
	}

	/**
	 * find the min key in this node
	 */
	int min() { return leaf ? key[0] : c[0]->min(); }

	/**
	 * find the max key in this node
	 */
	int max() { return leaf ? key[n-1] : c[n]->max(); }

	/**
	 * find the successor of key k in this node
	*/
	void searchSuccessor(int k) {
        int i=0, ;
        while(i < n) {
            if(key[i] == k){
                found = 1;
                break;
            }
            i++;
        }
	}

	void searchPredecessor(int k) {
		/* TODO */
	}
};

class BTree {
	int t; // the branching factor
public:
	Node* root;
	// constructor: will be used instead of create()
	BTree(int);
	~BTree();
	Node* allocateNode();
	Node* search(Node* x, int key);
	void insert(int key);
	void insertNonfull(Node* x, int k);
	void del(int key);
	void showTree();
	void diskRead(Node*);
	void diskWrite(Node*);
	void splitChild(Node*, int, Node*);
	void display(Node*, int);
	void inorderDisplay(Node*);
	void indentedDisplay();
};

#endif /* BTREE_H_ */
