package projectEuler;

import java.lang.NumberFormatException;

public class ProblemTimerMisc extends ProblemTimer {

	
	public static void printUsage(){
		System.out.println("Usage: java projectEuler.ProblemTimerMisc <solve/report> <pid pid ...>");
	}
	public static void main(String[] args){
		if(args.length < 2){
			printUsage();
			return;
		}

		ProblemTimerMisc instance = new ProblemTimerMisc();

		if(args[0].toLowerCase().equals("solve")){
			for(int i = 1; i < args.length; i++){
				try{
					int pid = Integer.valueOf(args[i]);
					if (pid <= 0)
						System.out.println("There is no problem numbered " + pid);
					else if (pid <= 100)
						instance.previousTimer.main(new String[]{"solve", args[i]});
					else {
						Problem problem;
						switch(pid){
						case 102:
							problem = instance.problems[0];
							break;
						case 144:
							problem = instance.problems[1];
							break;
						case 205:
							problem = instance.problems[2];
							break;
						case 206:
							problem = instance.problems[3];
							break;
						case 301:
							problem = instance.problems[4];
							break;
						default:
							problem = null;
						}

						if(problem == null)
							System.out.println("There is no problem numbered " + pid);
						else 
							problem.solve(true);
					}
				} catch(NumberFormatException e){
					System.out.println(args[i] + " is an invalid problem number");
				}
			}
		} else if(args[0].toLowerCase().equals("report")){

		} else printUsage();

	}

	public ProblemTimerMisc() {
		previousTimer = new ProblemTimer51To100();

		problems = new Problem[5];
		problems[0] = new P102();
		problems[1] = new P144();
		problems[2] = new P205();
		problems[3] = new P206();
		problems[4] = new P301();
	}

}
