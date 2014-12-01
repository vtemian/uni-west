/*
 * IODialog.cpp
 *
 *  Created on: Oct 1, 2014
 *      Author: Mircea Marin
 */

#include "IODialog.h"

Node IODialog::getNode() {
	string id;
	cout << "Enter node id: ";
	cin >> id;
	// create and return the corresponding node
	return Node(id);
}

Edge IODialog::getEdge() {
	string id;
	cout << "Enter id of first node: ";
	cin >> id;
	// create  the node for first endpoint
	Node n1(id);
	cout << "Enter id of second node: ";
	cin >> id;
	// create the node for second endpoint
	Node n2(id);
	return Edge(n1, n2);
}

int IODialog::getGraphOperation() {
	int option = 0;
	while(true) {
		cout << graphMenu << endl << "Enter your choice (1-" << 7 << "): ";
		if (!(cin >> option)) {
			cout << "Unknown option. Try again." << endl;
			cin.clear();
			cin.ignore(10000, '\n');
		} else if (option < 1 || option > 7) {
			cout << "Unknown option. Try again." << endl;
		} else {
			cout << "Proceed with selection " << option << " .." << endl;
			cin.ignore(10000, '\n');
			break;
		}
	}
	return option;
}

void IODialog::getNodeNames(list<string>& lst) {
	cout << "Type in the keys of the nodes: " << flush;
	string key;
	string line;
	if (getline(cin,line)) {
		istringstream iss(line);
		while (iss >> key) {
			lst.push_back(key);
		}
	}
}

string IODialog::nodeInfo(set<Node> lst) {
	ostringstream os;
	os << "\nNodes: ";
	for (set<Node>::const_iterator i = lst.begin(); i != lst.end(); i++)
		os << i->toString() << ' ';
	return os.str();
}

string IODialog::edgeInfo(set<Edge> lst) {
	ostringstream os;
	os << "\nEdges: ";
	for (set<Edge>::const_iterator i = lst.begin(); i != lst.end(); i++)
		os << i->toString() << ' ';
	os << '\n';
	return os.str();

}
