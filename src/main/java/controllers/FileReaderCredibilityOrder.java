package controllers;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import sets.CredibilityBase;

import java.io.*;

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
            if(br.readLine().equals("credibility_order;context")) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    int context;
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
                        int morePriorAgent;
                        int lessPriorAgent;
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
            return false;
        }

        return true;
    }

    public static void writeCredibilityBaseFromData(String folderPath, String name, SimpleDirectedGraph<Integer, DefaultEdge> graph){
        String fileName = name+".csv";
        String filePath = folderPath+"\\"+fileName;

        createFile(filePath, graph);
    }

    private static void createFile(String filePath, SimpleDirectedGraph<Integer, DefaultEdge> graph){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("credibility_order;context");
            writer.newLine();

            // write data
            for(DefaultEdge edge: graph.edgeSet()){
                writer.write(graph.getEdgeTarget(edge)+">"+graph.getEdgeSource(edge)+";1");
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
