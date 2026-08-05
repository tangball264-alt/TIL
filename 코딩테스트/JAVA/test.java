import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class test {
    
    public static void main(String[] args) {
        String inputstr = "hello";
        int inputnum = 9;
        int[] inputarr = {1, 2, 3, 4, 5};
        test myTest = new test();

        int result = myTest.solution(inputnum);
        System.out.println("result: " + result);
    }
    public int solution(int n) {
        int answer = 0;
        for(int i = 1; i <= Math.sqrt(answer); i++) {
            if(n % i == 0) {
                answer += i;
            }
        }
        return answer;
    }
}
