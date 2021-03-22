package org.example.reflection;

public class NewBook {

    public static String A = "A";

    private String b = "B";

    public NewBook() {

    }

    public NewBook(String b) {
        this.b = b;
    }

    public void c() {
        System.out.println("c");
    }

    public int d(int left, int right) {
        return left + right;
    }
}
