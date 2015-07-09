import sys
import os

# usage : python fileappend.py  "C:\\Temp\\test.log"
filehost = sys.argv[1]
print(filehost)
print(os.path.exists(filehost))
print(os.path.split(filehost))
fd, fr= os.path.split(filehost)
print("Directory contents: [ "+ fd + " ]\n" )
print(os.listdir(fd)) # list content of  directory

if os.path.exists(filehost):
	try:
		f= open(filehost, 'a+')
		f.write("# This is a test line appended.")

		#lines=f.readlines()
		#for ln in lines:
		#	print(ln)

		f.close()
	except:
		print("IOException occurred.")
		
"""
if os.path.isfile(filehost):
	try:
		with open(filehost) as file:
			print('File could be opened...')
		except IOError as e:
			print("Unable to open the file.")
"""	
