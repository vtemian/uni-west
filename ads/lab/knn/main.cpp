#include <algorithm>
#include <sstream>
#include <fstream>
#include <iostream>
#include <vector>

#include "Point.h"

using namespace std;

Point test_point;

bool comparator(Point a, Point b) {
    return a.distance(test_point) < b.distance(test_point);
}

bool cmp0(Point a, Point b) {
    return a[0] < b[1];
}

bool cmp1(Point a, Point b) {
    return a[1] < b[1];
}

bool cmp2(Point a, Point b) {
    return a[2] < b[2];
}

typedef bool(*CMP)(Point, Point);
CMP cmp[] = {
        &cmp0, &cmp1, &cmp2
};

int main() {
    string line;
    float a, b, c;
    std::vector<Point> points;
    ifstream fin("/home/wok/university/ads/lab/knn/input.txt");

    cout<<fin.is_open();
    // load points from file
    while(getline(fin, line)) {
        istringstream iss(line);
        if(!(iss>>a>>b>>c)) break;
        points.push_back(Point(a, b, c));
    }

    // get point from user
    cout<<"Test point: ";
    cin>>a>>b>>c;
    test_point = Point(a, b, c);
    sort(points.begin(), points.end(), comparator);

    for(std::vector<Point>::iterator it = points.begin(); it != points.end(); ++it) {
        cout<<it->toString()<<" "<<it->distance(test_point)<<endl;
    }

    return 0;
}