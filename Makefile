default:
	javac projectEuler/*.java

solveAll:
	java projectEuler.ProblemTimerMisc solve all
reportAll:
	java projectEuler.ProblemTimerMisc report all

PID?=0
solveOne:
	java projectEuler.ProblemTimerMisc solve $(PID)
reportOne:
	java projectEuler.ProblemTimerMisc report $(PID)

clean:
	rm /projectEuler/*.class
