//============================================================================
// Name        : GraphADT.cpp
// Author      : Mircea Marin
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "IODialog.h"
#include "SimpleGraph.h"

using namespace std;

#include <map>

typedef set<Edge>::const_iterator EI;
typedef list<string>::const_iterator SI;

int main() {
	SimpleGraph G;

	// read the whitespace-separated identifiers of the
	// initial nodes of the graph
	list<string> nodeNames;
	IODialog::getNodeNames(nodeNames);

	for (SI ci = nodeNames.begin(); ci != nodeNames.end(); ci++) {
		Node n(*ci);
		G.add(n);
	}

	int option = 0;

	while(true) {
		// Select the operation to perform:
		option = IODialog::getGraphOperation();
		switch (option) {
		case 1: // 1. Add node
			G.add(IODialog::getNode());
			break;
		case 2: // 2. Add edge
			G.add(IODialog::getEdge());
			break;
		case 3: // 3. Remove a node
			G.remove(IODialog::getNode());
			break;
		case 4: // 4. Remove an edge
			G.remove(IODialog::getEdge());
			break;
		case 5: // 5. Show the neighbors of a node
	  	    {
			Node n = IODialog::getNode();
			set<Node> neighbors = G.getNeighbors(n);
			cout << IODialog::nodeInfo(neighbors) << endl;
		    }
			break;
		case 6: // 6. Show graph content
			cout << G.toString();
			break;
		case 7: // 7. Exit
			break;
		}
		if(option == 7) {
			cout << "Bye!" << endl;
			break;
		}
	}
}

