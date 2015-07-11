import os
import time

t1 = time.time()
count =0
print('Starting file count...');
for (dname, dir,files) in os.walk('c:\\lunaworkspace'):
#	print('scanning directory:'+dname)
	for f in files:
		if f.endswith('.txt'):
			count = count +1
t2 = time.time()			
print('Files:' + str(count))
print('Time taken: %f' %(t2 - t1))
