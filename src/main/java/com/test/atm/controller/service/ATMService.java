package com.test.atm.controller.service;

import com.test.atm.dto.Account;
import com.test.atm.dto.Database;
import com.test.atm.exception.WithdrawException;
import org.springframework.stereotype.Component;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.math.BigDecimal;
import java.util.logging.Logger;

@Component
public class ATMService {
    private Logger logger = Logger.getLogger(getClass().getName());
    private MoneyConverters converters = MoneyConverters.ENGLISH_BANKING_MONEY_VALUE;

    public BigDecimal calSum(Account account) {
        logger.info("#calsum# : " + account);
        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(calByEachType(BigDecimal.valueOf(20), account.getBanknotes_20()));
        sum = sum.add(calByEachType(BigDecimal.valueOf(50), account.getBanknotes_50()));
        sum = sum.add(calByEachType(BigDecimal.valueOf(100), account.getBanknotes_100()));
        sum = sum.add(calByEachType(BigDecimal.valueOf(500), account.getBanknotes_500()));
        sum = sum.add(calByEachType(BigDecimal.valueOf(1000), account.getBanknotes_1000()));
        logger.info("#result  : " + sum);
        return sum;
    }

    public BigDecimal calByEachType(BigDecimal value, BigDecimal amount) {
        return value.multiply(amount);
    }


    public void withdraw(BigDecimal withdraw) {
        logger.info("#withdraw  : " + withdraw);

        if (Database.account.getBalance().compareTo(withdraw) >= 0) {
            try {
                Account account = new Account(Database.account); //backup
                Account useNotes = new Account();
                logger.info(account.toString());

                String withdrawStr = withdraw.toString();

                for (int i = 0; i < withdrawStr.length(); i++) {

                    String notesStr = String.valueOf(withdrawStr.charAt(i));
                    for (int j = 0; j < (withdrawStr.length() - 1) - i; j++) {
                        notesStr += 0;
                    }

                    BigDecimal notes = BigDecimal.valueOf(Long.parseLong(notesStr));
                    if (!notes.equals(BigDecimal.ZERO)) {
                        logger.info("-->" + notesStr);

                        withdrawProcess(account, useNotes, notes);

                    }
                }

                Database.account = account;
                Database.account.setBalance(Database.account.getBalance().subtract(withdraw));
                Database.msg = getMsg(withdraw, useNotes);
            } catch (WithdrawException e) {
                Database.msg = "it is not possible to dispense ฿ " + withdraw + " .";
                logger.info(Database.msg);
            }

        } else {
            logger.info("Your money is not enough.");
        }
    }

    private String getMsg(BigDecimal withdraw, Account useNotes) {
        StringBuilder sb = new StringBuilder().append("dispense ฿ ").append(withdraw).append(". by ");
        if (!useNotes.getBanknotes_1000().equals(BigDecimal.ZERO)) {
            sb.append(getWord(useNotes.getBanknotes_1000())).append(" ฿ 1000 notes, ");
        }
        if (!useNotes.getBanknotes_500().equals(BigDecimal.ZERO)) {
            sb.append(getWord(useNotes.getBanknotes_500())).append(" ฿ 500 notes, ");
        }
        if (!useNotes.getBanknotes_100().equals(BigDecimal.ZERO)) {
            sb.append(getWord(useNotes.getBanknotes_100())).append(" ฿ 100 notes, ");
        }
        if (!useNotes.getBanknotes_50().equals(BigDecimal.ZERO)) {
            sb.append(getWord(useNotes.getBanknotes_50())).append(" ฿ 50 notes, ");
        }
        if (!useNotes.getBanknotes_20().equals(BigDecimal.ZERO)) {
            sb.append(getWord(useNotes.getBanknotes_20())).append(" ฿ 20 notes");
        }

        String msg = sb.toString();
        if (msg.endsWith(", ")) {
            msg.substring(0, msg.length() - 3);
        }
        return msg;
    }

    private String getWord(BigDecimal useNotes) {
        String word = converters.asWords(useNotes);
        word=   word.substring(0, word.indexOf("£"));
        return word;
    }


    private void withdrawProcess(Account account, Account useNotes, BigDecimal notes) throws WithdrawException {


        if (isRemainNotSame(notes, 1000) && isEnough(notes, 1000, account.getBanknotes_1000())) {

            logger.info("is more than num");
            int decreaseAmount = getDecreaseAmount(notes, 1000);
            logger.info("decreaseAmount : " + decreaseAmount);
            account.setBanknotes_1000(decreaseAmount(decreaseAmount, account.getBanknotes_1000()));
            useNotes.setBanknotes_1000(BigDecimal.valueOf(decreaseAmount));
            logger.info("Banknotes_1000 remain : " + account.getBanknotes_1000());
            notes = notes.subtract(BigDecimal.valueOf(1000 * decreaseAmount));


        } else if (isRemainNotSame(notes, 500) && isEnough(notes, 500, account.getBanknotes_500())) {
            logger.info("Banknotes_500");

            logger.info("is more than num");
            int decreaseAmount = getDecreaseAmount(notes, 500);
            logger.info("decreaseAmount : " + decreaseAmount);
            account.setBanknotes_500(decreaseAmount(decreaseAmount, account.getBanknotes_500()));
            useNotes.setBanknotes_500(BigDecimal.valueOf(decreaseAmount));
            logger.info("Banknotes_500 remain : " + account.getBanknotes_500());
            notes = notes.subtract(BigDecimal.valueOf(500 * decreaseAmount));

        } else if (isRemainNotSame(notes, 100) && isEnough(notes, 100, account.getBanknotes_100())) {

            logger.info("is more than num");
            int decreaseAmount = getDecreaseAmount(notes, 100);
            logger.info("decreaseAmount : " + decreaseAmount);
            account.setBanknotes_100(decreaseAmount(decreaseAmount, account.getBanknotes_100()));
            useNotes.setBanknotes_100(BigDecimal.valueOf(decreaseAmount));
            logger.info("Banknotes_100 remain : " + account.getBanknotes_100());
            notes = notes.subtract(BigDecimal.valueOf(100 * decreaseAmount));

        } else if (isRemainNotSame(notes, 50) && isEnough(notes, 50, account.getBanknotes_50())) {

            logger.info("is more than num");
            int decreaseAmount = getDecreaseAmount(notes, 50);
            logger.info("decreaseAmount : " + decreaseAmount);
            account.setBanknotes_50(decreaseAmount(decreaseAmount, account.getBanknotes_50()));
            useNotes.setBanknotes_50(BigDecimal.valueOf(decreaseAmount));
            logger.info("Banknotes_50 remain : " + account.getBanknotes_50());
            notes = notes.subtract(BigDecimal.valueOf(50 * decreaseAmount));

        } else if (isRemainNotSame(notes, 20) && isEnough(notes, 20, account.getBanknotes_20())) {

            logger.info("is more than num");
            int decreaseAmount = getDecreaseAmount(notes, 20);
            logger.info("decreaseAmount : " + decreaseAmount);
            account.setBanknotes_20(decreaseAmount(decreaseAmount, account.getBanknotes_20()));
            useNotes.setBanknotes_20(BigDecimal.valueOf(decreaseAmount));
            logger.info("Banknotes_20 remain : " + account.getBanknotes_20());
            notes = notes.subtract(BigDecimal.valueOf(20 * decreaseAmount));

        } else {
            throw new WithdrawException();
        }


        if (!notes.equals(BigDecimal.ZERO)) {
            withdrawProcess(account, useNotes, notes);
        }

    }

    private BigDecimal decreaseAmount(int decreaseAmount, BigDecimal banknotes) {
        return banknotes.subtract(BigDecimal.valueOf(decreaseAmount));
    }

    private int getDecreaseAmount(BigDecimal notes, int i) {
        return notes.divide(BigDecimal.valueOf(i)).intValue();
    }

    private boolean isRemainNotSame(BigDecimal notes, int i) {
        return !notes.remainder(BigDecimal.valueOf(i)).equals(notes);
    }

    private boolean isEnough(BigDecimal notes, int i, BigDecimal banknotes) {
        return BigDecimal.valueOf(i).multiply(banknotes).compareTo(notes) >= 0;
    }

}
