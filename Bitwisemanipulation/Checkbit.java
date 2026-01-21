package Bitwisemanipulation;

import java.util.*;


public class Checkbit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String bitNumber = convertDecimaltoBit(n);
        int decimalNumber = convertBitToDecimal(bitNumber);


        System.out.println(bitNumber+"heellooo");
        System.out.println(decimalNumber);

        swapIntegers(5, 6);

        boolean flag=checkNthBitSet(13, 1);

       

    
    }

    private static String convertDecimaltoBit(int n) {
        String s = "";

        while(n>1){
            s = String.valueOf(n%2) + s;
            n/=2; 
        }

        s = String.valueOf(n) + s;

        return s;
    }

    private static int convertBitToDecimal(String bitNumber) {
        int n=bitNumber.length();
        int ans=0;
        for(int i=0;i<n;i++){
            if(bitNumber.charAt(i)=='1')   ans+=Math.pow(2, n-1-i);
            
        }
        return  ans;
    }

    private static void swapIntegers(int a,int b){
        System.out.println("Original a : "+a+" and Original b : "+b);
        a = a^b;
        b = a^b;
        a= a^b;
        System.out.println("Now a = "+a+" b = "+b);
    }


    public static boolean checkNthBitSet(int n,int k){
        int val=1<<k;
        if((n&val)!=0){
            return true;
        }
        System.out.println("hello");
        return false;
    }

    public static int setNthBit(int n,int k){
        int ans = (n | (1<<k));
        return  ans;
    }

    

    
    
}
