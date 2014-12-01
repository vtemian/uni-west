#ifndef ARC_H_
#define ARC_H_

#include <string>

#include "Node.h"

class Arc {
    Node u;
    Node v;
    float weight;
public:
    Arc() { }
    Arc(Node x, Node y) : u(x), v(y) { }
    Node getFirst() const { return u; }
    Node getSecond() const { return v; }
    float getWeight() const { return weight; }
    string toString() const {
        return u.toString() + "->" + v.toString();
    }
    friend bool operator==(const Arc&, const Arc&);
};

#endif
