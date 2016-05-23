#include "square.h"

Square::Square(Rectangle rect) {
    this->length = rect.length;
    Rectangle::obj++;
}

double Square::getArea() {
    return rect.length * rect.length;
};
