/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navarone.core;

import com.navarone.exceptions.ItemNotRegisteredException;
import com.navarone.exceptions.MalformedInputFileException;
import com.navarone.exceptions.UncalculatedException;
import com.navarone.salestaxproblem.BasicGood;
import com.navarone.salestaxproblem.Rounder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Register registers each line from a file and produces an output line
 * in a static BUFFER.
 * 
 * @author Craig
 */
public class Register {
    
    private static final String IMPORTED        = "imported";
    private static final String OUTPUT          = "Output";
    private static final String SPACE           = " ";
    private static final String COLON           = ":";
    private static final String SALES_TAXES     = "Sales Taxes";
    private static final String TOTAL           = "Total";
    private static final String NEWLINE         = "\n";
    private static final StringBuffer BUFFER    = new StringBuffer();
    private static State state                  = null;
    private static Token token                  = null;
    
    /**
     * basicTaxRegister stores a value of whether basic tax is applicable
     * based on a key of item-name
     */
    private static final HashMap<String,Boolean> basicTaxRegister =
            new HashMap<>();
    
    //Arraylist of goods that will be initialized each time the INPUT N is 
    //received
    private static List<BasicGood> list;    
    private final String path;
    static{
        basicTaxRegister.put("book", false);
        basicTaxRegister.put("music CD", true);
        basicTaxRegister.put("chocolate bar", false);
        
        basicTaxRegister.put("box of chocolates", false);
        basicTaxRegister.put("imported box of chocolates", false);
        basicTaxRegister.put("bottle of perfume", true);
        basicTaxRegister.put("imported bottle of perfume", true);
        
        basicTaxRegister.put("packet of headache pills", false);
    }
    
    public Register(String arg) {
        this.path = arg;
    }
    /**
     * Main method
     * @param args, the file name is args[0]
     */
    public static void main(String[] args){        
        if(args.length != 1){
            System.out.println("Parser help"
                    + "\nPlease provide input file path as parameter");
            return;
        }
        Register register = new Register(args[0]);
        register.run();
    }

    /**
     * Method to add tax and total information to the BUFFER
     * 
     * @param runningTotalTax, the total tax so far 
     */
    private static void addTaxes(BigDecimal runningTotalTax){
        BigDecimal total;
        if(!runningTotalTax.equals(new BigDecimal(0))){
            runningTotalTax = Rounder.roundUp(runningTotalTax);
            BUFFER.append(SALES_TAXES + COLON + SPACE).append(runningTotalTax)
                    .append(NEWLINE);
            total = new BigDecimal(0);
            if(list != null){
                for(BasicGood bg : list){
                    total = total.add(bg.getPrice());
                }
            }
            BUFFER.append(TOTAL + COLON + SPACE).append(total.add(runningTotalTax))
                    .append(NEWLINE + NEWLINE);
        }
    }
    /**
     * Method to register a line and update the RunningTotalTax
     * 
     * @param line, the line to be parsed
     * @param runningTotalTax, the total Tax so far
     * @return runningTotal, the updated tax
     * @throws UncalculatedException 
     * @throws com.navarone.exceptions.MalformedInputFileException 
     * @throws com.navarone.exceptions.ItemNotRegisteredException 
     */
    public static BigDecimal register(String line, BigDecimal runningTotalTax)
            throws UncalculatedException, MalformedInputFileException, 
                ItemNotRegisteredException {        
        //Matches Input N:
        Pattern p = Pattern.compile("Input\\s(\\d):");
        Matcher m = p.matcher(line);
        String number, count, item, amount;
        if(m.matches()){            
            token = Token.INPUT;
            state = StateMachine.transit(state, token);            
            addTaxes(runningTotalTax);
            number = m.group(1);
            BUFFER.append(OUTPUT + SPACE).append(number).append(COLON + NEWLINE);
            list = new ArrayList<>();
            runningTotalTax = new BigDecimal(0);
            return runningTotalTax;
        }
        
        //first imported goods
        // 1 imported box of chocolates at 0.01
        p = Pattern.compile("(\\d)\\simported\\s([\\w\\s]+)\\sat\\s([\\w.]+)");
        m = p.matcher(line);
        if(m.matches()){                    
            token = Token.ITEM;
            state = StateMachine.transit(state, token);
            count = m.group(1);
            item = IMPORTED + SPACE + m.group(2);
            amount = m.group(3);
            runningTotalTax = createNewItem(runningTotalTax,count, item, amount, true);
            return runningTotalTax;
        }
        
        //other version of imported
        //1 box of imported chocolates at 0.01
        //Need to do string manipulation to get desired output
        p = Pattern.compile("(\\d)\\s([\\w\\s]+\\simported\\s[\\w]+)\\sat\\s([\\w.]+)");
        m = p.matcher(line);
        if(m.matches()){                    
            token = Token.ITEM;
            state = StateMachine.transit(state, token);
            count = m.group(1);
            item = m.group(2);
            item = item.replace(IMPORTED + SPACE, "");
            item = IMPORTED + SPACE + item;
            amount = m.group(3);
            runningTotalTax = createNewItem(runningTotalTax,count, item, amount, true);
            return runningTotalTax;
        }
        
        //then non-imported
        //1 box of chocolates at 0.01
        p = Pattern.compile("(\\d)\\s([\\w\\s]+)\\sat\\s([\\w.]+)");
        m = p.matcher(line);
        if(m.matches()){
            token = Token.ITEM;
            state = StateMachine.transit(state, token);
            count = m.group(1);
            item = m.group(2);
            amount = m.group(3);
            runningTotalTax = createNewItem(runningTotalTax,count, item, amount, false);
            return runningTotalTax;
        }
        
        //Error, we should never reach here
        token = Token.ERROR;
        state = StateMachine.transit(state, token);
        return runningTotalTax;
    }   
    /**
     * Creates a new item and adds it to the list
     * 
     * @param runningTotalTax The total tax so far
     * @param count The number of items
     * @param item The item name
     * @param amount The cost of the item
     * @param imported Whether imported
     * @return the running total tax
     * @throws UncalculatedException 
     * @throws ItemNotRegisteredException 
     */
    private static BigDecimal createNewItem(BigDecimal runningTotalTax, 
            String count, String item, String amount, boolean imported)
            throws UncalculatedException, ItemNotRegisteredException {
        
        if(basicTaxRegister.get(item) == null)
            throw new ItemNotRegisteredException("The item "+ item +" has not been registered");
        
        MathContext mc = new MathContext(4);        
        BasicGood good = new BasicGood(item,new BigDecimal(amount),
                basicTaxRegister.get(item), imported);
        runningTotalTax = runningTotalTax.add(good.getTotalTax()
                .multiply(new BigDecimal(count), mc));
        list.add(good);
        
        BigDecimal result = good.getTotalTax().multiply(new BigDecimal(count), mc);
        BigDecimal result2 = good.getPrice().multiply(new BigDecimal(count), mc); 
        result = result2.add(result, mc);
        BUFFER.append(count).append(SPACE).append(item).append(COLON + SPACE)
                .append(result).append(NEWLINE);
        return runningTotalTax;
    }
    
    /**
     * The run method reads from a file and constructs the output
     * And prints the buffer to System.out
     */
    public void run() {
        FileReader file;
        BufferedReader buffer;
        String line;
        BigDecimal runningTotalTax;
        
        try{
            file            = new FileReader(this.path);
            buffer          = new BufferedReader(file);
            runningTotalTax = new BigDecimal(0);
            state           = State.INIT;
            
            while((line = buffer.readLine()) != null){
                if(!line.isEmpty()){
                    runningTotalTax = register(line, runningTotalTax);
                }
            }
            /*The below line would not be necessary if we had an end-of-file 
            marker in the input file.*/
            addTaxes(runningTotalTax);
            state = State.EOF;
            System.out.println(BUFFER.toString());
            
        } catch (FileNotFoundException ex) {
            state = State.ERROR;
            System.err.println("File not found. Please try again."
                + ex.getLocalizedMessage());            
        } catch (IOException io) {
            state = State.ERROR;
            System.err.println("IO Exception " + io.getLocalizedMessage());
        } catch(UncalculatedException | MalformedInputFileException 
                |ItemNotRegisteredException ex) {
            state = State.ERROR;
            System.err.println(ex.getLocalizedMessage());
        }
    }
    /**
     * Returns the current state
     * @return 
     */
    public static State getState(){
        return state;
    }
    /**
     * Returns the current buffer
     * @return 
     */
    public static String getBuffer(){
        return BUFFER.toString();
    }
}
