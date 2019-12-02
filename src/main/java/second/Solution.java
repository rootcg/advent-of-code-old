package second;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	private static List<Integer> memory;

	public static void main(String[] args) throws URISyntaxException, IOException {
		final List<Integer> initialMemory = readInput();
		final int nounAddress = 1;
		final int verbAddress = 2;

		int noun = 0;
		int verb = 0;

		for (int i = 0; i <= 99; i++) {
			for (int j = 0; j <= 99; j++) {
				memory = new ArrayList<>(initialMemory);
				memory.set(nounAddress, i);
				memory.set(verbAddress, j);
				runProgram();

				if(memory.get(0) == 19690720) {
					noun = i;
					verb = j;
					break;
				}
			}
		}

		System.out.println("Desired noun: " + noun);
		System.out.println("Desired verb: " + verb);
	}

	private static void runProgram() {
		int instructionPointer = 0;
		for (;;) {
			int opcode = memory.get(instructionPointer);
			if (opcode == 99)
				break;

			execute(opcode, memory.get(instructionPointer + 1),
					memory.get(instructionPointer + 2),
					memory.get(instructionPointer + 3));

			instructionPointer += 4;
		}
	}

	private static void execute(int opcode, int... params) {
		int n1 = memory.get(params[0]);
		int n2 = memory.get(params[1]);
		int resultAddress = params[2];

		switch (opcode) {
			case 1:
				memory.set(resultAddress, n1 + n2);
				break;
			case 2:
				memory.set(resultAddress, n1 * n2);
		}
	}

	private static List<Integer> readInput() throws FileNotFoundException, URISyntaxException {
		Path inputPath = Paths.get(Thread.currentThread().getContextClassLoader().getResource("second/input.txt").toURI());
		List<Integer> integerList = new ArrayList<>();

		try (Scanner s = new Scanner(inputPath.toFile()).useDelimiter(",")) {
			while (s.hasNextInt()) {
				integerList.add(s.nextInt());
			}
		}

		return integerList;
	}

}
