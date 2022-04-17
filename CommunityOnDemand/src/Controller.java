import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller extends Application {
    private BorderPane welcomePane = new BorderPane();
    private StackPane background = new StackPane();
    private BorderPane loginPane = new BorderPane();
    private Button login = new Button();
    private Button signin = new Button();


    public void start(Stage primaryStage) {
        constructSignUp();
        constructWelcome();
        Scene scene = new Scene(background, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public void constructWelcome() {
        background.getChildren().add(backgroundImg());
        VBox loginButtons = loginOrSignUp();
        loginButtons.setAlignment(Pos.CENTER);
        Text welcome = welcomeLabel();
        welcomePane.setAlignment(welcome, Pos.CENTER);
        welcomePane.setMargin(welcome, new Insets(50.0, 0.0, 50.0, 0.0));
        welcomePane.setTop(welcome);
        background.setAlignment(Pos.CENTER);
        welcomePane.setCenter(loginButtons);
        background.getChildren().add(welcomePane);
    }

    public ImageView backgroundImg() {
        Image image = new Image("./img/Background.png");
        ImageView view = new ImageView(image);
        return view;
    }

    public VBox loginOrSignUp() {
        VBox accountButtons = new VBox(20.0);
        login = new Button("Log in");
        signin = new Button("Sign up");
        login.setFont(new Font("Poppins", 17));
        signin.setFont(new Font("Poppins", 17));
        login.setStyle("-fx-background-color: #C1D1A6; -fx-border-color: black;");
        signin.setStyle("-fx-background-color: #C1D1A6; -fx-border-color: black;");
        login.setPrefWidth(100.0);
        login.setPrefHeight(50.0);
        signin.setPrefWidth(100.0);
        signin.setPrefHeight(50.0);
        accountButtons.getChildren().addAll(signin, login);

        return accountButtons;
    }

    public Text welcomeLabel() {
        Text welcome = new Text("Community\non Demand");
        welcome.setStroke(Color.BLACK);
        welcome.setFont(new Font("Poppins", 40));
        welcome.setFill(Color.WHITE);
        return welcome;
    }

    public void constructSignUp() {
        signin.setOnAction(e -> {
            background = new StackPane(); // reset
            welcomePane = new BorderPane(); // reset


            Text signUp = new Text("Sign Up");
            signUp.setStroke(Color.BLACK);
            signUp.setFont(new Font("Poppins", 40));
            signUp.setFill(Color.WHITE);


            welcomePane.setTop(signUp);
            welcomePane.setAlignment(signUp, Pos.CENTER);
            welcomePane.setMargin(signUp, new Insets(50.0, 0.0, 50.0, 0.0));
            background.getChildren().add(welcomePane);
        });

    }


}
