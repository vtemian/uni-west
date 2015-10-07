from copy import copy

from .base import Scheduler


class SRTFScheduler(Scheduler):
    def schedule(self):
        gantt = []
        request_queue = []

        procs = sorted(self.content, key=lambda proc: proc['arrival'])[::-1]

        now = procs[len(procs) - 1]['arrival']
        self.start = now

        request_queue.append(copy(procs.pop()))
        while procs[len(procs) - 1]['arrival'] <= now:
            request_queue.append(copy(procs.pop()))

        request_queue = sorted(request_queue, key=lambda proc: proc['burst'])

        while len(request_queue):
            start = now
            proc = request_queue.pop(False)

            now += 1

            if proc['burst'] > 1:
                proc['burst'] -= 1
                request_queue.append(proc)

            while len(procs) > 0 and procs[len(procs) - 1]['arrival'] <= now:
                request_queue.append(copy(procs.pop()))

            request_queue = sorted(request_queue, key=lambda proc: proc['burst'])

            gantt.append({
                'proc': proc['index'],
                'from': start,
                'to': now,
            })

        return gantt, self._parse_gantt(gantt)
