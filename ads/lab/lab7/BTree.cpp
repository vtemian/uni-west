/*
 * BTree.cpp
 */

#include "BTree.h"
#include <iostream>

using namespace std;

Node* BTree::allocateNode() {
	return new Node(t);
}

BTree::BTree(int i) : t(i) {
	Node* x = allocateNode();
	diskWrite(x);
	root = x;
}

BTree::~BTree() { delete root; }

/**
 * Returns the descendant of node x (or x itself) which contains key k
 */
Node* BTree::search(Node* x, int k) {
	int i = 0;
	while (i < x->n && k > (x->key)[i])
		i++;
	if (i < x->n && k == (x->key)[i])
		return x;
	if (x->leaf)
		return 0;
	else {
		diskRead((x->c)[i]);
		return search((x->c)[i], k);
	}
}

void BTree::splitChild(Node* x, int i, Node* y) {
	Node* z = allocateNode();
	z->leaf = y->leaf;
	z->n = t-1;
	for (int j = 0; j <= t-2; j++)
		(z->key)[j] = (y->key)[j+t];
	if (!(y->leaf))
		for (int j = 0; j <= t-1; j++)
			(z->c)[j] = (y->c)[j+t];
	y->n = t-1;
	for (int j = x->n; j >= i+1; j--) {
		(x->c)[j+1] = (x->c)[j];
		(x->key)[j] = (x->key)[j-1];
	}
	(x->c)[i+1] = z;
	(x->key)[i] = (y->key)[t-1];
	x->n = 1 + x->n;
	diskWrite(y); diskWrite(z); diskWrite(x);
}

void BTree::insertNonfull(Node* x, int k) {
	int i = x->n - 1;
	if (x->leaf) {
		while (i >= 0 && k < (x->key)[i]) {
			(x->key)[i+1] = (x->key)[i];
			i--;
		}
		(x->key)[i+1] = k;
		x->n = x->n + 1;
		diskWrite(x);
	} else {
		while(i >= 0 && k < (x->key)[i]) i --;
		i++;
		diskRead((x->c)[i]);
		if ((x->c)[i]->n == 2*t-1) {
			splitChild(x, i, (x->c)[i]);
			if (k > (x->key)[i]) i++;
		}
		insertNonfull((x->c)[i], k);
	}
}

void BTree::insert(int k) {
	Node* r = root;
	if (r->n == 2*t-1) {
		Node* s = allocateNode();
		root = s;
		s->leaf = false;
		s->n = 0;
		(s->c)[0] = r;
		splitChild(s, 0, r);
		insertNonfull(s, k);
	}
	else insertNonfull(r, k);
}

void BTree::diskRead(Node* x) { }
void BTree::diskWrite(Node* x) { }

void BTree::display(Node* x, int indent) {
	if (x == 0)
		return;
	bool b = x->leaf;
	for (int i = 0; i < x->n; i++) {
		if (!b) display((x->c)[i], indent + 2);
		for (int j = 0; j < indent; j++) cout << ' ';
		cout << (x->key)[i] << '\n';
	}
	if (!b) display((x->c)[x->n], indent + 2);
}

void BTree::inorderDisplay(Node* x) {
	bool b = x->leaf;
	for (int i = 0; i < x->n; i++) {
		if (!b) inorderDisplay((x->c)[i]);
		cout << (x->key)[i] << ' ';
	}
	if (!b) inorderDisplay((x->c)[x->n]);
}

void BTree::indentedDisplay() {
	cout << "The B-tree is" <<endl;
	display(root, 0);
}

