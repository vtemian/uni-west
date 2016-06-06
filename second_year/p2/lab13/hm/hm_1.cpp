#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>
#include <iterator>
#include <map>
#include <list>

using namespace std;

int main() {

    string from, to, blacklist_from;

    cout << "The name of the file which is read : "; cin >> from;
    cout << "The name of the file which is written : "; cin >> to;
    cout << "The name of the file containing blacklisted words: "; cin >> blacklist_from;

    //open the file
    ifstream is(from.c_str());

    //define file iterators
    istream_iterator <string> isi(is);
    istream_iterator <string> eos; //end of stream

    //define vector which contains informations from the file
    vector <string> words(isi, eos);

    map <string, int> wordCount;
    map<string, int>::iterator wordCountIterator;

    for (vector<string>::iterator it = words.begin(); it != words.end(); it++) {

        wordCount[*it] ++;

        wordCountIterator = wordCount.find(*it);

        if ( wordCount.end() != wordCountIterator ) {
            wordCountIterator->second += 1;
        } else {
            wordCount.insert(pair<string, int>(*it, 1));
        }

    }

    ifstream blacklist_file(blacklist_from.c_str());
    //define file iterators
    istream_iterator <string> blacklist_isi(blacklist_file);
    istream_iterator <string> blacklist_eos; //end of stream

    vector <string> blacklisted_words(blacklist_isi, blacklist_eos);

    ofstream out(to.c_str());

    map <string, int>::const_iterator it;
    for (it = wordCount.begin(); it != wordCount.end(); it++) {
        if (find(blacklisted_words.begin(), blacklisted_words.end(), it->first) == blacklisted_words.end())
            out << it->second << " " << it->first << "\n";
    }

    return (!is.eof() && !out);
}
