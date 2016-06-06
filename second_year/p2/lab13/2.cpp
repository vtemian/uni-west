#include <iostream>
#include <list>
#include <string>

using namespace std;

int main() {
    list<string> name;

    name.push_back("midle name");
    name.push_front("last name");
    name.push_front("first name");

    cout << "the list is ";

    for (list<string>::iterator it = name.begin(); it != name.end(); it++) {
        cout << *it << ", ";
    }

    cout << endl;

    cout << "the last element is: " << (string) name.back() << endl;
    cout << "the dimension of the list is: " << name.size() << endl;

    return 0;
}
