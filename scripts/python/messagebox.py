import ctypes

mbox=ctypes.windll.user32.MessageBoxW
# This message box will have "YES NO CANCEL"
ret=mbox(None, "Press OK to end.","Message Box Demo in Python",  0x3)
# This message box will have "OK CANCEL"
#ret=mbox(None, "Press OK to end.","Message Box Demo in Python",  0x1)
print(ret)

if ret == 1 :
	print("You pressed OK.")
elif ret == 6: 
	print("You pressed YES.")
elif ret == 7:
	print("You pressed No.")
else :
	print("You pressed Cancel.")
	