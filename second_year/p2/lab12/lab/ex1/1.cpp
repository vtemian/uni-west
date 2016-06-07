#include <iostream>
#include "stack.h"
#include "point.h"

using namespace std;

int main() {
    int i;
    Stack<Point> s(2);

    for (i=0; i < 2; i++) {
        s.add(Point(1, 2));
    }

    return 0;
}
