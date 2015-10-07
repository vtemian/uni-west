/*
 * Node.cpp
 *
 *  Created on: Sep 30, 2014
 *      Author: Mircea Marin
 */

#include "Node.h"
#include <string>

using std::string;

bool operator==(const Node& m, const Node& n) { return m.id == n.id; }
bool operator< (const Node& m, const Node& n) { return m.id < n.id; }
