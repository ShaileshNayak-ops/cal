package com.shailesh.cal;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;

public class Controller {
    private String dir;
    public Controller(String dir) {
        this.dir = dir;
    }
    public void listLicenses() {
        File dir = new File(this.dir);
        FileFilter fileFilter = new WildcardFileFilter("*.txt");
        File[] files = dir.listFiles(fileFilter);
        for(int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }
    public String createLicense(String name, String year, String project, String org) throws IOException{
        InputStream in = new FileInputStream(String.format("%s/%s.txt", this.dir, name));
        String pFp = IOUtils.toString(in, "UTF-8");

        return pFp.replaceAll("year", year).replaceAll("organization", org).replaceAll("project", project);
    }
    public void writeToFile(File fp, String stream) {
        try {
            FileWriter writer = new FileWriter(fp);
            writer.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}