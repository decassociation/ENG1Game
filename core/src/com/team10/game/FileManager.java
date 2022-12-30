package com.team10.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

/**
 * FileManager is a class for managing settings files for the game to make it easy to read and change settings both by
 * editing the files directly or through java
 * 
 * Files should be formatted such that each line is
 *      variableName=data
 */

public class FileManager {
    private File file;
    
    /**
     * Constructor for FileManager
     * 
     * @param filepath the path to the file
     * @throws IOException
     */
    public FileManager(String filepath){
        file = new File(filepath);
    }

    public FileManager(){
        String filepath = System.getenv("APPDATA") + "/PiazzaPanic/desktop_settings.txt";
        file = new File(filepath);
    }

    /**
     * Get the data associated with the given variable
     * 
     * @param variableName the part of the line before the = sign to look for
     * @return the part of the line after the = sign on the same line as variableName
     */
    public String read(String variableName){
        Scanner reader;
        try {
            reader = new Scanner(file);

            String line;
            String[] split;
            String data = null;

            // Iterate over each line, split over the = and check if the first part matches variableName
            while(reader.hasNextLine()){
                line = reader.nextLine();
                split = line.split("=");
                if(split[0].equals(variableName)){
                    data = split[1];
                }
            }
            reader.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        
    }


    /**
     * Write the data associated with the given variable name, if the variable does not exist then create it
     * 
     * @param variableName the part of the line before the = sign to look for
     * @param data the part of the line after the = sign to overwrite
     */
    public void write(String variableName, String data){
        String[] split;
        boolean added = false;

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

            // Iterate over each line, split over the = and check if the first part matches variable name
            for (int i = 0; i < lines.size(); i++) {
                split = lines.get(i).split("=");

                // If it matches, change the second part to data
                if(split[0].equals(variableName) && !added){
                    lines.set(i, split[0] + "=" + data);
                    added = true;
                };
            }

            if(!added){
                lines.add(variableName + "=" + data);
                added = true;
            }

            Files.write(file.toPath(), lines, StandardCharsets.UTF_8);  //Re-write every line back to the file
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
}
