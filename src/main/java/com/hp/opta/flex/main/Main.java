package com.hp.opta.flex.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.hp.opta.flex.antlr.parser.impl.ParserImpl;
import com.hp.opta.flex.configuration.EventParsingData;
import com.hp.opta.flex.configuration.EventParsingDataHolder;
import com.hp.opta.flex.configuration.model.ConfigurationMetaData;
import com.hp.opta.flex.execution.ParsingExcecutioner;


/**
 * The Class Main.
 *
 * @author Yoav Nordmann
 * @since Apr 17, 2016
 */
public class Main {
	
    /** The out. */
    @Option(name="-o",usage="output to this file",metaVar="OUTPUT", required=true)
    private String out = new String("output.txt");	
    
    /** The conf. */
    @Option(name="-c",usage="configuration file",metaVar="CONFIG", required=true)
    private String conf;	
    
    /** The in. */
    @Option(name="-i",usage="event input file",metaVar="INPUT", required=true)
    private String in;	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the FlexEngine Parser");
		new Main().runProgram(args);
	}
	
	
    /**
     * Run program.
     *
     * @param args the args
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void runProgram(String[] args) throws IOException {
        
    	CmdLineParser parser = new CmdLineParser(this);

        try {
            // parse the arguments.
            parser.parseArgument(args);
            
            System.out.println("Reading Configuration");
            
            String allData = readAllLines(conf);
            
            ConfigurationMetaData metaData = new ParserImpl().parse(allData);
            EventParsingData data = EventParsingDataHolder.publish("main", metaData);
            ParsingExcecutioner.publish("main", data);

            System.out.println("Reading Input");
            String line = this.readInputLine(this.in);
            
            System.out.println("Executing Parsing");
            Object result = ParsingExcecutioner.parse("main", line);

            System.out.println("Writing Output");
            writeOutputLine(result, out);
            
            System.out.println("done.");
            
            
            
        } catch(CmdLineException exception) {
            // if there's a problem in the command line,you'll get this exception. 
        	// this will report an error message.
            System.err.println(exception.getMessage());
            System.err.println("java -jar FlexEngine-XYZ.jar [options...] arguments...");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            return;
        }

    }

    
    /**
     * Read input line.
     *
     * @param input the input
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private String readInputLine(String input) throws IOException{
    	List<String> lines = Files.readAllLines(Paths.get(input));
    	return lines.get(0);
    }
    
    
    private String readAllLines(String input) throws IOException{
    	
    	StringBuilder strBuilder = new StringBuilder();
    	
    	Files.readAllLines(Paths.get(input)).forEach((line) -> strBuilder.append(line).append("\n"));
    	
    	return strBuilder.toString();
    }
    
    
    
    /**
     * Write output line.
     *
     * @param result the result
     * @param output the output
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void writeOutputLine(Object result, String output) throws IOException{
    	
    	List<String> data =  Arrays.asList(new String[]{String.valueOf(result),"\n"});
    	
    	Files.write(Paths.get(output), data, 
    			StandardOpenOption.CREATE, 
    			StandardOpenOption.WRITE, 
    			StandardOpenOption.APPEND);
    	
    }

}
