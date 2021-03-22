package org.example.reflection;

@MyAnnotation("hello")
public class Book {

    @MyAnnotation(name = "field1", value = "value1")
    private static String B = "BOOK1";

    private static final String C = "BOOK2";

    private String a;

    public String d = "d";

    protected String e = "e";

    public Book() {

    }

    // Compile Error!
    // @MyAnnotation
    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("f");
    }

    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }
}
