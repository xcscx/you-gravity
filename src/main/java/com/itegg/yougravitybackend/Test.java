package com.itegg.yougravitybackend;

public class Test {

    public static void main(String[] args) {
        int a = 10;
        int b = 15;
        System.out.println("数字a：" + a + "和数字b：" + b + "的最大公因数是：" + gcd(a, b));
    }

    public static int gcd(int a, int b) {
        a= Math.abs(a);
        b= Math.abs(b);

        while(b != 0) {
            int temp = b;
            System.out.println("temp = " + temp);
            b = a % b;
            System.out.println("b = " + b);
            a = temp;
            System.out.println("a = " + a);
        }
        return a;
    }

}
