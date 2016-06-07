#include "node.h"

template <class T>
void Node<T>::setData(T data) {
    this->data = data;
}

template <class T>
void Node<T>::setNext(Node *node) {
    this->next = node;
}

template <class T>
void Node<T>::setNextNull() {
    this->next = NULL;
}
