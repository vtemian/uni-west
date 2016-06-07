#include "node.h"
#include "list.h"

template <class T>
void List<T>::append (T data) {
    if (head == NULL) {
        head = new Node<T>;
        head->setData(data);
        tail = head;
    } else {
        Node<T>* node = new Node<T>;
        node->setNextNull();
        node->setData(data);
        tail->setNext(node);
        tail = node;
    }
}

template <class T>
typename List<T>::Iterator List<T>::begin () const {
    List<T>::Iterator iterator;
    iterator.setNode(head);
    return iterator;
}

template <class T>
typename List<T>::Iterator List<T>::end () const {
    List<T>::Iterator iterator;
    iterator.setNode(tail);
    return iterator;
}

template <class T>
int List<T>::Iterator::operator ==(Iterator& x) const {
    return currentNode->getData() == x.getNode()->getData();
}

template <class T>
int List<T>::Iterator::operator !=(Iterator& x) const {
    T data = x.getNode()->getData();
    Node<T> nodeB = *currentNode;
    return nodeB.getData() != data;
}

template <class T>
T List<T>::Iterator::operator *() const {
    Node<T> nodeB = *currentNode;
    return nodeB.getData();
}

template <class T>
typename List<T>::Iterator& List<T>::Iterator::operator ++(int) {
    currentNode = currentNode->getNextNode();
    return *this;
}
