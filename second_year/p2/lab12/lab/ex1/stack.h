#ifndef STACK_H
#define STACK_H

template <typename T>
class Stack {
    int top, length;
    T *t;
    public:
        Stack() {
            top = -1;
            length = 100;
            t = new T[length];
        };
        Stack(int length) {
            top = -1;
            this->length = length;
            t = new T[length];
        };
        void add(T element);
        void list();
        bool empty() const;
        bool full();
};

#include "stack.tpp"
#endif
