#ifndef DGRAPH_H_
#define DGRAPH_H_

#include "Arc.h"

using namespace std;

class DGraph {
    map<Node, list<Node> > alist;
public:
    DGraph();
    void add(Node);
    void add(Arc);
    void remove(Node);
    //void remove(Arc);
    string toString();

    virtual ~SimpleGraph();
};

#endif
