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
	private int numOfCashiers = 0;
	private int numOfShelves = 0;

	@FXML
	void exploreBtnClicked(ActionEvent event) {

		File file = fc.showOpenDialog((Stage) (((Node) event.getSource()).getScene().getWindow()));
		readFile(file);
		routeTxt.setText(file.getAbsolutePath().toString());

	}

	public void readFile(File file) {

		File output = new File("output.txt");
		if (output.exists()) {
			PrintWriter writer;
			try {
				writer = new PrintWriter(output);
				writer.print("");
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		String fileName = file.getName();
		if (fileName.contains(".txt")) {

			try {

				FileInputStream fis = new FileInputStream(file);
				BufferedReader buff = new BufferedReader(new InputStreamReader(fis));
				numOfCases = Integer.parseInt(buff.readLine());
				for (int i = 0; i < numOfCases; i++) {

					numOfCashiers = Integer.parseInt(buff.readLine());
					store = new Store(numOfCashiers, this);
					numOfShelves = Integer.parseInt(buff.readLine());
					for (int j = 0; j < numOfShelves; j++) {

						String[] infoShelf = buff.readLine().split(" ");
						String id = infoShelf[0];
						int bookNum = Integer.parseInt(infoShelf[1]);
						store.addShelf(id, bookNum);
						for (int k = 0; k < bookNum; k++) {

							String[] infoBook = buff.readLine().split(" ");
							String isbn = infoBook[0];
							double price = Double.parseDouble(infoBook[1]);
							int units = Integer.parseInt(infoBook[2]);
							store.addBook(isbn, price, units, j);

						}

					}
					int clientNum = Integer.parseInt(buff.readLine());
					store.setClientsSize(clientNum);
					for (int j = 0; j < clientNum; j++) {

						String[] infoClient = buff.readLine().split(" ");
						store.addClient(infoClient[0], j + 1);

						for (int k = 1; k <= infoClient.length - 1; k++) {
							store.addBookToCart(infoClient[k], j);
						}
					}
					startCheckOut();

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

	public void startCheckOut() {

		store.insertClientsIntoQueue();
		store.startThreads();

	}

	public void printOutput() {

		try {

			FileWriter fr = new FileWriter("output.txt", true);
			BufferedWriter bw = new BufferedWriter(fr);
			PrintWriter pr = new PrintWriter(bw);
			ArrayList<Client> clients = store.getClientsExit();
			for (int i = 0; i < clients.size(); i++) {
				pr.println(clients.get(i).getId() + " " + store.getClientValue(i));
				pr.println(store.getClientCart(i));
			}
			pr.println();
			pr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
