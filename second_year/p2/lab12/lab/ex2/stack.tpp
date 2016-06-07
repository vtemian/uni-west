#include <iostream>
#include <stdexcept>

#include "stack.h"

template <typename T>
void Stack<T>::push (T element) {
    if (full()) {
        throw std::length_error("The stack is full");
    }

    top++;
    t[top] = element;
}

template <typename T>
T Stack<T>::pop () {
    if (empty()) {
        throw std::length_error("The stack is empty");
    }

    return t[top--];
}


template <typename T>
void Stack<T>::list() {
    int i;

    for (i=0; i <= top; i++) {
        std::cout << this->t[i];
    }
}

template <typename T>
bool Stack<T>::empty() const {
    return top == -1;
}

template <typename T>
bool Stack<T>::full() {
    return top >= this->length - 1;
}
