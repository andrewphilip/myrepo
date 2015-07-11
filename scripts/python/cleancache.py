import os
import sys

# Temporary internet files path: C:\\Users\\Philip_a\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files
print("Starting to cleanup temporay internet files folder..")
filepath = "C:\\Users\\Philip_a\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files"
count =0
dircnt=0
if os.path.exists(filepath):
	os.chdir(filepath)
	print(os.getcwd())
	print("could access temporary internet file folder...")
	flist =[ f for f in os.listdir(".")]
	print(flist)
	for f in flist:
		if os.path.isfile(f):
			#os.remove(f)
			print("removed file "+ f)
			count = count + 1
"""			
	for (dname, dir,files) in os.walk(filepath):
		for f in files:
			if not (f.endswith('.dat') | f.endswith('.ini') ):
				try:
					ffp= dname +"\\"+f
					if os.path.exists(ffp):
						if os.path.isdir(ffp):
							dircnt = dircnt + 1
							os.rmdir(ffp)
						else:
							print("removing file :"+ffp)
							os.remove(ffp)
							count = count + 1
				except:
					print("Exception occurred in removing files from Temp Internet Files.")
"""

print("End: finished cleanup task.")
print("Files removed : "+ str(count))
# print("Directories removed : "+ str(dircnt))

