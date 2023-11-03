package com.course.CRUD.utils;

public class GenerateCode {
    public static int generateNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
