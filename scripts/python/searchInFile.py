import sys
import re
import os

# usage : python searchInFile.py "074M12 0" "c:\\inputfiles"

srchStr = sys.argv[1]
print("Search String :"+srchStr)
count = 0

for (dname, dir,files) in os.walk(sys.argv[2]):
	#print("Dir: "+dname)
	for f in files:
		if f.endswith('.txt'):
			#print("File :"+ f)
			lines = open(dname+'\\'+f, 'r').readlines()
			filecnt = 0
			for ln in lines:
				if re.search(srchStr, ln):
					print("From "+f+ " : " + ln )
					count = count + 1
					filecnt = filecnt + 1
print("Found: "+ str(count)+ " hits in " + str(filecnt) + " file(s)")
		
		