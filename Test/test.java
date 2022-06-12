import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException{
        String line;

        Scanner Obj = new Scanner(System.in);
        line = Obj.nextLine();
        if (line.isEmpty()) System.out.println("rong roi");

        /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while ((line = br.readLine()) != null){
            if (line.isEmpty()) System.out.println("rong roi");
            else System.out.println(line);
        } */

    }
}
