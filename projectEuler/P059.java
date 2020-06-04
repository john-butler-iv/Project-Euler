package projectEuler;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class P059 extends Problem {

	private String data;

	public P059() {
		readData("p059.txt");
	}

	@Override
	public long solve(boolean printResults) {
		for (char a = 'a'; a <= 'z'; a++) {
			for (char b = 'a'; b <= 'z'; b++) {
				for (char c = 'a'; c <= 'z'; c++) {

					String text = decrypt(new String(new char[] { a, b, c }));
					if (text.contains(" the ")) {

						int total = 0;
						for (int i = 0; i < text.length(); i++)
							total += text.charAt(i);

						if (printResults)
							System.out.println(total + " is the sum of the ASCII values of the decrypted message");
						return total;

					}
				}
			}
		}

		if (printResults)
			System.out.println("could not find the password");
		return 0;
	}

	private String decrypt(String key) {
		String text = "";

		for (int i = 0; i < data.length(); i++)
			text += (char) (data.charAt(i) ^ key.charAt(i % key.length()));

		return text;
	}

	private void readData(String filename) {
		try {
			Scanner scanner = new Scanner(new File(filename));
			scanner.useDelimiter(",");

			char[] rawData = new char[1455];

			for (int i = 0; scanner.hasNext(); i++)
				rawData[i] = (char) scanner.nextInt();

			this.data = new String(rawData);

		} catch (IOException e) {
			e.printStackTrace();
			this.data = "";
		}
	}

	@Override
	public String getTitle() {
		return "Problem 059: XOR Decryption";
	}
}
