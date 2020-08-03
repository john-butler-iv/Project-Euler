default:
	javac projectEuler/*.java
	java projectEuler.ProblemTimerMisc solve 144

clean:
	rm /projectEuler/*.class
