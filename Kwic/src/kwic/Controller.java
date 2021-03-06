package kwic;

import javafx.scene.control.TextArea;

/*
This class is the main guts of the program it uses the UI Class to be able to 
display the output. It sends the input to the input reader which then sends its data to the lineProcessor 
The line processor then sends the data to the circular shifter which then sends its data to the alphabetizer 
The data is then sent to the output writer. 
*/
public class Controller {
    
    UI ui;

    public Controller(UI ui) {
        this.ui = ui;
    }
    
    public void processCircularShift(String input, TextArea output) {
        long startTime = System.nanoTime();
        //process input
        InputReader inputReader = new InputReader(input);
        //use inputreaders data to process the lines
        LineProcessor lineProcessor = new LineProcessor(inputReader);
        lineProcessor.setup();
        //shift the words in the lines
        CircularShifter circularShifter = new CircularShifter(lineProcessor);
        circularShifter.constructCircularShifts();
        //aphabetize the ines 
        Alphabetizer alphabetizer = new Alphabetizer(circularShifter);
        alphabetizer.sortCircularShifts();
        //output the alphabetized lines 
        OutputWriter outputWriter = new OutputWriter( alphabetizer.getSortedShifts(), output);
        outputWriter.outputToText();
        System.out.println("Total Time " + (System.nanoTime()-startTime));
    }

}
