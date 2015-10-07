/*
 * Edge.cpp
 *
 *  Created on: Sep 30, 2014
 *      Author: Mircea Marin
 */

#include "Edge.h"
#include <string>

using std::string;

/**
 * Equality operator for  edges:
 *
 * e1 == e2 if and only if:
 *   first node of e1 == first node of e2 AND second node of e1 == second node of e2
 *   OR
 *   first node of e1 == second node of e2 AND second node of e1 == first node of e2
 *
 * (See C++ implementation below)
 */
bool operator==(const Edge& e1, const Edge& e2) {
	  return ((e1.getFirst() == e2.getFirst() && e1.getSecond() == e2.getSecond())
			  || (e1.getFirst() == e2.getSecond() && e1.getSecond() == e2.getFirst()));
}

/**
 * A comparison operator for edges:
 */
bool operator< (const Edge& e1, const Edge& e2) {
	return e1.toString() < e2.toString();
}
