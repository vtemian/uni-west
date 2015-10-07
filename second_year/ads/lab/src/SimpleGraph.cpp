/*
 * SimpleGraph.cpp
 *
 *  Created on: Oct 1, 2014
 *      Author: Mircea Marin
 */

#include "SimpleGraph.h"
#include "IODialog.h"

typedef set<Edge>::const_iterator EI;

SimpleGraph::SimpleGraph() { } // default constructor

SimpleGraph::~SimpleGraph() { } // default destructor

/**
 * add(n) adds node n to the graph.
 */
void SimpleGraph::add(Node n) {nodes.insert(n); }

/**
 * add(e) adds edge e to the graph.
 *
 * Note: adding an edge to a graph also adds its endpoints to the set of
 * nodes of that graph.
 */
void SimpleGraph::add(Edge e) {
	nodes.insert(e.getFirst());
	nodes.insert(e.getSecond());
	edges.insert(e);
}

/**
 * remove(n) removes node n from the graph
 */
void SimpleGraph::remove(Node n) {
	nodes.erase(n);
	for(EI i = edges.begin(); i != edges.end(); i++)
		if (i->getFirst() == n || i->getSecond() == n)
			edges.erase(i);

}

/**
 * remove(e) removes edge e from the graph
 */
void SimpleGraph::remove(Edge e) {
	edges.erase(e);
}

set<Node> SimpleGraph::getNeighbors(Node n) {
	set<Node> result;
    for (EI i = edges.begin(); i != edges.end(); i++) {
    	// check if the edge referred by i has an endpoint n
    	if (i->getFirst() == n)
    		result.insert(i->getSecond());
    	else if (i->getSecond() == n)
    		result.insert(i->getFirst());
    }
    return result;
}

/**
 * toString() returns a string that indicates the structure of this graph
 */
string SimpleGraph::toString() {
	return IODialog::nodeInfo(nodes) + IODialog::edgeInfo(edges);
}
