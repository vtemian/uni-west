/*
 * Edge.h
 *
 *  Created on: Sep 30, 2014
 *      Author: Mircea Marin
 */

#ifndef EDGE_H_
#define EDGE_H_

#include <string>

#include "Node.h"

class Edge {
	Node u;
	Node v;
public:
	Edge() { }
	Edge(Node x, Node y) : u(x), v(y) { }
	Node getFirst() const { return u; }
	Node getSecond() const { return v; }
	string toString() const {
		return (u < v) ? u.toString() + "-" + v.toString()
					: v.toString() + "-" + u.toString();
	}
	friend bool operator==(const Edge&, const Edge&);
	friend bool operator<(const Edge&, const Edge&);
};



#endif /* EDGE_H_ */
