import urllib2
import os
import socket

timeout = 30
socket.setdefaulttimeout(timeout)

try:
	resp = urllib2.urlopen('http://pythontest3.org')
	#html = resp.read()
except urllib2.HTTPError as e:
	print( e.code)
	print(e.read())
except Exception as e:
	print("The server could not serve the request.")
	print('Error msg: '+ e.message)
	print('Error args: \n')
	print( e.args)
except URLError as e:
	print('Unable to reach the server.')
	print('Reason: '+e.reason)
else:
	print('Success in reaching the site.')

