package controllers;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import sets.CredibilityBase;

import java.io.*;
import java.util.List;

public class FileReaderCredibilityOrder {

    public static CredibilityBase<Integer,Integer> processCredibilityBaseFromFile(String filePathA, String filePathB){
        CredibilityBase<Integer, Integer> cb = new CredibilityBase<Integer, Integer>();
        if(readFile(filePathA, cb)){
            if(filePathB!=null && !readFile(filePathB, cb)){
                return null;
            }
        }else{
            return null;
        }
        return cb;
    }

    private static boolean readFile(String csvFile, CredibilityBase<Integer, Integer> cb){
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            if(br.readLine().equals("context;credibility_order")) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    int context;
                    if(parts.length == 2){
                        try{
                            context = Integer.parseInt(parts[0]);
                        }catch(NumberFormatException e){
                            return false;
                        }
                    }else{
                        // File with wrong syntax
                        return false;
                    }

                    String[] prior = parts[1].split(",");
                    for(String p: prior){
                        String[] credibilityElement = p.split(">");
                        if (credibilityElement.length == 2) {
                            int morePriorAgent;
                            int lessPriorAgent;
                            try{
                                morePriorAgent = Integer.parseInt(credibilityElement[0].trim().toLowerCase());
                                lessPriorAgent = Integer.parseInt(credibilityElement[1].trim().toLowerCase());
                            }catch(NumberFormatException e){
                                return false;
                            }

                            cb.addCredibilityObject(morePriorAgent, lessPriorAgent, context);

                        }else {
                            // File with wrong syntax
                            return false;
                        }
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

    public static void writeCredibilityBaseFromData(String folderPath, String name, List<SimpleDirectedGraph<Integer, DefaultEdge>> listGraph){
        String fileName = name+".csv";
        String filePath = folderPath+"\\"+fileName;

        createFile(filePath, listGraph);
    }

    private static void createFile(String filePath, List<SimpleDirectedGraph<Integer, DefaultEdge>> listGraph){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("context;credibility_order");
            writer.newLine();

            int context = 1;
            for(SimpleDirectedGraph<Integer, DefaultEdge> graph : listGraph){
                // write data
                writer.write(context+";");
                for(DefaultEdge edge: graph.edgeSet()){
                    writer.write(graph.getEdgeTarget(edge)+">"+graph.getEdgeSource(edge));
                    writer.write(",");
                }
                writer.newLine();
                context++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
