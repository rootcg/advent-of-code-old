package root.cristian;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid. You make a map
 * (your puzzle input) of the open squares (.) and trees (#) you can see. For example:
 * <pre>
 * ..##.......
 * #...#...#..
 * .#....#..#.
 * ..#.#...#.#
 * .#...##..#.
 * ..#.##.....
 * .#.#.#....#
 * .#........#
 * #.##...#...
 * #...##....#
 * .#..#...#.#
 * </pre>
 * These aren't the only trees, though; due to something you read about once involving arboreal genetics and biome
 * stability, the same pattern repeats to the right many times:
 * <pre>
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........#.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...##....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 * </pre>
 * You start on the open square (.) in the top-left corner and need to reach the bottom (below the bottom-most row on
 * your map).The toboggan can only follow a few specific slopes (you opted for a cheaper model that prefers rational
 * numbers); start by counting all the trees you would encounter for the slope right 3, down 1.
 * <p/>
 * Starting at the top-left corner of your map and following a slope of right 3 and down 1, how many trees would you
 * encounter?
 */
public class Day3 {

    private final record Point(int x, int y) {}

    private static final char TREE = '#';

    final long first(final List<String> lines) {
        char[][] map = generateMap(lines);
        Stream<Point> moves = Stream.iterate(new Point(0, 0), point -> new Point(point.x() + 3, point.y() + 1));

        return moves.takeWhile(point -> point.y() < map.length && point.x() < map[point.y()].length)
                    .filter(point -> map[point.y()][point.x()] == TREE)
                    .count();
    }

    final int second(final List<String> lines) {
        throw new IllegalStateException("Not implemented");
    }

    private char[][] generateMap(final List<String> lines) {
        final int requiredLength = 1 + lines.size() * 3;
        final Function<String, char[]> scaleRow = (line) ->
                IntStream.range(0, requiredLength / line.length())
                         .flatMap(ignore -> line.chars())
                         .mapToObj(Character::toString)
                         .collect(Collectors.joining())
                         .toCharArray();

        return lines.stream().map(scaleRow).collect(Collectors.toList()).toArray(char[][]::new);
    }

}
