package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Client;
import model.Store;

public class MainWindowController {

	@FXML
	private Button exploreBtn;
	@FXML
	private TextField routeTxt;
	@FXML
	private Button goBtn;
	private FileChooser fc = new FileChooser();
	private int numOfCases = 0;
	private Store store;
	private int storesDone = 0;
	private int numOfCashiers = 0;
	private int numOfShelves = 0;
	private String outputContent = "";
	private ArrayList<Store> stores;

	@FXML
	void exploreBtnClicked(ActionEvent event) {

		File file = fc.showOpenDialog((Stage) (((Node) event.getSource()).getScene().getWindow()));
		readFile(file);
		routeTxt.setText(file.getAbsolutePath().toString());

	}

	public void readFile(File file) {

		String fileName = file.getName();
		if (fileName.contains(".txt")) {

			try {

				FileInputStream fis = new FileInputStream(file);
				BufferedReader buff = new BufferedReader(new InputStreamReader(fis));
				numOfCases = Integer.parseInt(buff.readLine());
				stores = new ArrayList<Store>();
				for (int i = 0; i < numOfCases; i++) {

					numOfCashiers = Integer.parseInt(buff.readLine());
					Store cStore = new Store(numOfCashiers, this);
					stores.add(cStore);
					numOfShelves = Integer.parseInt(buff.readLine());
					for (int j = 0; j < numOfShelves; j++) {

						String[] infoShelf = buff.readLine().split(" ");
						String id = infoShelf[0];
						int bookNum = Integer.parseInt(infoShelf[1]);
						cStore.addShelf(id, bookNum);
						for (int k = 0; k < bookNum; k++) {

							String[] infoBook = buff.readLine().split(" ");
							String isbn = infoBook[0];
							double price = Double.parseDouble(infoBook[1]);
							int units = Integer.parseInt(infoBook[2]);
							cStore.addBook(isbn, price, units, j);

						}

					}
					int clientNum = Integer.parseInt(buff.readLine());
					cStore.setClientsSize(clientNum);
					for (int j = 0; j < clientNum; j++) {

						String[] infoClient = buff.readLine().split(" ");
						cStore.addClient(infoClient[0], j + 1);

						for (int k = 1; k <= infoClient.length - 1; k++) {
							cStore.addBookToCart(infoClient[k], j);
						}
					}
					cStore.insertClientsIntoQueue();
					cStore.startThreads();

				}

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error reading the file", "Error", JOptionPane.ERROR_MESSAGE);
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

	public void addToOutput() {

		for (int i = 0; i < stores.size(); i++) {
			outputContent += stores.get(i).getClientsData() + "\n";
			System.out.println(stores.get(i).getClientsData());
		}
		printOutput();

	}

	public void storeDone() {

		storesDone++;
		if (storesDone == stores.size()) {
			addToOutput();
		}

	}

	public void printOutput() {

		try {
			File output = new File("output.txt");
			PrintWriter pr = new PrintWriter(output);
			pr.println(outputContent);
			pr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
