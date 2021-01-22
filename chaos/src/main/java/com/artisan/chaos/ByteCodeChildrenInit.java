package com.artisan.chaos;

public class ByteCodeChildrenInit extends ByteCodeSuperInit {

    private String name;

    private String address;

    public ByteCodeChildrenInit(String name, int age, byte gender, String address) {
        super(name, age, gender);
        this.name = name;
        this.address = address;
    }

    public static ByteCodeSuperInit test() {
        ByteCodeChildrenInit bci = new ByteCodeChildrenInit("xz man", 18, (byte) 1, "外太空");
        bci.name = bci.name + "genius";
        return bci;
    }

    public static void main(String[] args) {
        ByteCodeChildrenInit.test();
    }
}
