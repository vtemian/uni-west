class Operations {
    static int a;
    int b;
    public:
        void set(int i, int j) {a = i; b = j;}
        static void print();
        int print(int i, int j) {return i + j;};
};

int Operations::a;
void Operations::print() {
    cout<<a<<endl;
}

int main() {
    Operations o1, o2;

    o1.set(10, 10);
    o2.set(20, 20);

    Operations::print();

    o1.print();
    o2.print();

    cout<<
    return 0;
}
