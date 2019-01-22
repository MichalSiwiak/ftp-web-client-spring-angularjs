package net.coffeecoding;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException {
      /*  PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();*/

        File file = new File("test123.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("dupa");
        writer.close();

    }
}
