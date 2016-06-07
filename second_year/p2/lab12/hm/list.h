#ifndef LIST_H
#define LIST_H

#include "node.h"

template <typename T> class List {
    Node<T> *head, *tail;
    public:
        void append (T data);
        void remove();
        List() {
            head = NULL;
            tail = NULL;
        };
        class Iterator {
            Node<T> *currentNode;
            public:
                Iterator() {
                    currentNode = NULL;
                };
                Iterator(Node<T> *node) {
                    currentNode = node;
                };
                void setNode(Node<T> *node) {
                    currentNode = node;
                };
                Node<T>* getNode() {
                    return currentNode;
                }
                int operator == (Iterator& x) const;
                int operator != (Iterator& x) const;
                T operator *() const;
                Iterator& operator ++(int);
        };
        Iterator begin() const;
        Iterator end() const;
};

#include "list.tpp"

#endif
