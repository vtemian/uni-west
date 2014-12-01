/*
 * DGraph.cpp
 *
 *  Created on: Oct 1, 2014
 *      Author: Mircea Marin
 */

#include "DGraph.h"
#include "IODialog.h"

using namespace std;

typedef map<Node, list<Node> >::const_iterator MI;

DGraph::DGraph() { } // default constructor

DGraph::~DGraph() { } // default destructor

/**
 * add(n) adds node n to the graph.
 */
void DGraph::add(Node n) {
    list<Node> empty;
    if( alist.find(n) == alist.end() ) {
        alist[n] = empty;
    }
}

/**
 * add(e) adds arc a to the graph.
 *
 */
void DGraph::add(Arc a) {
    alist[a.getFirst()].push_back(a.getSecond());
}

/**
 * remove(n) removes node n from the graph
 */
void DGraph::remove(Node n) {
    alist.erase(alist.find(n));

    for(MI i = alist.begin(); i != alist.end(); i++)
        i->second.erase(n);

}
/**
 * toString() returns a string that indicates the structure of this graph
 */
string DGraph::toString() {
    //return IODialog::nodeInfo(nodes) + IODialog::edgeInfo(edges);
}
