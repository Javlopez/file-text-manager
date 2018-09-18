package com.jlopez.inputData;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        String choose;

        Scanner inputChoose = new Scanner(System.in);

        printer("Please choose your Test:");
        printer("1.- Reading by using InputStreamReader ");
        printer("2.- Reading by using Scanner");
        printer("3.- Adding information into a text");
        printer("4.- Read files");
        printer("5.- Exit");

        try {
            int option = inputChoose.nextInt();

            switch (option) {
                case 1:
                    usingInputStream();
                    break;
                case 2:
                    usingScanner();
                    break;
                case 3:
                    saveInFile();
                    break;
                case 4:
                    readFile();
                    break;
                default:
                    printer("Thanks for using our app");
                    printer("Exiting ....");
                    Thread.sleep(2000);
                    break;
            }
        } catch (InterruptedException ie) {
            printer("Exception in the sleep happened");
            ie.printStackTrace();
        }

    }

    public static void printer(String message) {
        System.out.println(message);
    }

    public static void printer(int message) {
        System.out.println(message);
    }

    public static void usingScanner(){
        printer("*******  You are using Scanner ******");
        printer("[Enter Z to exit]");
        String capture = null;
        Scanner scan = new Scanner(System.in);
        printer("Enter something...");
        capture = scan.nextLine();

        while(capture != null) {
            if(capture.equals("z")) {
                printer("Exiting......");
                break;
            }
            printer("Last data entered:" + capture);
            capture = scan.nextLine();
        }
    }

    public static void usingInputStream(){
        printer("****  You are using InputStreamReader ***");
        printer("[Enter Z to exit]");
        String capture;
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader bInput = new BufferedReader(input);

        try {
            System.out.println("Enter some data");

            capture = bInput.readLine();

            while (capture != null) {
                if(capture.equals("z")) {
                    printer("Exiting......");
                    break;
                }

                System.out.println("Data: " + capture);
                capture = bInput.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveInFile(){
        String fileName = null;
        String content = null;

        Scanner sf = new Scanner(System.in);

        printer("Please enter the file name to save:");
        fileName = sf.nextLine();

        try {
            PrintWriter filePointer = new PrintWriter(new FileWriter(fileName, true));
            printer("Enter the text that you need to write (use Z to stop the enter):");

            content = sf.nextLine();
            while (content != null) {
                if(content.equals("z")){
                    break;
                }
                filePointer.println(content);
                content = sf.nextLine();
            }
            filePointer.close();
        } catch (IOException ie) {
            printer("Something goes wrong during write the file check the stackTrace");
            ie.printStackTrace();
        }
    }

    public static void readFile(){

        Scanner scan = new Scanner(System.in);
        File[] files = new File(".").listFiles();


        if(files == null) {
            printer("There's no file in the folder");
            return;
        }

        for (File file:files) {
            printer(file.getName());
        }

        printer("Please put the name of file to read:");
        String fileName = scan.nextLine();

        try {
            BufferedReader readerPointer = new BufferedReader(new FileReader(fileName));
            String readLine;

            readLine = readerPointer.readLine();
            while (readLine != null) {
                printer(readLine);
                readLine = readerPointer.readLine();
            }

        } catch (FileNotFoundException fe) {
            printer("The file cannot be found");
            fe.printStackTrace();
        } catch (IOException ie) {
            printer("The file cannot be read");
            ie.printStackTrace();
        }

    }
}
