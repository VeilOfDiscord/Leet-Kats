import java.util.*;
import java.io.*;
public class cd2 {
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String first = br.readLine();
        String[] jj = first.split(" ");

        int n = Integer.parseInt(jj[0]);
        int m = Integer.parseInt(jj[1]);

        while(n != 0 && m != 0){
            HashSet<Integer> map = new HashSet<Integer>();
            int dupes = 0;
            for(int i = 0; i < n+m; i ++){
                int cd = 0;
                String input = br.readLine();
                String[] piece = input.split(" ");
                for(int j = 0; j < piece.length; j++){
                    try {cd = Integer.parseInt(piece[j]);} 
                    catch (Exception e) {System.out.println("Not Integer");}
                }
                if(map.contains(cd)){dupes += 1;}
                else{map.add(cd);}
            }
            System.out.println(dupes);
            first = br.readLine();
            jj = first.split(" ");
    
            n = Integer.parseInt(jj[0]);
            m = Integer.parseInt(jj[1]);
                map = new HashSet<>();
        }
        br.close();
    } 
}
