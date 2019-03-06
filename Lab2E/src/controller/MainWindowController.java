package controller;

import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	private boolean popped = false;

	@FXML
	void nextBtnClicked(ActionEvent event) {

		//if (!cashierNumTxt.getText().isEmpty()) {

			//if (!shelfNumTxt.getText().isEmpty()) {

				if (!popped) {

//					cashierNum = Integer.parseInt(cashierNumTxt.getText());
//					shelfNum = Integer.parseInt(shelfNumTxt.getText());
					FXMLLoader loader = new FXMLLoader(getClass().getResource(("/view/PopUpWindowView.fxml")));
					try {
						Parent root = loader.load();
						Stage popUp = new Stage();
						Scene popUpScene = new Scene(root);
						popUp.setScene(popUpScene);
						popUp.show();
						popped = true;
					} catch (IOException e) {
						e.printStackTrace();
					}
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
