package com.ocr.ocr.util;

public class AmountCalculator {

    public static int getAmountByReason(String reason) {
        switch (reason) {
            case "Wrong Parking":
                return 500;
            case "No Helmet":
                return 1000;
            default:
                return -1;
        }
    }
}
