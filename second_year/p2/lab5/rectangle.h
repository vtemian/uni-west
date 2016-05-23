class Rectangle {
    double length, width;
    static int obj;
    public:
        explicit Rectangle(double length=0, double width=0);
        ~Rectangle();
        Rectangle(const Rectangle& rect);
        double getArea();
        double getPerimeter();
        static void printObj();
    friend class Square;
};
