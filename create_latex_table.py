import sys

input_filename = sys.argv[1]
serial_execution_time = float(sys.argv[2s])

with open(input_filename) as input_file:
	for line in input_file:
		num_threads, execution_time = line.split(',')
		execution_time = float(execution_time)
		print('''
		\hline
			{num_threads} & {execution_time} & {speedup} \\\\
		'''.format(num_threads=num_threads, execution_time=execution_time, speedup=serial_execution_time / execution_time))
