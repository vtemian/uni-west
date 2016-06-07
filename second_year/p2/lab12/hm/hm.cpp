#include <iostream>
#include "list.h"
#include "point.h"

using namespace std;

int main() {
    List <Point> list;

    list.append(Point(1, 1));
    list.append(Point(2, 2));
    list.append(Point(3, 14));

    List <Point>::Iterator index = list.begin(), end = list.end();
    for(; index != end; index++)
        cout << *index << " " << endl;

    return 0;
}
