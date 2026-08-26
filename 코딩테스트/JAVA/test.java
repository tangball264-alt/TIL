import java.util.*;

public class test {
    
    public static void main(String[] args) {
        String inputstr = "1a2b3c4d123Z";
        int inputnum = 9;
        int[][] inputarr = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}};
        test myTest = new test();

        int result = myTest.solution(inputarr);
        System.out.println("result: " + result);
    }



    public int solution(int[][] board) {
        int answer = 0;
        System.out.println("board: " + Arrays.deepToString(board));
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==1){
                    for(int x=Math.max(i-1,0);x<Math.min(board.length, i+2);x++){
                        for(int y=Math.max(j-1,0);y<Math.min(board[0].length, j+2);y++){
                            board[x][y] += 1;
                        }
                    }
                }
            }
        }
        System.out.println("board: " + Arrays.deepToString(board));
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==0){
                    answer ++;
                }
            }
        }
        return answer;
    }

}

1 1
진입
2 2
3 3
3 4
4 5
5 6
5 7
6 8
7 9
7 10
8 11
9 12
9 13
9 14
10 15


