#include <iostream>

using namespace std;

class Date {
    public:
        int day, month, year;
        void init(int day, int month, int year);
        Date(int, int, int);
};

typedef int Date::*PM;
typedef void (Date::*PMF)(int, int, int);

Date::Date(int d, int m, int y) {
    day = d;
    month = m;
    year = y;
}

void Date::init(int d, int m, int y) {
    day = d;
    month = m;
    year = y;
}


/*
void f(){
    Date today;
    Date *pdate = &today;

    today.init(13, 3, 2013);
    cout<<"Today is "<<pdate->day<<" "<<pdate->month<<" "<<today.year;
}*/

void f() {
    Date today(1,1,12);
    Date *pdate = &today;

    PM pm = &Date::day;
    PMF pmf = &Date::init;

    //(today.*pmf)(14, 3, 2012); // today.init(14, 3, 2012);
    //today.*pm = 8; // today.day = 8

    //(pdate->*pmf) (14, 3, 2012); // today.init(14, 3, 2012);
    //pdate->*pm = 8; // today.day = 8
    cout<<today.day<<" "<<today.month<<" "<<today.year<<endl;
}

int main() {
    f();
    return 0;
}
