import os
import sys

print(os.getcwd()) #current working dir
print(os.path.abspath('.')) #alternative way
print(os.listdir('.'))  # list all files and directories in the current directory.
os.chdir('../');
if os.path.exists('./python'):
	sys.stdout.write('python dir exists.\n')

if os.path.isdir('./python'):
	sys.stdout.write('python is a directory\n')
	
sys.stderr.write('Warning: Testing error output\n')
sys.stdout.write('Info: logging info...\n')
print(sys.path)
print('\n'.join(sys.modules.keys()))