#!/usr/bin/env python
import sys

from parsers import TextParser
from schedulers import RRScheduler, FCFSScheduler, SJFScheduler, SRTFScheduler
from devices import CliOutputDevice


if len(sys.argv) < 2:
    print "Invalid number of arguments"

input_file = sys.argv[1]
content = TextParser(input_file).parse()
out = CliOutputDevice()

gantt, times = RRScheduler(content).schedule()
out.pprint("\n++++++ rr ++++++\n")
out.gantt(gantt)
out.times(times)

gantt, times = FCFSScheduler(content).schedule()
out.pprint("\n++++++ fcfc ++++++\n")
out.gantt(gantt)
out.times(times)

gantt, times = SJFScheduler(content).schedule()
out.pprint("\n++++++ sjf ++++++\n")
out.gantt(gantt)
out.times(times)

gantt, times = SRTFScheduler(content).schedule()
out.pprint("\n++++++ srtf ++++++\n")
out.gantt(gantt)
out.times(times)
