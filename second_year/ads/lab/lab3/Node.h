/*
 * Node.h
 *
 *  Created on: Sep 30, 2014
 *      Author: Mircea Marin
 */

#ifndef NODE_H_
#define NODE_H_

#include <set>
#include <string>

using std::string;

class Node {
	string id;
public:
	Node() { }
	Node(string s) : id(s) {}
	// string representation of a node
	string toString() const { return id; }
	// comparison operators
	friend bool operator==(const Node&, const Node&);
	friend bool operator<(const Node&, const Node&);
};

#endif /* NODE_H_ */
