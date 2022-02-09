package com.shailesh.cal;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public class Application {
    public static void main(String[] argv) {
        if(System.getenv("LICENSE_DIR") == null) {
            System.out.println("[Error]: The environment variable LICENSE_DIR was not set");
            System.exit(1);
        }else {

            Controller ctrl = new Controller(System.getenv("LICENSE_DIR"));
            List<String> args = new LinkedList<String>();
            for(String arg : argv) {
                args.add(arg);
            }
            if(args.contains("--list")) {
                ctrl.listLicenses();
            } else if(args.contains("--help")) {
                System.out.println("Create A License");
                System.out.println("Useage: ");
                System.out.println("cal --list");
                System.out.println("cal <license-name> <year> <project> <organization>");
                System.out.println("cal <license-name> <year> <project> <organization> --out <file>");
            } else if(args.contains("--create")) {
                try {
                   Integer idx = args.indexOf("--create");
                   String lName = args.get(idx+1);
                   String lYear = args.get(idx+2);
                   String lProject = args.get(idx+3);
                   String lOrg = args.get(idx+4);
                   try {
                       String lic = ctrl.createLicense(lName, lYear, lProject, lOrg);
                       if(args.contains("--out")) {
                           try {
                               String file = args.get(args.indexOf("--out")+1);
                               File fp = new File(file);
                               ctrl.writeToFile(fp, lic);

                           } catch(IndexOutOfBoundsException e) {
                               System.out.println("[Error]: Arguments not supplied correctly");
                               e.printStackTrace();
                           }
                       }else {
                           System.out.println(lic);
                       }
                   }catch(IOException e) {
                       System.out.println(String.format("The license %s is not available", lName));
                       e.printStackTrace();
                   }
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("[Error]: Arguments not supplied correctly");
                    e.printStackTrace();
                }
            }else {
                System.out.println("Wrong Method");
                System.out.println("Try typing --help");
            }
        }
    }
}