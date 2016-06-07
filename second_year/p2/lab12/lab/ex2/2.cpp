#include <iostream>
#include "stack.h"

using namespace std;

int main() {
    Stack <int> anIntegerStack;

    anIntegerStack.push(5);
    anIntegerStack.push(7);

    if(anIntegerStack.empty())
        cout << "Empty stack" << endl;
    else
        cout << anIntegerStack.pop() << endl;

    Stack<string> route;

    route.push("Timisoara");
    route.push("Lugoj");
    route.push("Deva");

    while(!route.empty())
        cout << route.pop() << " -> ";

    return 0;
}
