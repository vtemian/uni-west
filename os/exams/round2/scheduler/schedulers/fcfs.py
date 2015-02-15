from copy import copy
from Queue import Queue, Empty

from .base import Scheduler


class FCFSScheduler(Scheduler):
    def schedule(self):
        gantt = []
        request_queue = Queue()

        procs = sorted(self.content, key=lambda proc: proc['arrival'])[::-1]
        now = procs[len(procs) - 1]['arrival']
        request_queue.put(copy(procs.pop()))
        self.start = now

        while True:
            try:
                start = now
                proc = request_queue.get(False)

                now += proc['burst']

                while len(procs) > 0 and procs[len(procs) - 1]['arrival'] <= now:
                    request_queue.put(copy(procs.pop()))

                gantt.append({
                    'proc': proc['index'],
                    'from': start,
                    'to': now,
                })

            except Empty:
                break
        return gantt, self._parse_gantt(gantt)
