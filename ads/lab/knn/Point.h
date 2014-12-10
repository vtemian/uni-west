#include <sstream>

struct Point {
    float x[3];

    Point(float a, float b, float c){
        x[0] = a;
        x[1] = b;
        x[2] = c;
    }

    Point(){
        x[0] = x[1] = x[2] = 0;
    }

    std::string toString(){
        std::ostringstream out;
        out<<"("<<x[0]<<","<<x[1]<<","<<x[2]<<")";
        return out.str();
        //return "( " + std::to_string(x[0]) + ", " + std::to_string(x[1]) + ", " + std::to_string(x[2])+ ")";
    }
    float distance(Point &point) {
        float distance = 0;
        for(int i=0; i<3; i++){
            distance += (x[i] - point[i]) * (x[i] - point[i]);
        }
        return sqrt(distance);
    }
    inline float operator[] (const int index){
        return x[index];
    }
};


