package com.card.collector.backend.utility;

import java.util.Random;

public class Utility {

    public static int getRandomNumber(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }
}
