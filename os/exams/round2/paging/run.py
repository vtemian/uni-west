#!/usr/bin/env python
import sys

from devices import CliOutputDevice
from parsers import TextParser
from algorithms import (FIFOAlgorithm, OptimalAlgorithm, LRUAlgorithm,
                        ClockAlgorithm)


if len(sys.argv) < 2:
    print "Invalid number of arguments"

out = CliOutputDevice()
content = TextParser(sys.argv[1]).parse()

result = FIFOAlgorithm(content, 4).compute()
out.pprint("++++++ fifo +++++++")
out.table(result)

result = OptimalAlgorithm(content, 4).compute()
out.pprint("++++++ optimal +++++++")
out.table(result)

result = LRUAlgorithm(content, 4).compute()
out.pprint("++++++ lru +++++++")
out.table(result)

result = ClockAlgorithm(content, 4).compute()
out.pprint("++++++ clock +++++++")
out.table(result)
