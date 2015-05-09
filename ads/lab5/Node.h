struct Node {
    int key;
    Node *sibling;

    Node* reverseList(Node *ptr)
    {
        Node* current = ptr;
        Node* next = ptr;

        while(next != NULL) {
            ptr = next;
            current->sibling->sibling = current;
            next = next->sibling;
            current = next;
            std::cout<<next->key;
        }
        return ptr;
    }

    void toString(){
        Node* node = sibling;
        std::cout<<key<<"\n";

        while(node != NULL)
        {
            std::cout<<node->key<<"\n";
            node = node->sibling;
        }
    }
};