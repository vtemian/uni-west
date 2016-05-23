#include <iostream>

#ifndef rectangle
#define rectangle
#include "rectangle.h"
#endif

#include "square.h"

using namespace std;

int main() {
    Rectangle r1(10, 20), r2=r1;
    Square s;

    cout << "Area of r1 is " << r1.getArea() <<endl;
    cout << "Perimeter of r1 is " << r1.getPerimeter() <<endl;
    cout << "Square area of r1 is " << s.getArea(r1) <<endl;

    r1.printObj();
    return 0;
}
