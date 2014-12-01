/*
 * SimpleGraph.h
 *
 *  Created on: Oct 1, 2014
 *      Author: Mircea Marin
 */

#ifndef SIMPLEGRAPH_H_
#define SIMPLEGRAPH_H_

#include "Edge.h"

using namespace std;

class SimpleGraph {
	set<Edge> edges;
	set<Node> nodes;
public:
	SimpleGraph();
	void add(Node);
	void add(Edge);
	void remove(Node);
	void remove(Edge);
	set<Node> getNeighbors(Node);
	string toString();

	virtual ~SimpleGraph();
};

#endif /* SIMPLEGRAPH_H_ */
