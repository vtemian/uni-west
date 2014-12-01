#include "Arc.h"
#include <string>

using std::string;

/**
 * Equality operator for arcs:
 *
 * e1 == e2 if and only if:
 *   first node of e1 == first node of e2 AND second node of e1 == second node of e2
 *
 * (See C++ implementation below)
 */
bool operator==(const Arc& e1, const Arc& e2) {
	  return (e1.getFirst() == e2.getFirst() && e1.getSecond() == e2.getSecond());
}

/**
 * A comparison operator for arcs:
 */
bool operator< (const Arc& e1, const Arc& e2) {
	return e1.toString() < e2.toString();
}
