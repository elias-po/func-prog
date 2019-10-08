package func.prog.ld2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class PerfectNumber {

    enum STATE {
        ABUNDANT,
        DEFICIENT,
        PERFECT
    }

    public static Map<Integer, STATE> stateMap = new HashMap<>();

    public static void initStateMap() {
        stateMap.put(-1, STATE.DEFICIENT);
        stateMap.put(0, STATE.PERFECT);
        stateMap.put(1, STATE.ABUNDANT);
    }

    public static Set<Integer> divisors(int n) {
        Set<Integer> initialDivisors = new HashSet<>();
        Set<Integer> divisors = new HashSet<>();
        IntPredicate modulus = (x) -> n % x == 0;
        Predicate<Integer> moreModulus = (y) -> n % (n/y) == 0;

        IntStream
                .iterate(1, (i) -> i + 1)
                .limit((long) Math.ceil(Math.sqrt(n)))
                .filter(modulus)
                .forEach(initialDivisors::add);

        initialDivisors
                .stream()
                .filter(moreModulus)
                .map(y -> n/y)
                .forEach(divisors::add);

        divisors.addAll(initialDivisors);
        return divisors;
    }


    public static STATE process(int n) {
        initStateMap();
        Set<Integer> divisors = divisors(n);

        int sum = divisors
                .stream()
                .reduce(0, (a, b) -> a + b);
        sum -= n;
        
        try {
            return stateMap.get((sum - n) / (Math.abs(sum - n)));
        } catch (Exception e) {
            return stateMap.get(0);
        }
    }
}
