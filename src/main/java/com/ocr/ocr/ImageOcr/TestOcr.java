package com.ocr.ocr.ImageOcr;

import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class TestOcr {

    public static void main(String[] args) {
        TestOcr testOcr = new TestOcr();
        String text = testOcr.testOcr();
        System.out.printf(text);
    }

    private String testOcr() {
        try {
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Users\\DELL\\Desktop\\software\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
            String text=tesseract.doOCR(new File("C:\\Users\\DELL\\Desktop\\software\\Tess4J-3.4.8-src\\Tess4J\\plate_1.jpg"));
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }
}
