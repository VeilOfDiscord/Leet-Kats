//Nathaell Avril Leman
//COP 3503 - Kattis Problem 2
//Solution to Dance Recital 
//2/12/2022

import java.util.*;
import java.io.*;

public class RP2 {
    private static String[] routines;
    private static int[] order;
    private static int r;
    public static void main(String[] args) throws IOException{
        HashMap<ArrayList<Integer>,Integer> map = new HashMap<ArrayList<Integer>, Integer>();
        RP2 recital = new RP2();
        recital.similar(map);
        // System.out.println(r);
        int min = recital.permute(map, order, 0,r-1,26);
        System.out.println(min);
        // System.out.println("Fin");
    }
    public RP2() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        r = Integer.parseInt(input);

        routines = new String[r];
        order = new int[r];
        for(int i = 0; i < r; i++){
            routines[i] = reader.readLine();
            order[i] = i;
        }
    }
    public int similarUtil(String a, String b){
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < a.length(); i++){
            for(int j = 0; j <b.length(); j++){
                if(a.charAt(i) == b.charAt(j)){
                    set.add(i);
                }
            }
        }
        return set.size();
    }
    public void similar(HashMap<ArrayList<Integer>,Integer> map){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < r; j++){

                //Indexes of the strings.
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(i);
                list.add(j);
                //Number of similarities 
                int value = similarUtil(routines[i], routines[j]);
                map.put(list, value);
            }
        }
    }
    public int findMin(HashMap<ArrayList<Integer>,Integer> set, int[] str){
        int max = 0;
        for(int i = 1; i < str.length; i++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(str[i-1]);
            list.add(str[i]);
            int temp = set.get(list);
            max += temp;
        }
        return max;
    }
    public int permute(HashMap<ArrayList<Integer>,Integer> map, int[] str, int l,int r, int min){
        if(l == r){
            int temp = findMin(map, str);
            if(temp < min)
                min = temp;
            // System.out.println(str[0]+" "+str[1]+" "+str[2]+" "+str[3]);
        }
        else{
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                min = permute(map, str, l+1,r,min);
                str = swap(str,l,i);
            }
        }
        return min;
    }
    public int[] swap(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return a;
    }
}