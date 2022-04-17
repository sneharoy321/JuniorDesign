import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField email = new TextField();
    private TextField name = new TextField();
    private TextField password = new TextField();
    private String nameOfUser;
    private Button advanceToDashBoard;



    public void start(Stage primaryStage) {
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
        constructSignUp();
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
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset

            Text signUp = new Text("Sign Up");  // sign up text and placement
            signUp.setStroke(Color.BLACK);
            signUp.setFont(new Font("Poppins", 40));
            signUp.setFill(Color.WHITE);
            welcomePane.setTop(signUp);
            welcomePane.setAlignment(signUp, Pos.CENTER);
            welcomePane.setMargin(signUp, new Insets(50.0, 0.0, 30.0, 0.0));

            VBox collectiveInformation = new VBox(30.0);
            collectiveInformation.getChildren().addAll(nameTextField(),
                    emailTextField(), passwordTextField());
            collectiveInformation.setAlignment(Pos.CENTER);

            welcomePane.setCenter(collectiveInformation);


            advanceDashboard();
            welcomePane.setBottom(advanceToDashBoard);
            welcomePane.setMargin(advanceToDashBoard, new Insets(30.0, 0.0, 50.0, 0.0));

            background.getChildren().add(welcomePane);
        });
    }

    public void advanceDashboard() {
        advanceToDashBoard = new Button("Sign up");
        advanceToDashBoard.setStyle("-fx-background-color: #C1D1A6; -fx-border-color: black;");
        advanceToDashBoard.setFont(new Font("Poppins", 17));
        advanceToDashBoard.setPrefWidth(100.0);
        advanceToDashBoard.setPrefHeight(50.0);
        BorderPane.setAlignment(advanceToDashBoard, Pos.CENTER);
    }


    private VBox emailTextField() {
        VBox emails = new VBox(10.0);
        Text emailHere = new Text("Your email");
        emailHere.setFont(new Font("Poppins", 17));
        emailHere.setFill(Color.WHITE);
        email.setMaxWidth(200.0);
        email.setStyle("-fx-border-color: black;");
        emails.getChildren().addAll(emailHere, email);
        emails.setAlignment(Pos.CENTER);
        return emails;
    }

    private VBox nameTextField() {
        VBox names = new VBox(10.0);
        Text nameHere = new Text("Your name");
        nameHere.setFont(new Font("Poppins", 17));
        nameHere.setFill(Color.WHITE);
        name.setMaxWidth(200.0);
        name.setStyle("-fx-border-color: black;");
        names.getChildren().addAll(nameHere, name);
        names.setAlignment(Pos.CENTER);
        return names;
    }

    private VBox passwordTextField() {
        VBox passwords = new VBox(10.0);
        Text passwordHere = new Text("Your password");
        passwordHere.setFont(new Font("Poppins", 17));
        passwordHere.setFill(Color.WHITE);
        password.setMaxWidth(200.0);
        password.setStyle("-fx-border-color: black;");
        passwords.getChildren().addAll(passwordHere, password);
        passwords.setAlignment(Pos.CENTER);
        return passwords;
    }
}
