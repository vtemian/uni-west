from abc import ABCMeta, abstractmethod


class Scheduler(object):
    __meta__ = ABCMeta

    def __init__(self, content):
        self.content = content

    @abstractmethod
    def schedule(self):
        pass

    def _parse_gantt(self, gantt):
        times = {}
        procs = {
            proc['index']: {
                'arrival': proc['arrival'],
                'burst': proc['burst']
            } for proc in self.content
        }

        for proc in procs:
            if proc not in times:
                times[proc] = {
                    'tw': 0,
                    'tr': 0
                }

            abstr = 0
            for scheduled in gantt:
                if proc == scheduled['proc']:
                    abstr = scheduled['to']

            times[proc]['tr'] = abstr - procs[proc]['arrival']
            times[proc]['tw'] = times[proc]['tr'] - procs[proc]['burst']

        return times
