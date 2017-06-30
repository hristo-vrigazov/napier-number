import sys
from matplotlib import pyplot as plt


input_filename = sys.argv[1]
serial_execution_time = float(sys.argv[2])

x, y = [], []

with open(input_filename) as input_file:
	for line in input_file:
		num_threads, execution_time = line.split(',')
		execution_time = float(execution_time)
		x.append(num_threads)
		y.append(serial_execution_time / execution_time)


plt.plot(x, y)

plt.title('Number of threads/Speedup')
plt.ylabel('Speedup')
plt.xlabel('Number of threads')

plt.show()