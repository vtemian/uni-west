import Queue
import copy
fd = open('processes.txt')

processes = []
endTime = 0
time = 0

for line in fd:
		tempProc = line.split(" ")
		tempProc[0] = int(tempProc[0])
		tempProc[1] = int(tempProc[1])
		tempProc.append(0)
		tempProc.append(0)
		tempProc.append(0)
		process = (arrival, burst, tw, tr, visited) = tempProc
		processes.append(process)

for process in processes:
	#print("Arrival {}; Burst {}".format(process[0],process[1]))
	endTime += int(process[1])
	pass

backupProcesses = copy.deepcopy(processes)

def getProcessesFifo(q, ps, time):
	for process in ps:
		if process[0] <= time and int(process[4]) == 0:
			process[4] = 1
			q.append(process)
	q.sort(key=lambda tup: tup[0])
	return q

def computeTr(ps):
	for process in ps:
		process[3] = process[1] + process[2]		


def fifo(ps):
	time = -1
	q = []
	while len(q) == 0:
		time +=1
		q = getProcessesFifo(q, ps, time)
	while time < endTime:
		q = getProcessesFifo(q, ps, time)
		process = q.pop(0)
		process[2] = time - process[0]	
		time += process[1]
	computeTr(ps)
	print "Fifo"
	print "Arr	Burst		Tw		Tr"
	for process in ps:
		print("{} 	{}		{}		{}".format(process[0],process[1],process[2],process[3]))

def sjf(ps):
	time = -1
	q = []
	while len(q) == 0:
		time +=1
		q = getProcessesFifo(q, ps, time)
	while time < endTime:
		q = getProcessesFifo(q, ps, time)
		q.sort(key=lambda tup: tup[1])
		process = q.pop(0)
		process[2] = time - process[0]	
		time += process[1]
	computeTr(ps)
	print "SJF"
	print "Arr	Burst		Tw		Tr"
	for process in ps:
		print("{} 	{}		{}		{}".format(process[0],process[1],process[2],process[3]))

fifo(processes)
processes = copy.deepcopy(backupProcesses)
sjf(processes)
processes = copy.deepcopy(backupProcesses)

