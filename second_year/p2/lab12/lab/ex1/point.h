#include <iostream>

class Point {
    int x, y;
    public:
        Point() {
            x = 0;
            y = 0;
        };
        Point(int x, int y) {
            this->x = x;
            this->y = y;
        };
        friend std::ostream &operator<< (std::ostream &output, const Point &p) {
            output << "(" << p.x << ", " << p.y << ")";
            return output;
        };
        int getX() {
            return x;
        };
        int getY() {
            return y;
        };
};
