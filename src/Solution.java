import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

class Directory{
    String path;
    Directory parent;
    ArrayList<Directory> children;
    String name ;

    Directory(String name, Directory father, String path){
        this.name = name;
        this.path = path;
        parent = father;
        this.children = new ArrayList<>();

    }
}

public class Solution {
    public static void main(String[] args) {

        Directory root = new Directory("C:\\", null, "C\\");
        Directory current_dir = root;

        if( current_dir.parent != null ){
            current_dir.path = current_dir.parent.path + "\\" + current_dir.name + "\\";
        }
        System.out.println(current_dir.path);

        Scanner sc1 = new Scanner(System.in);

        while (true) {
            String cmd1 = sc1.next();

            if (Objects.equals(cmd1, "pwd")) {
                System.out.println("Path");
                System.out.println("----");
                System.out.println("Current directory: " + current_dir.path  + ">");
            }

            if (Objects.equals(cmd1, "mkdir")) {
                String str = sc1.next();

                Directory new_dir = new Directory(str, current_dir, current_dir.path + "\\" + str);

                System.out.println("Current directory: " + current_dir.path);
                current_dir.children.add(new_dir);
                System.out.println("New Folder: " + str);
            }

            if (Objects.equals(cmd1, "ls")) {
                System.out.println("Current directory: " + current_dir.path);
                for (Directory child : current_dir.children) {
                    System.out.println(child.name);
                }
            }
            if (Objects.equals(cmd1, "cd")) {
                String str = sc1.next();
                boolean found = false;

                for (Directory child : current_dir.children){
                    if ( Objects.equals(child.name, str)) {
                        current_dir = child;
                        found= true;
                        break;
                    }
                }

                if (found) {
                    System.out.println("Changed dir to: " + current_dir.path);
                } else {
                    System.out.println("Folder does not exist. ");
                }

            if (Objects.equals(cmd1, "cd ..")) {

                if (current_dir.parent != null) {
                    current_dir = current_dir.parent;
                    System.out.println("Changed dir to : " + current_dir.path);
                } else {
                    System.out.println("ROOT");
                }
                }
            }
        }
    }
}
