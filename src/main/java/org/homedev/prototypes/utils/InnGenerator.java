package org.homedev.prototypes.utils;

import java.util.concurrent.ThreadLocalRandom;

public class InnGenerator {
    public static String generate16RegionInn() {
        String prefix = "1656";
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return prefix + random.nextInt(10000000, 99999999);
    }
}
