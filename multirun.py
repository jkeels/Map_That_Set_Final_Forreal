#!/usr/bin/python
import subprocess

if __name__ == "__main__":
	
	a = []
	nmax = 100
	nstep = 5
	kmax = 20;
	kstep = 2;
	
	for i in range(5, nmax+1, nstep):
		a.append([])
		a[0].append("n\k")
		for j in range(2, kmax+1, kstep):
			a[0].append(j)
	
	row = 1
	for i in range(5, nmax+1, nstep):
		a.append([])
		a[row].append(i)
		col = 0
		for j in range(2, kmax+1, kstep):
			command = ["java", "-jar", "mtsProgram.jar", "-x", str(i), str(j)]
			process = subprocess.Popen(command, shell=False, stdout=subprocess.PIPE)
			output = process.communicate()[0]
			pos = -1
			x = output[-1]
			while (x != " "):
				pos -= 1
				x = output[pos:pos+1]
			a[row].append(int(output[pos+1:-1]))
			col += 1
		row += 1
	
	for i in range(0, row):
		for j in range(0, col):
			print a[i][j],
			print "\t",
		print ""