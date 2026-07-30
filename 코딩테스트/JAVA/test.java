import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class test {
    
    public static void main(String[] args) {
        int[] inputarr = {1, 2, 3, 4, 5};
        test myTest = new test();

        int[] result = myTest.solution(inputarr);
        System.out.println("result: " + Arrays.toString(result));
    }
    public int[] solution(int[] num_list) {
        for(int i = 0; i < num_list.length; i++) {
            num_list[i] = num_list[i]%2==0?1:0;
        }
        return {num_list.}
    }
}
