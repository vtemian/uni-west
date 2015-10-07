/*
 * Main.cpp
 */

#include "BTree.h"
#include <iostream>
#include <sstream>
#include <list>

using namespace std;

int getTreeOperation();
void perform(BTree*, int);
void getNodeKeys(list<int>&);

int main() {
	cout << "This application creates and operates on a B-Tree" << endl
			<< "Indicate the desired branching factor t: ";
	int t;
	cin >> t;
	BTree* B = new BTree(t);
	int o = 1;
	while (o != 9) {
		o = getTreeOperation();
		perform(B, o);
	}
	delete B;
}

void showTreeOperation() {
	cout << " 1. Add nodes\t\t 2. Delete node" << endl
		 << " 3. Get minimum node\t 4. Get maximum node" << endl
		 << " 5. Get predecessor\t 6. Get successor" << endl
		 << " 7. Show tree\t\t 8. Show tree content (inorder traversal)" << endl
		 << " 9. Exit" << endl
		 << "Enter your choice (1-9): ";
}

int getTreeOperation() {
	int option = 0;
	while (true) {
		showTreeOperation();
		if (!(cin >> option)) {
			cout << "Unknown option. Try again." << endl;
			cin.clear();
			cin.ignore(10000, '\n');
		} else if (option < 1 || option > 9) {
			cout << "Unknown option. Try again." << endl;
		} else {
			cout << "Proceed with selection " << option << " .." << endl;
			cin.ignore(10000, '\n');
			break;
		}
	}
	return option;
}

void perform(BTree* B, int o) {
		list<int> nodeKeys;
		int k;
		switch (o) {
		case 1: /* Add nodes -- DONE */
			getNodeKeys(nodeKeys);
			for (list<int>::iterator it = nodeKeys.begin();
					it !=nodeKeys.end(); it++)
				B->insert(*it);
			break;
		case 2: /* delete node */
			/* TODO */
			break;
		case 3: /* get minimum key */
			cout << "Mininum is " << B->root->min();
			break;
		case 4: /* get maximum key */
			cout << "Maximum is " << B->root->max();
			break;
		case 5: /* find predecessor */
			cout << "Get key of node for which to find predecessor: ";
			cin >> k;
			B->root->searchPredecessor(k);
			break;
		case 6: /* find successor */
			cout << "Get key of node for which to find successor: ";
			cin >> k;
			B->root->searchSuccessor(k);
			break;
		case 8:
			B->inorderDisplay(B->root);
			cout << endl;
			break;
		case 7:
			B->indentedDisplay();
			break;
		case 9:
			cout << "Exit" << endl;
			break;
		}
	}

void getNodeKeys(list<int>& nodeKeys) {
	cout << "Type in the keys of the nodes: " << flush;
	int key;
	string line;
	if (getline(cin,line)) {
		istringstream iss(line);
		while (iss >> key) {
			nodeKeys.push_back(key);
		}
	}
}
