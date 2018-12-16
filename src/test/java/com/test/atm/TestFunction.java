package com.test.atm;

import org.junit.Test;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.math.BigDecimal;

public class TestFunction {
    @Test
    public void mod() {
        System.out.println(70 % 100);
    }

    @Test
    public void numberToWord() {
        MoneyConverters converters  = MoneyConverters.ENGLISH_BANKING_MONEY_VALUE;
        String word = converters.asWords(BigDecimal.valueOf(2000));
        System.out.println(word.substring(0,word.indexOf("£")));


    }

}
