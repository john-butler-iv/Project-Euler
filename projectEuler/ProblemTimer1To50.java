package projectEuler;

import java.lang.NumberFormatException;

class ProblemTimer1To50 extends ProblemTimer {
	public static void printUsage(){
		System.out.println("Usage: java projectEuler.ProblemTimer1To50 <solve/report> <pid pid ...>");
	}

	public static void main(String[] args){
		if(args.length < 2){
			printUsage();
			return;
		}

		ProblemTimer51To100 instance = new ProblemTimer51To100();
		if(args[0].toLowerCase().equals("solve")){
			for(int i = 1; i < args.length; i++){
				try{
					int pid = Integer.valueOf(args[i]);
					if(pid > 50)
						System.out.println("There is no problem numbered " + pid);
					else if (pid <= 0)
						System.out.println("There is no problem numbered " + pid);
					else {
						Problem problem = instance.problems[pid - 1];
						if(problem == null)
							System.out.println("There is no problem numbered " + pid);
						else {
							problem.solve(true);
						}
					}
				} catch(NumberFormatException e){
					System.out.println(args[i] + " is an invalid problem number");
				}
			}
		} else if(args[0].toLowerCase().equals("report")){

		} else printUsage();

	}


	public ProblemTimer1To50() {
		problems = new Problem[50];
		problems[1] = new P002();
		problems[2] = new P003();
		problems[3] = new P004();
		problems[4] = new P005();
		problems[5] = new P006();
		problems[6] = new P007();
		problems[7] = new P008();
		problems[8] = new P009();
		problems[9] = new P010();
		problems[10] = new P011();
		problems[11] = new P012();
		problems[12] = new P013();
		problems[13] = new P014();
		problems[14] = new P015();
		problems[15] = new P016();
		problems[16] = new P017();
		problems[17] = new P018();
		problems[18] = new P019();
		problems[19] = new P020();
		problems[20] = new P021();
		problems[21] = new P022();
		problems[22] = new P023();
		problems[23] = new P024();
		problems[24] = new P025();
		problems[25] = new P026();
		problems[26] = new P027();
		problems[27] = new P028();
		problems[28] = new P029();
		problems[29] = new P030();
		problems[30] = new P031();
		problems[31] = new P032();
		problems[32] = new P033();
		problems[33] = new P034();
		problems[34] = new P035();
		problems[35] = new P036();
		problems[36] = new P037();
		problems[37] = new P038();
		problems[38] = new P039();
		problems[39] = new P040();
		problems[40] = new P041();
		problems[41] = new P042();
		problems[42] = new P043();
		problems[43] = new P044();
		problems[44] = new P045();
		problems[45] = new P046();
		problems[46] = new P047();
		problems[47] = new P048();
		problems[48] = new P049();
		problems[49] = new P050();
	}

}
