import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class test {
    
    public static void main(String[] args) {
        String inputstr = "1 2 Z 3";
        int inputnum = 9;
        int[] inputarr = {1, 2, 3, 4, 5};
        test myTest = new test();

        int result = myTest.solution(inputstr);
        System.out.println("result: " + result);
    }


    public int solution(String s) {
        /*일단 할 일 : 공백 기준 split
        그러면 1, 2, Z, 3이 나와.
        그 중 Z의 인덱스를 찾아야 해.
        그러면 역순으로. 진행.*/
        String[] str = s.split(" ");
        int answer = 0;
        for(int i=0;i>str.length;i++){
            if(str[i].equals("Z")){
                answer -= Integer.parseInt(str[i-1]);
            }else{
                answer += Integer.parseInt(str[i]);
            }
            
        }
        return answer;
    }


}
