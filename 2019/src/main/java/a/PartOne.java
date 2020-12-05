package a;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PartOne {

	public static void main(String[] args) throws URISyntaxException, IOException {
		Path inputPath = Paths.get(Thread.currentThread().getContextClassLoader().getResource("first/input.txt").toURI());
		System.out.println(calculateFuelCount(inputPath));
	}

	private static long calculateFuelCount(Path inputPath) throws IOException {
		try (Stream<String> moduleMassStream = Files.lines(inputPath)) {
			return moduleMassStream.map(Long::valueOf)
								   .map(mass -> mass / 3 - 2)
								   .reduce(Long::sum)
								   .orElseThrow(IOException::new);
		}
	}

}
