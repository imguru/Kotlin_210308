
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Sample {
    static List<Integer> filter(List<Integer> data, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();

        for (Integer e : data) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int n = 10;
		List<Integer> result1 = filter(data, (e) -> e % 2 == 0);
		List<Integer> result2 = filter(data, (e) -> e % 2 == 0);
		List<Integer> result3 = filter(data, (e) -> e % 2 == 0);

	}
}
