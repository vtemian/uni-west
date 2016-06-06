#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>
#include <iterator>

using namespace std;

int main() {

    string from, to;

    cout << "The name of the file which is read : "; cin >> from;
    cout << "The name of the file where we write : "; cin >> to;

    //open the file
    ifstream is(from.c_str());

    //define file iterators
    istream_iterator <string> isi(is);
    istream_iterator <string> eos; //end of stream

    //define vector which contains informations from the file
    vector <string> v(isi, eos);

    //sort words from the vector
    sort(v.begin(), v.end());

    //open file for writing
    ofstream os(to.c_str());

    //define iterator
    ostream_iterator <string> osi(os, "\n");

    //write in a file without duplications
    unique_copy(v.begin(), v.end(), osi);

    return (!is.eof() && !os);
}
