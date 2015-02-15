from pprint import pprint

from .base import OutputDevice


class CliOutputDevice(OutputDevice):
    def pprint(self, content):
        print content

    def gantt(self, gantt):
        print "======= gantt ========"
        pprint(gantt)
        print "\n"

    def times(self, times):
        print "======= times ========"
        pprint(times)
        print "\n"
