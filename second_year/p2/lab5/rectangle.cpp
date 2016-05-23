#include <iostream>
#include "rectangle.h"

using namespace std;

int Rectangle::obj = 0;

Rectangle::~Rectangle() {
    cout << "destructor" << endl;

    obj--;
}

Rectangle::Rectangle(double length, double width) {
    this->length = length;
    this->width = width;

    obj++;
}

Rectangle::Rectangle(const Rectangle& rect) {
    this->length = rect.length;
    this->width = rect.width;

    obj++;
}

double Rectangle::getArea() {
    return this->length * this->width;
}

double Rectangle::getPerimeter() {
    return 2 * (this->length + this->width);
}

void Rectangle::printObj() {
    cout << "Objects " << obj << endl;
}
