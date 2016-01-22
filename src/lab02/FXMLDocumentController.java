/*
 * 
 */
package lab02;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Mark Hall
 * @author Thomas Spencer
 *
 */
public class FXMLDocumentController implements Initializable {

    // @FXML connects each textarea or button with the code.  It is very useful.
    @FXML
    private TextArea inputPane;   // Input text panel
    @FXML
    private TextArea outputPane;  // Output text panel
    
    /**
     * HANDLE BROWSE
     *  When the button "Browse" is clicked, the user will be able to find 
     * the file on his computer.  This will then be displayed in the "inputPane"
     * pane.
     * 
     * @param event 
     */
    @FXML
    private void handleBrowse(ActionEvent event) {
        outputPane.setText("Click the 'Process' button below to process "
                + "machine code!");

        //Create a file chooser, which will browse for a file.
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show load file dialog
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            ParseFile fileParser = new ParseFile(file.getAbsolutePath());
            String[] arrayList = fileParser.getArray();
            for (String singleLine : arrayList) {
                inputPane.appendText(singleLine + "\n");
            }
        }
    }

    @FXML
    private void handleProcess(ActionEvent event) {

        // First, we create an arrayList of strings
        ArrayList<String> outputArray = new ArrayList<>();

        // Then, we have to parse the Text Area into an array of Strings
        for (String line : inputPane.getText().split("\\n")) {
            outputArray.add(line);
        }

        // After that, we have to clear out any previous text.
        outputPane.clear();

        // Finally, we send it back to ParseFile for processing, then put it
        // back into the output field.
        ParseFile parse = new ParseFile();
        String[] arrayList = parse.createOutput(outputArray);
        for (String singleLine : arrayList) {
            outputPane.appendText(singleLine + "\n");
        }

    }

    @FXML
    private void handleSave(ActionEvent event) {

        ArrayList<String> toFile = new ArrayList<>();

        toFile.addAll(Arrays.asList(outputPane.getText().split("\\n")));

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show save file dialog
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                for (String fileLine : toFile) {
                    out.println(fileLine + "\n");
                }
                out.close();
                System.out.println(outputPane.getText());
                System.out.println(file.getAbsoluteFile());
                Desktop.getDesktop().open(new File(file.getParent()));

            } catch (IOException ex) {
                System.out.println("Error writing file!");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputPane.setText("Click the 'Browse' button above to "
                + "select a file, OR delete this line of text and begin typing!");
        inputPane.setWrapText(true);
        outputPane.setWrapText(true);
    }

}
