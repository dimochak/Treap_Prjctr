package utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class TestGenerator {
    public static Integer[] generateSequence(int n) {
        return Arrays.stream(IntStream.range(1, n + 1).toArray()).boxed().toArray(Integer[]::new);
    }

    public static Integer[] generateCustomArray(int n) {
        Integer[] result = generateSequence(n);
        Collections.shuffle(Arrays.asList(result));
        return result;
    }

    public static Integer[] generateReversedSequence(int n) {
        return Arrays.stream(generateSequence(n)).map(i -> n - i + 1).toArray(Integer[]::new);
    }

    public static int[] generatePrimitivesSequence(int n) {
        return Arrays.stream(generateSequence(n)).mapToInt(Integer::intValue).toArray();
    }

}
