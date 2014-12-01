/*
 * IODialog.h
 *
 *  Created on: Sep 30, 2014
 *      Author: Mircea Marin
 *
 *  This header file contains the implementation of the basic I/O operations
 *  of the application.
 *
 */

#ifndef IODIALOG_H_
#define IODIALOG_H_

#include "Edge.h"
#include "Node.h"
#include <iostream>
#include <sstream>
#include <set>
#include <list>

using namespace std;

namespace IODialog {
const string graphMenu =
		string("Select the operation to perform:\n") +
				" 1. Add node\n" +
				" 2. Add edge\n" +
				" 3. Remove node\n" +
				" 4. Remove edge\n" +
				" 5. Show the neighbors of a node\n" +
				" 6. Show graph content\n" +
				" 7. Exit\n";

/**
 * getNode() asks the user to provide a node id (a string)
 * and returns a node with that node id
 */
Node getNode();

/**
 * getEdge() asks the user to provide ids for two nodes, and returns
 * an edge whose endpoints are nodes with the ids indicated by the user
 */
Edge getEdge();

/**
 * getGraphOperation() asks the user to choose a number for a choice from the
 * menu
 *   1. Add node
 *   2. Add edge
 *   3. Remove node
 *   4. Remove edge
 *   5. Show the neighbors of a node
 *   6. Show graph content
 *   7. Exit
 * and returns the number chosen by the user.
 *
 * Note: the while loop forces the user to choose a number between 1 and 7.
 *
 */
int getGraphOperation();

/**
 * getNodeNames(list<string>& lst) works as follows:
 * 1) It takes as input argument a reference to a list of strings,
 * initially empty
 * 2) It asks the user to type in a line of strings,
 *    separated by whitespace (e.g., blank)
 * 3) It adds the strings from the input line into the list
 *    referred by the input argument
 *
 * Note that this function returns by reference the computed result.
 */
void getNodeNames(list<string>&);

string nodeInfo(set<Node>);

string edgeInfo(set<Edge>);

}

#endif /* IODIALOG_H_ */
