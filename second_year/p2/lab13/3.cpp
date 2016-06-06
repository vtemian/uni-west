#include <iostream>
#include <map>
#include <string>
#include <functional>

using namespace std;

int main() {

    //declaring a mapping for days names
    map <string, int> days;

    //adding informations in map
    days["Monday"] = 1;
    days["Tuesday"] = 2;
    days["Wednesday"] = 3;
    days["Thursday"] = 4;
    days["Friday"] = 5;
    days["Saturday"] = 6;
    days["Sunday"] = 7;

    //display the informations from map
    cout << "Day-Name\n";

    map <string, int>::const_iterator it;
    for (it = days.begin(); it != days.end(); it++) {
        hashIt(it->first);
        cout << it->second << " " << it->first << "\n";
    }

    return 0;
}
