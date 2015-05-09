#include <iostream>

#include "Node.h"

using namespace std;

int main() {
    int nr;
    Node* node = new Node;
    Node* head = new Node;

    head = node;

    cout<<"Introduce a number of nodes: ";
    cin>>nr;

    while(nr) {
        cout<<"Give a key: ";
        cin>>node->key;

        node->sibling = new Node;
        node = node->sibling;
        nr--;
    }

    head->toString();
    head->reverseList(head)->toString();
    return 0;
}