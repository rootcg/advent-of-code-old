package root.cristian;

import java.util.List;

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

    final int first(final List<String> lines) {
        throw new IllegalStateException("Not implemented");
    }

    final int second(final List<String> lines) {
        throw new IllegalStateException("Not implemented");
    }

}
