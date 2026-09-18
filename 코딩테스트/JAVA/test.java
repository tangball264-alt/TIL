import java.util.*;

public class test {
    
    public static void main(String[] args) {
        test myTest = new test();

        int result = myTest.solution(new String[]{"ayaye", "uuuma", "ye", "yemawoo", "ayaa"});
        System.out.println("result: " + result);
    }


    public int solution(String[] babbling) {
        /*
        문자열 배열에서, 넷 중 하나와 일치하는 값 찾기.
        */
        int answer = 0;
        for(String s : babbling){
            int n = 0;
            System.out.println("s: " + s);
            switch(s){
                case "aya" : n += 3; System.out.println("aya");
                case "ye" : n += 2; System.out.println("ye");
                case "woo" : n += 3; System.out.println("woo");
                case "ma" : n += 2; System.out.println("ma");
            }
            if(n == s.length()){
                System.out.println("n : " + n + ", s.length() : " + s.length());
                answer++;
            }
        }
        return answer;
    }


}



