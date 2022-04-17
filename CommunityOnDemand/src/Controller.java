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
import javafx.scene.shape.Circle;
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
    private Button playSkillGame;
    private Button instructions;
    private int currCardIndex = 0;
    private Button skillsResults;
    private int numUsed = 0;
    private int numNotUsed = 0;
    private int numNotHave = 0;


    public void start(Stage primaryStage) {
        constructWelcome();
        Scene scene = new Scene(background, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Community on Demand");
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
            constructDashBoard();
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

    public void constructDashBoard() {
        advanceToDashBoard.setOnAction(e -> {
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset


            VBox welcomeText = new VBox();

            Text welcomeName = new Text();
            welcomeName.setText("Welcome");
            welcomeName.setFont(new Font("Poppins", 36));
            welcomeName.setStroke(Color.BLACK);
            welcomeName.setFill(Color.WHITE);


            Text welcomeNames = new Text();
            welcomeNames.setText(name.getText() + "!");
            welcomeNames.setFont(new Font("Poppins", 36));
            welcomeNames.setStroke(Color.BLACK);
            welcomeNames.setFill(Color.WHITE);

            welcomeText.getChildren().addAll(welcomeName, welcomeNames);

            welcomePane.setTop(welcomeText);
            welcomePane.setMargin(welcomeText, new Insets(50.0, 0.0, 30.0, 0.0));
            welcomePane.setAlignment(welcomeName, Pos.CENTER);
            welcomePane.setAlignment(welcomeNames, Pos.CENTER);
            welcomeText.setAlignment(Pos.CENTER);


            VBox buttons = new VBox(15.0);
            playSkillGame = new Button("Card Skills Assessment");
            playSkillGame.setFont(new Font("Poppins", 17));
            playSkillGame.setStyle("-fx-background-color: #C1D1A6; -fx-border-color: black;");
            playSkillGame.setMaxWidth(200.0);
            playSkillGame.setPrefHeight(50.0);

            instructions = new Button("How to Play");
            instructions.setFont(new Font("Poppins", 17));
            instructions.setStyle("-fx-background-color: #C1D1A6; -fx-border-color: black;");
            instructions.setMaxWidth(200.0);
            instructions.setPrefHeight(50.0);

            welcomePane.setAlignment(instructions, Pos.CENTER);
            welcomePane.setAlignment(playSkillGame, Pos.CENTER);


            buttons.getChildren().addAll(instructions, playSkillGame);
            welcomePane.setCenter(buttons);
            buttons.setAlignment(Pos.CENTER);
            welcomePane.setMargin(playSkillGame, new Insets(30.0, 0.0, 50.0, 0.0));
            background.getChildren().add(welcomePane);
            cardGame();
            instructionRead();
        });
    }



    public void instructionRead() {
        instructions.setOnAction(e -> {
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset
            Text textInstructions = new Text();
            textInstructions.setText("During the skills assessment,you will \nswipe through cards"
                    + " that fall within \nfive achievement domains:\n" +
                            "1. Career Awareness\n" +
                            "2. Innovation\n" +
                            "3. Workforce Ready\n" +
                            "4. STEAM Careers\n" +
                            "5. Leadership\n \n" +
                    "If you have the skill" +
                    " and currently use it,\n" +
                    "press the green button.\n\n" +
                    "If you have the skill," +
                    " but do not use it,\n press" +
                    " the yellow button.\n\n" +
                            "If you do not have the skill,\n" +
                            "press the red button");
            textInstructions.setFont(new Font("Poppins", 14));
            textInstructions.setFill(Color.BLACK);
            textInstructions.setStroke(Color.BLACK);
            welcomePane.setCenter(textInstructions);
            welcomePane.setBottom(playSkillGame);
            playSkillGame.setPrefHeight(50.0);
            playSkillGame.setPrefWidth(70.0);
            playSkillGame.setText("Play Game!");

            Text text = new Text("How to Play:");
            text.setUnderline(true);
            text.setFont(new Font("Poppins", 36));
            text.setStroke(Color.BLACK);
            text.setFill(Color.WHITE);
            welcomePane.setAlignment(text, Pos.CENTER);
            welcomePane.setTop(text);
            welcomePane.setMargin(text, new Insets(25.0, 0.0, 20, 0.0));
            welcomePane.setAlignment(playSkillGame, Pos.CENTER);
            cardGame();
            background.getChildren().add(welcomePane);
        });
    }


    public void cardGame() {
        playSkillGame.setOnAction(e -> {
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset

            StackPane good = new StackPane();
            Circle have = new Circle(30, Color.GREEN);
            Text x = new Text("x");
            x.setFill(Color.BLACK);
            good.getChildren().addAll(have, x);

            Text x1 = new Text("x");
            StackPane bad = new StackPane();
            Circle haveNot = new Circle(30, Color.RED);
            bad.getChildren().addAll(haveNot, x1);

            Text x2 = new Text("x");
            StackPane yellow = new StackPane();
            Circle noUse = new Circle(30, Color.YELLOW);
            yellow.getChildren().addAll(noUse, x2);

            x.setFont(new Font("Poppins", 20));
            x1.setFont(new Font("Poppins", 20));
            x2.setFont(new Font("Poppins", 20));

            yellow.setOnMouseClicked(l -> {
                if (currCardIndex < STEAMCards.steam.size()) {
                    numNotUsed++;
                }
                switchCard();
            });
            bad.setOnMouseClicked(k -> {
                if (currCardIndex < STEAMCards.steam.size()) {
                    numNotHave++;
                }
                switchCard();
            });
            good.setOnMouseClicked(f -> {
                if (currCardIndex < STEAMCards.steam.size()) {
                    numUsed++;
                }
                switchCard();
            });

            HBox buttonOptions = new HBox(50.0);
            buttonOptions.getChildren().addAll(bad, yellow, good);
            welcomePane.setBottom(buttonOptions);
            buttonOptions.setAlignment(Pos.CENTER);

            if (currCardIndex < STEAMCards.steam.size()) {
                ImageView card = STEAMCards.steam.get(currCardIndex);
                currCardIndex++;
                welcomePane.setCenter(card);
                welcomePane.setAlignment(card, Pos.CENTER);
                card.setPreserveRatio(true);
                card.setFitHeight(330);
                card.setFitWidth(250);
            }
            background.getChildren().add(welcomePane);
            welcomePane.setMargin(buttonOptions, new Insets(0.0, 0.0, 20, 0.0));

        });

    }

    public void switchCard() {
        if (currCardIndex < STEAMCards.steam.size()) {
            ImageView card = STEAMCards.steam.get(currCardIndex);
            currCardIndex++;
            welcomePane.setCenter(card);
            welcomePane.setAlignment(card, Pos.CENTER);
            card.setPreserveRatio(true);
            card.setFitHeight(330);
            card.setFitWidth(250);
        } else {
            Text noMore = new Text("No more\ncards left!");
            noMore.setFont(new Font("Poppins", 36));
            welcomePane.setCenter(noMore);
            welcomePane.setAlignment(noMore, Pos.CENTER);
            noMore.setFill(Color.WHITE);
            noMore.setStroke(Color.BLACK);
            skillsResults = new Button("See Skill Report");
            skillsResults.setStyle("-fx-background-color: #C1D1A6; -fx-border-color: black;");
            skillsResults.setFont(new Font("Poppins", 17));
            skillsResults.setPrefWidth(200);
            skillsResults.setPrefHeight(100);
            welcomePane.setBottom(skillsResults);
            welcomePane.setMargin(skillsResults, new Insets(0.0, 0.0, 20, 0.0));
            welcomePane.setAlignment(skillsResults, Pos.CENTER);
            genSkillsReport();
        }
    }

    public void genSkillsReport() {
        skillsResults.setOnAction(e -> {
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset

            Text skill = new Text("Skills Report: ");
            int totalCards = numNotHave + numUsed + numNotUsed;
            Text report = new Text("You currently have " + numUsed +
                    "\nSTEAM Career skills.\n\n" +
                    "You currently have but\n" +
                    "do not use " + numNotUsed + " STEAM\n" +
                    "Career skills\n\n" +
                    "You currently do not have\n" +
                    numNotHave + " STEAM Career Skills.\n\n");
            Text competency = new Text("Overall STEAM \nCareer " +
                    "Competency: " + (numUsed / totalCards));
            skill.setFill(Color.WHITE);
            skill.setUnderline(true);
            skill.setStroke(Color.BLACK);
            skill.setFont(new Font("Poppins", 36));

            report.setFill(Color.WHITE);
            report.setFont(new Font("Poppins", 15));

            competency.setFill(Color.BLACK);
            competency.setStroke(Color.BLACK);
            competency.setFont(new Font("Poppins", 25));

            welcomePane.setTop(skill);
            welcomePane.setCenter(report);
            welcomePane.setBottom(competency);
            welcomePane.setAlignment(skill, Pos.CENTER);
            welcomePane.setAlignment(competency, Pos.CENTER);
            welcomePane.setAlignment(report, Pos.CENTER);
            welcomePane.setMargin(competency, new Insets(0.0, 0.0, 20, 0.0));
            welcomePane.setMargin(skill, new Insets(50.0, 0.0, 30.0, 0.0));
            background.getChildren().add(welcomePane);
        });
    }
}
