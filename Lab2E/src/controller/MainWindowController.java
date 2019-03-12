package controller;

import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainWindowController {

	@FXML
	private Label cashierNumLbl;
	@FXML
	private TextField cashierNumTxt;
	@FXML
	private Label shelfNumLbl;
	@FXML
	private TextField shelfNumTxt;
	@FXML
	private Button nextBtn;
	private int cashierNum = 0;
	private int shelfNum = 0;

	@FXML
	void nextBtnClicked(ActionEvent event) {

		// if (!cashierNumTxt.getText().isEmpty()) {

		// if (!shelfNumTxt.getText().isEmpty()) {

//					cashierNum = Integer.parseInt(cashierNumTxt.getText());
//					shelfNum = Integer.parseInt(shelfNumTxt.getText());
		FXMLLoader loader = new FXMLLoader(getClass().getResource(("/view/BasicInformationWindow.fxml")));
		try {
			Parent root = loader.load();
			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
			Scene popUpScene = new Scene(root);
			window.setScene(popUpScene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

//			} else {
//				JOptionPane.showMessageDialog(null, "Please insert the number of shelves", "Error",
//						JOptionPane.ERROR_MESSAGE);
//			}

//		} else {
//			JOptionPane.showMessageDialog(null, "Please insert the number of cashiers", "Error",
//					JOptionPane.ERROR_MESSAGE);
//		}

	}

}
