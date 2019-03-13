package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindowController {

	@FXML
	private Button exploreBtn;

	@FXML
	private TextField routeTxt;

	@FXML
	private Button goBtn;
	private FileChooser fc = new FileChooser();
	private int numOfCases = 0;

	@FXML
	void exploreBtnClicked(ActionEvent event) {

		File file = fc.showOpenDialog((Stage) (((Node) event.getSource()).getScene().getWindow()));
		readFile(file);
		System.out.println(file.getAbsolutePath().toString());

	}

	@FXML
	void goBtnClicked(ActionEvent event) {

	}

	public void readFile(File file) {

		String fileName = file.getName();
		if (fileName.contains(".txt")) {

			try {

				FileInputStream fis = new FileInputStream(file);
				BufferedReader buff = new BufferedReader(new InputStreamReader(fis));
				numOfCases = Integer.parseInt(buff.readLine());
				for (int i = 0; i < numOfCases; i++) {
					
				}

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error readign the file", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "The first line of the file must be a number", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error readign the file", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
