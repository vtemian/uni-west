#ifndef NODE_H
#define NODE_H

template <class T>
class Node {
    T data;
    Node* next;
    public:
        void setData(T data);
        void setNext(Node *node);
        void setNextNull();
        T getData() {
            return data;
        };
        Node* getNextNode() {
            return next;
        };
};

#include "node.tpp"

#endif
