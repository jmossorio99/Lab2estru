package controller;

import java.io.File;

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

	@FXML
	void exploreBtnClicked(ActionEvent event) {

		File file = fc.showOpenDialog((Stage) (((Node) event.getSource()).getScene().getWindow()));
		System.out.println(file.getAbsolutePath().toString());

	}

	@FXML
	void goBtnClicked(ActionEvent event) {

	}

}
