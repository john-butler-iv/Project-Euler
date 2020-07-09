default:
	javac projectEuler/*.java
	java projectEuler.ProblemTimer51To100 solve 96

clean:
	rm /projectEuler/*.class
