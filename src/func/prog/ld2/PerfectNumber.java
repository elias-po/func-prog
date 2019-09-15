package func.prog.ld2;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class PerfectNumber {

    enum STATE {
        ABUNDANT,
        DEFICIENT,
        PERFECT
    }

    public static Set<Integer> divisors(int n) {
        Set<Integer> divisors = new HashSet<>();
        IntPredicate modulus = (x) -> n % x == 0;
        IntStream
                .iterate(1, (i) -> i + 1)
                .limit(n)
                .filter(modulus)
                .forEach(divisors::add);
        return divisors;
    }


    public static STATE process(int n) {
        Set<Integer> divisors = divisors(n);

        int sum = divisors
                .stream()
                .reduce(0, (a, b) -> a + b);
        sum -= n;

        return sum > n ? STATE.ABUNDANT : (sum < n ? STATE.DEFICIENT : STATE.PERFECT);
    }
}
