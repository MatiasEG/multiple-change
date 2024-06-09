package controllers;

import sets.CredibilityBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderCredibilityOrder {

    public static CredibilityBase<Integer,Integer> processCredibilityBaseFromFile(String filePathA, String filePathB){
        CredibilityBase<Integer, Integer> cb = new CredibilityBase<Integer, Integer>();
        if(readFile(filePathA, cb)){
            if(!readFile(filePathB, cb)){
                return null;
            }
        }else{
            return null;
        }
        return cb;
    }

    private static boolean readFile(String csvFile, CredibilityBase<Integer, Integer> cb){
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            if(br.readLine().equals("credibility_order")) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    int context = 0;
                    if(parts.length == 2){
                        try{
                            context = Integer.parseInt(parts[1]);
                        }catch(NumberFormatException e){
                            return false;
                        }
                    }else{
                        // File with wrong syntax
                        return false;
                    }

                    String[] prior = parts[0].split(">");
                    if (prior.length == 2) {
                        int morePriorAgent = 0;
                        int lessPriorAgent = 0;
                        try{
                            morePriorAgent = Integer.parseInt(prior[0].trim().toLowerCase());
                            lessPriorAgent = Integer.parseInt(prior[1].trim().toLowerCase());
                        }catch(NumberFormatException e){
                            return false;
                        }

                        cb.addCredibilityObject(morePriorAgent, lessPriorAgent, context);

                    }else {
                        // File with wrong syntax
                        return false;
                    }
                }
            }else {
                // File with wrong syntax
                return false;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
