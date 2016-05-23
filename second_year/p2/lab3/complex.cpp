#include <iostream>
#include <sstream>


using namespace std;

class complex {
    public:
        complex(double re=0.0, double im=0.0);
        complex(const complex& src);
        ~complex();
        double real();
        double img();
        string print();
    private:
        double re, im;
};

string complex::print() {
    std::ostringstream strs;
    strs <<re<<" "<<im;
    std::string str = strs.str();
    return str;
}

double complex::real() {
    return re;
}

double complex::img() {
    return im;
}

complex::complex(double re, double im) {
    cout<<"default constructor "<<re<<" "<<im<<endl;
    this->re = re;
    this->im = im;
}

complex::complex(const complex& src) {
    cout<<"copy constructor "<<src.re<<" "<<src.im<<endl;
    re = src.re;
    im = src.im;
}

complex::~complex() {
    cout<<"destructor "<<re<<" "<<im<<endl;
}

void assert(bool b) {
    if (!b) {
        cout<<"Assertion failed"<<endl;
    } else {
        cout<<"Assertion passed"<<endl;
    }
}

void f1(complex c) {
    cout<<"f1 "<<c.print()<<endl;
}

void f2(complex &c) {
    cout<<"f2 "<<c.print()<<endl;
}

void AssertComplex() {
    complex c1, c2=2, c3(1, -1), c4=c2;

    assert(c3.real() == 1);
    assert(c3.img() == -1);

    f1(c3);
    f2(c3);
}

int main() {
    AssertComplex();
    return 0;
}
