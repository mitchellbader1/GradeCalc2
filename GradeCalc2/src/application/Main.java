package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {
	int categoryCount = 1;
	static ArrayList<TextField> gradesText = new ArrayList<TextField>();
	static ArrayList<Double> grades = new ArrayList<Double>();
	public void start(Stage primaryStage) {
		try {
			//Creating the root BorderPane
			BorderPane root = new BorderPane();

			//Defining the Font Sizes
			Font smallFont = new Font(12);
			Font mediumFont = new Font(18);
			Font largeFont = new Font(24);

			//Right Half

			Label gpaLabel = new Label("GPA: N/A");
			gpaLabel.setFont(largeFont);

			//Left Half

				//Creating the contents of checkListUI
				Label addClass = new Label("Add a Class:");
				addClass.setFont(largeFont);
				Button addCategory = new Button("Add a Class");
				Button doneClass = new Button("Calculate GPA");
				HBox firstCategory = addClassCategory(categoryCount);
				categoryCount++;
				//Adding the buttons and label to an HBox
				HBox topCheckUI = new HBox(addClass, addCategory, doneClass);
				topCheckUI.setSpacing(25);

				//Creating the checkListUI VBox
				VBox checkListUI = new VBox();
				checkListUI.getChildren().add(topCheckUI);
				checkListUI.getChildren().add(firstCategory);
				checkListUI.setPadding(new Insets(10));

				//Creating the addCategory listener
				addCategory.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						checkListUI.getChildren().add(addClassCategory(categoryCount));
						categoryCount++;
					}
				});

				//Creating the doneClass listener
				doneClass.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						//Put everything on its respective stack
							for(int i = 0; i < categoryCount-1; i++)
							{
								double parsedGrade = Double.parseDouble(gradesText.get(i).getText());
								grades.add(parsedGrade);
							}
						//Calculate GPA
							double GPASum = 0;
							for(int i = 0; i < grades.size(); i++)
							{
								GPASum += getGPA(grades.get(i));
							}
							double avgGPA = GPASum/grades.size();
							gpaLabel.setText("GPA: " + avgGPA);
					}
				});


			//Adding each element of the UI to the BorderPane
			root.setLeft(checkListUI);
			root.setRight(gpaLabel);


			//Finalizing scene and stage
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Grade Calculator");
			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	private static HBox addClassCategory(int i)
	{
		//Defining the Font Sizes
		Font mediumFont = new Font(18);

		Label catNum = new Label(i + "");
		catNum.setFont(mediumFont);
		TextField gradeBox = new TextField("Enter % grade in class");
		gradesText.add(gradeBox);
		HBox toDisplay = new HBox(catNum, gradeBox);
		toDisplay.setPadding(new Insets(3));
		toDisplay.setSpacing(10);
		return toDisplay;
	}
	private static double getGPA(double doub)
	{
		if(doub >= 90)
		{
			return 4;
		}
		else if(doub >= 80)
		{
			return 3;
		}
		else if(doub >= 70)
		{
			return 2;
		}
		else if(doub >= 60)
		{
			return 1;
		}
		else return 0;
	}
}