default:
	javac projectEuler/*.java
	java projectEuler.ProblemTimerMisc solve 102

clean:
	rm /projectEuler/*.class
