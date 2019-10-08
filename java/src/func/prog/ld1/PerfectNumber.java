package func.prog.ld1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PerfectNumber {

    enum STATE {
        ABUNDANT,
        DEFICIENT,
        PERFECT
    }

    public static Set<Integer> divisors(int n) {
        Set<Integer> divisors = new HashSet<Integer>();
        divisors.add(1);
        for (int i = 2; i <= n; i++) {
            if (n % i == 0)
                divisors.add(i);
        }

        return divisors;
    }

    public static STATE process(int n) {
        Set<Integer> divisors = divisors(n);

        Iterator i = divisors.iterator();
        int sum = 0;

        while (i.hasNext())
            sum += (int) i.next();
        sum -= n;

        if (sum > n)
            return STATE.ABUNDANT;
        if (sum < n)
            return STATE.DEFICIENT;
        return STATE.PERFECT;
    }
}
