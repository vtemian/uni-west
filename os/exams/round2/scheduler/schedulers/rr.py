from copy import copy
from Queue import Queue, Empty

from .base import Scheduler


class RRScheduler(Scheduler):
    def schedule(self, quanta=None):
        gantt = []

        if quanta is None:
            quanta = self._get_quanta(sorted([proc['burst'] for proc in self.content]))

        request_queue = Queue()

        procs = sorted(self.content, key=lambda proc: proc['arrival'])[::-1]
        now = procs[len(procs) - 1]['arrival']
        self.start = now
        request_queue.put(copy(procs.pop()))

        while True:
            try:
                start = now
                proc = request_queue.get(False)

                if proc['burst'] < quanta:
                    now += proc['burst']
                else:
                    now += quanta

                while len(procs) > 0 and procs[len(procs) - 1]['arrival'] <= now:
                    request_queue.put(copy(procs.pop()))

                if proc['burst'] > quanta:
                    proc['burst'] -= quanta
                    request_queue.put(copy(proc))

                gantt.append({
                    'proc': proc['index'],
                    'from': start,
                    'to': now,
                })

            except Empty:
                break
        return gantt, self._parse_gantt(gantt)

    def _get_quanta(self, burst):
        return burst[int(len(burst) * 0.8) - 1]
