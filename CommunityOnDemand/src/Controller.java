import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Locale;

/**
 * Community on Demand Application.
 * Virtual version of physical card game.
 */
public class Controller extends Application {
    private BorderPane welcomePane = new BorderPane();
    private StackPane background = new StackPane();
    private BorderPane loginPane = new BorderPane();
    private Button login = new Button();
    private Button signin = new Button();
    private TextField email = new TextField();
    private TextField name = new TextField();
    private PasswordField password = new PasswordField();
    private String nameOfUser;
    private Button advanceToDashBoard;
    private Button playSkillGame;
    private Button instructions;
    private int currCardIndex = 0;
    private Button skillsResults;
    private int numUsed = 0;
    private int numNotUsed = 0;
    private int numNotHave = 0;
    private ImageView backImg;
    private ProgressBar progress = new ProgressBar(0);

    /**
     * Starts the application
     * @param primaryStage stage that shows the scene.
     */
    public void start(Stage primaryStage) {
        constructWelcome();
        Scene scene = new Scene(background, 300, 500);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Community on Demand");
    }

    /**
     * Constructs the welcome stage of
     * the application.
     */
    public void constructWelcome() {
        backImg = backgroundImg();
        background.getChildren().add(backImg);
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
        constructLogIn();
    }

    /**
     * Constructs the background image.
     * @return An ImageView containing the background.
     */
    public ImageView backgroundImg() {
        Image image = new Image("./img/Background.png");
        ImageView view = new ImageView(image);
        return view;
    }

    /**
     * Constructs the login or sign up options.
     * @return VBox holding these buttons.
     */
    public VBox loginOrSignUp() {
        VBox accountButtons = new VBox(20.0);
        login = new Button("LOG IN");
        signin = new Button("SIGN UP");
        login.setFont(new Font("Poppins", 15));
        login.setTextFill(Color.WHITE);
        login.setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-background-color:transparent;-fx-font-weight:bold;");
        login.setEffect(new DropShadow());

        signin.setFont(new Font("Poppins", 15));
        signin.setTextFill(Color.WHITE);
        signin.setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-background-color:transparent;-fx-font-weight:bold;");
        signin.setEffect(new DropShadow());

        login.setOnMouseEntered(e -> {
                login.setStyle("-fx-background-color:#dae7f3;");
        });

        login.setOnMouseExited(e -> {
            login.setStyle("-fx-border-color: white; -fx-border-width:2;" +
                    "-fx-background-color:transparent;-fx-font-weight:bold;");
            login.setTextFill(Color.WHITE);
        });

        signin.setOnMouseEntered(e -> {
            signin.setStyle("-fx-background-color:#dae7f3;");
        });

        signin.setOnMouseExited(e -> {
            signin.setStyle("-fx-border-color: white; -fx-border-width: 2;" +
                    "-fx-background-color:transparent;-fx-font-weight:bold;");
            signin.setTextFill(Color.WHITE);
        });

        login.setPrefWidth(100.0);
        login.setPrefHeight(50.0);
        signin.setPrefWidth(100.0);
        signin.setPrefHeight(50.0);
        accountButtons.getChildren().addAll(signin, login);
        return accountButtons;
    }

    /**
     * Constructs the welcome label.
     * @return Returns the text holding the welcome.
     */
    public Text welcomeLabel() {
        Text welcome = new Text("COMMUNITY\nON DEMAND");
        welcome.setEffect(new DropShadow());
        welcome.setFont(new Font("Poppins", 40));
        welcome.setFill(Color.WHITE);
        return welcome;
    }

    /**
     * Constructs the sign up page.
     */
    public void constructSignUp() {
        signin.setOnAction(e -> {
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset
            Text signUp = new Text("SIGN UP");  // sign up text and placement
            //signUp.setStroke(Color.BLACK);
            signUp.setEffect(new DropShadow());
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

    /**
     * Constructs the log in page.
     */
    public void constructLogIn() {
        login.setOnAction(e -> {
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset
            Text signUp = new Text("LOG IN");  // sign up text and placement
            signUp.setFont(new Font("Poppins", 40));
            signUp.setFill(Color.WHITE);
            signUp.setEffect(new DropShadow());
            welcomePane.setTop(signUp);
            welcomePane.setAlignment(signUp, Pos.CENTER);
            welcomePane.setMargin(signUp, new Insets(50.0, 0.0, 30.0, 0.0));

            VBox collectiveInformation = new VBox(30.0);
            collectiveInformation.getChildren().addAll(emailTextField(), passwordTextField());
            collectiveInformation.setAlignment(Pos.CENTER);

            welcomePane.setCenter(collectiveInformation);

            advanceDashboard();
            welcomePane.setBottom(advanceToDashBoard);
            welcomePane.setMargin(advanceToDashBoard, new Insets(30.0, 0.0, 50.0, 0.0));

            background.getChildren().add(welcomePane);
            constructDashBoard();
        });
    }

    /**
     * Constructs the advance to dashboard
     * functionality and button.
     */
    public void advanceDashboard() {
        advanceToDashBoard = new Button("SIGN UP");
        advanceToDashBoard.setFont(new Font("Poppins", 15));
        advanceToDashBoard.setTextFill(Color.WHITE);
        advanceToDashBoard.setEffect(new DropShadow());
        advanceToDashBoard.setStyle("-fx-border-color: white; -fx-border-width: 1; -fx-background-color:transparent;-fx-font-weight:bold;");

        advanceToDashBoard.setOnMouseEntered(e -> {
            advanceToDashBoard.setStyle("-fx-background-color:#dae7f3;");
        });

        advanceToDashBoard.setOnMouseExited(e -> {
            advanceToDashBoard.setStyle("-fx-border-color: white; -fx-border-width: 2;" +
                    "-fx-background-color:transparent;-fx-font-weight:bold;");
            advanceToDashBoard.setTextFill(Color.WHITE);
        });
        advanceToDashBoard.setPrefWidth(100.0);
        advanceToDashBoard.setPrefHeight(50.0);
        BorderPane.setAlignment(advanceToDashBoard, Pos.CENTER);
    }

    /**
     * Constructs the email text field.
     * @return Text field for email entry.
     */
    private VBox emailTextField() {
        VBox emails = new VBox(10.0);
        Text emailHere = new Text("EMAIL");
        emailHere.setStyle("-fx-font-weight:bold;");
        emailHere.setFont(new Font("Poppins", 12));
        emailHere.setFill(Color.WHITE);
        email.setMaxWidth(200.0);
        email.setEffect(new DropShadow());
        emails.getChildren().addAll(emailHere, email);
        emails.setAlignment(Pos.CENTER);
        return emails;
    }

    /**
     * Constructs the name text field.
     * @return The field for the name.
     */
    private VBox nameTextField() {
        VBox names = new VBox(10.0);
        Text nameHere = new Text("YOUR NAME");
        nameHere.setStyle("-fx-font-weight: bold;");
        nameHere.setFont(new Font("Poppins", 12));
        nameHere.setFill(Color.WHITE);
        name.setMaxWidth(200.0);
        //name.setStyle("-fx-border-color: black;");
        name.setEffect(new DropShadow());
        names.getChildren().addAll(nameHere, name);
        names.setAlignment(Pos.CENTER);
        return names;
    }

    /**
     * Constructs the password text field.
     * @return The field for the password.
     */
    private VBox passwordTextField() {
        VBox passwords = new VBox(10.0);
        Text passwordHere = new Text("PASSWORD");
        passwordHere.setStyle("-fx-font-weight: bold;");
        passwordHere.setFont(new Font("Poppins", 12));
        passwordHere.setFill(Color.WHITE);
        password.setMaxWidth(200.0);
        //password.setStyle("-fx-border-color: black;");
        password.setEffect(new DropShadow());
        passwords.getChildren().addAll(passwordHere, password);
        passwords.setAlignment(Pos.CENTER);
        return passwords;
    }

    /**
     * Constructs the dashboard.
     */
    public void constructDashBoard() {
        advanceToDashBoard.setOnAction(e -> {
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset


            VBox welcomeText = new VBox();

            Text welcomeName = new Text();
            welcomeName.setText("WELCOME");
            welcomeName.setFont(new Font("Poppins", 36));
            welcomeName.setFill(Color.WHITE);
            welcomeName.setEffect(new DropShadow());

            Text welcomeNames = new Text();
            welcomeNames.setText(name.getText().toUpperCase(Locale.ROOT) + "!");
            welcomeNames.setFont(new Font("Poppins", 36));
            welcomeNames.setFill(Color.WHITE);
            welcomeNames.setEffect(new DropShadow());

            welcomeText.getChildren().addAll(welcomeName, welcomeNames);

            welcomePane.setTop(welcomeText);
            welcomePane.setMargin(welcomeText, new Insets(50.0, 0.0, 30.0, 0.0));
            welcomePane.setAlignment(welcomeName, Pos.CENTER);
            welcomePane.setAlignment(welcomeNames, Pos.CENTER);
            welcomeText.setAlignment(Pos.CENTER);


            VBox buttons = new VBox(15.0);
            playSkillGame = new Button("Card Skills Assessment");
            playSkillGame.setFont(new Font("Poppins", 17));

            playSkillGame.setMaxWidth(200.0);
            playSkillGame.setPrefHeight(50.0);

            playSkillGame.setFont(new Font("Poppins", 15));
            playSkillGame.setTextFill(Color.WHITE);
            playSkillGame.setEffect(new DropShadow());
            playSkillGame.setStyle("-fx-border-color: white; -fx-border-width: 1; -fx-background-color:transparent;-fx-font-weight:bold;");

            playSkillGame.setOnMouseEntered(f -> {
                playSkillGame.setStyle("-fx-background-color:#dae7f3;");
            });

            playSkillGame.setOnMouseExited(f -> {
                playSkillGame.setStyle("-fx-border-color: white; -fx-border-width: 2;" +
                        "-fx-background-color:transparent;-fx-font-weight:bold;");
                playSkillGame.setTextFill(Color.WHITE);
            });


            instructions = new Button("How to Play");
            instructions.setFont(new Font("Poppins", 17));

            instructions.setMaxWidth(200.0);
            instructions.setPrefHeight(50.0);

            instructions.setFont(new Font("Poppins", 15));
            instructions.setTextFill(Color.WHITE);
            instructions.setEffect(new DropShadow());
            instructions.setStyle("-fx-border-color: white; -fx-border-width: 1; -fx-background-color:transparent;-fx-font-weight:bold;");

            instructions.setOnMouseEntered(f -> {
                instructions.setStyle("-fx-background-color:#dae7f3;");
            });

            instructions.setOnMouseExited(f -> {
                instructions.setStyle("-fx-border-color: white; -fx-border-width: 2;" +
                        "-fx-background-color:transparent;-fx-font-weight:bold;");
                instructions.setTextFill(Color.WHITE);
            });


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

    /**
     * Constructs the instructions for the
     * card skills assessment.
     */
    public void instructionRead() {
        instructions.setOnAction(e -> {
            //background.getChildren().remove(backImg);
            //background.setStyle("-fx-background-color: gainsboro;");
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset
            Text textInstructions = new Text();
            textInstructions.setText("During the skills assessment, you will \nswipe through cards"
                    + " that fall within \nfive achievement domains:\n\n" +
                            "1. Career Awareness\n" +
                            "2. Innovation\n" +
                            "3. Workforce Ready\n" +
                            "4. STEAM Careers\n" +
                            "5. Leadership\n\n" + "If you have the skill" +
                    "\nand currently use it,\n" +
                    "press the green button.  \n\n" +"If you have the skill," +
                    "\nbut do not use it,   \npress" +
                    " the orange button. \n\n" +"If you do not have the \nskill " +
                            "press the red button");


            textInstructions.setFont(new Font("Poppins", 12));
            textInstructions.setFill(Color.BLACK);
            textInstructions.setStyle("-fx-font-weight:bold;");


            BorderPane.setAlignment(textInstructions, Pos.CENTER);


            welcomePane.setBottom(playSkillGame);
            playSkillGame.setPrefHeight(35.0);
            playSkillGame.setPrefWidth(70.0);
            playSkillGame.setText("Play Game!");


            Rectangle backgrounds = new Rectangle(250, 350, Color.WHITE);
            backgrounds.setStroke(Color.BLACK);
            StackPane stack = new StackPane();
            stack.getChildren().addAll(backgrounds, textInstructions);
            welcomePane.setCenter(stack);
            backgrounds.setEffect(new DropShadow());


            Text text = new Text("INSTRUCTIONS");
            text.setFont(new Font("Poppins", 35));
            text.setFill(Color.WHITE);
            text.setEffect(new DropShadow());
            text.setTextAlignment(TextAlignment.CENTER);
            welcomePane.setAlignment(text, Pos.CENTER);
            welcomePane.setTop(text);
            welcomePane.setMargin(text, new Insets(30.0, 0.0, 15, 0.0));
            welcomePane.setAlignment(playSkillGame, Pos.CENTER);
            cardGame();
            background.getChildren().add(welcomePane);
        });
    }


    /**
     * Constructs the card game.
     */
    public void cardGame() {
        playSkillGame.setOnAction(e -> {
            if (!background.getChildren().contains(backImg)) {
                background.getChildren().add(backImg);
            }
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset
            welcomePane.setTop(progress);
            BorderPane.setAlignment(progress, Pos.CENTER);
            progress.setPrefWidth(250);
            progress.setPrefHeight(15);
            progress.setStyle("-fx-accent: green;");
            progress.setEffect(new DropShadow());
            BorderPane.setMargin(progress, new Insets(10, 0, 0, 0));
            StackPane good = new StackPane();
            Circle have = new Circle(30, Color.GREEN);
            have.setStroke(Color.WHITE);
            have.setStrokeWidth(1.5);
            Text x = new Text("  Have\nand use");
            x.setFill(Color.BLACK);
            good.getChildren().addAll(have, x);
            good.setOnMouseEntered(f -> {
                have.setFill(Color.web("#dae7f3"));
            });

            good.setOnMouseExited(f -> {
                have.setFill(Color.GREEN);
            });


            Text x1 = new Text("Don't\nhave");
            StackPane bad = new StackPane();
            Circle haveNot = new Circle(30, Color.RED);
            haveNot.setStroke(Color.WHITE);
            bad.getChildren().addAll(haveNot, x1);
            bad.setOnMouseEntered(f -> {
                haveNot.setFill(Color.web("#dae7f3"));
            });

            bad.setOnMouseExited(f -> {
                haveNot.setFill(Color.RED);
            });
            haveNot.setStrokeWidth(1.5);


            Text x2 = new Text("Have but\ndon't use");
            StackPane yellow = new StackPane();
            Circle noUse = new Circle(30, Color.ORANGE);
            yellow.setOnMouseEntered(f -> {
                noUse.setFill(Color.web("#dae7f3"));
            });
            yellow.setOnMouseExited(f -> {
                noUse.setFill(Color.ORANGE);
            });
            noUse.setStroke(Color.WHITE);

            yellow.getChildren().addAll(noUse, x2);
            noUse.setStrokeWidth(1.5);
            x.setFont(new Font("Poppins", 12));
            x.setStyle("-fx-font-weight:bold;");
            x.setFill(Color.WHITE);
            x1.setFont(new Font("Poppins", 12));
            x1.setStyle("-fx-font-weight:bold;");
            x1.setFill(Color.WHITE);
            x2.setFont(new Font("Poppins", 12));
            x2.setStyle("-fx-font-weight:bold;");
            x2.setFill(Color.WHITE);

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
            HBox buttonOptions = new HBox(40.0);

            have.setEffect(new DropShadow());
            haveNot.setEffect(new DropShadow());
            noUse.setEffect(new DropShadow());

            VBox vertical = new VBox(15);
            Text forThis = new Text("For this project, this is a skill I:");
            forThis.setFill(Color.WHITE);
            forThis.setEffect(new DropShadow());
            forThis.setStyle("-fx-font-weight: bold;");
            forThis.setFont(new Font("Poppins", 12));
            vertical.setAlignment(Pos.CENTER);
            buttonOptions.getChildren().addAll(bad, yellow, good);
            vertical.getChildren().addAll(forThis, buttonOptions);
            welcomePane.setBottom(vertical);
            buttonOptions.setAlignment(Pos.TOP_CENTER);
            welcomePane.setMargin(forThis, new Insets(0, 0, 0, 0));

            if (currCardIndex < STEAMCards.steam.size()) {
                ImageView card = STEAMCards.steam.get(currCardIndex);
                card.setEffect(new DropShadow());
                currCardIndex++;
                welcomePane.setCenter(card);
                welcomePane.setAlignment(card, Pos.CENTER);
                card.setPreserveRatio(true);
                card.setFitHeight(330);
                welcomePane.setMargin(card, new Insets(0, 0, 0, 0));
                card.setFitWidth(250);
            }
            background.getChildren().add(welcomePane);
            welcomePane.setMargin(vertical, new Insets(0, 0, 0, 0));
            welcomePane.setMargin(buttonOptions, new Insets(0.0, 0.0, 20, 0.0));

        });

    }

    /**
     * Constructs switch card functionality.
     */
    public void switchCard() {
        progress.setProgress(currCardIndex / (double) STEAMCards.steam.size());
        if (currCardIndex < STEAMCards.steam.size()) {
            ImageView card = STEAMCards.steam.get(currCardIndex);
            card.setEffect(new DropShadow());
            currCardIndex++;
            welcomePane.setCenter(card);
            welcomePane.setMargin(card, new Insets(0, 0, 0, 0));
//            card.setOnSwipeRight(new EventHandler<SwipeEvent>() {   Registers that a touch swipe occered
//                @Override public void handle(SwipeEvent event) {
//                    System.out.println("Rectangle: Swipe right event");
//                    event.consume();
//                }
//            });
//
//            card.setOnSwipeLeft(new EventHandler<SwipeEvent>() {
//                @Override public void handle(SwipeEvent event) {
//                    System.out.println("Rectangle: Swipe left event");
//                    event.consume();
//                }
//            });

            welcomePane.setAlignment(card, Pos.CENTER);
            card.setPreserveRatio(true);
            card.setFitHeight(330);
            card.setFitWidth(250);
        } else {
            welcomePane.getChildren().remove(progress);
            Text noMore = new Text("NO MORE\nCARDS LEFT");
            BorderPane.setAlignment(noMore, Pos.CENTER);
            noMore.setFont(new Font("Poppins", 36));
            noMore.setStyle("-fx-font-weight: bold");
            noMore.setEffect(new DropShadow());
            welcomePane.setCenter(noMore);
            welcomePane.setAlignment(noMore, Pos.CENTER);
            noMore.setFill(Color.WHITE);
            noMore.setStroke(Color.BLACK);
            skillsResults = new Button("See Skill Report");
            skillsResults.setStyle("-fx-border-color: white; -fx-border-width:2;" +
            "-fx-background-color:transparent;-fx-font-weight:bold;");
            skillsResults.setEffect(new DropShadow());
            skillsResults.setOnMouseExited(e -> {
                skillsResults.setStyle("-fx-border-color: white; -fx-border-width:2;" +
                        "-fx-background-color:transparent;-fx-font-weight:bold;");
                skillsResults.setTextFill(Color.WHITE);
            });

            skillsResults.setOnMouseEntered(e -> {
                skillsResults.setStyle("-fx-background-color:#dae7f3;");
            });
            skillsResults.setFont(new Font("Poppins", 17));
            skillsResults.setPrefWidth(200);
            skillsResults.setPrefHeight(100);
            welcomePane.setBottom(skillsResults);
            welcomePane.setMargin(skillsResults, new Insets(0.0, 0.0, 20, 0.0));
            welcomePane.setAlignment(skillsResults, Pos.CENTER);
            genSkillsReport();
        }
    }

    /**
     * Generates a skill report for the user,
     * specifically for the STEAM career
     * achievement domain.
     */
    public void genSkillsReport() {
        skillsResults.setOnAction(e -> {
            //background.getChildren().remove(backImg);
            //background.setStyle("-fx-background-color: #C1D1A6");
            background.getChildren().remove(welcomePane);
            welcomePane = new BorderPane(); // reset

            Text skill = new Text("SKILL:\nSTEAM CAREERS ");
            BorderPane.setAlignment(skill, Pos.CENTER);
            int totalCards = numNotHave + numUsed + numNotUsed;
            Text have = new Text("You currently have " + numUsed +
                    "\nSTEAM Career skills.");
            Text noUse = new Text("You currently have but\n" +
                    "do not use " + numNotUsed + " STEAM\n" +
                    "Career skills");
            Text haveNot = new Text(" You currently do not have\n" +
                    numNotHave + " STEAM Career Skills.");
            Text competency = new Text("Overall STEAM \nCareer " +
                    "Competency: " + numUsed + "/" + totalCards);

            Rectangle green = new Rectangle(200, 75, Color.GREEN);
            StackPane greenPane = new StackPane();
            greenPane.getChildren().addAll(green, have);

            Rectangle orange = new Rectangle(200, 75, Color.ORANGE);
            StackPane orangePane = new StackPane();
            orangePane.getChildren().addAll(orange, noUse);

            Rectangle red = new Rectangle(200, 75, Color.RED);
            StackPane redPane = new StackPane();
            redPane.getChildren().addAll(red, haveNot);

            have.setFont(new Font("Poppins", 15));
            have.setFill(Color.WHITE);
            have.setStyle("-fx-font-weight:bold;");

            haveNot.setFont(new Font("Poppins", 15));
            haveNot.setFill(Color.WHITE);
            haveNot.setStyle("-fx-font-weight:bold;");

            noUse.setFont(new Font("Poppins", 15));
            noUse.setFill(Color.WHITE);
            noUse.setStyle("-fx-font-weight:bold;");


            VBox allRectangles = new VBox(25);
            allRectangles.getChildren().addAll(greenPane, orangePane, redPane);
            allRectangles.setAlignment(Pos.CENTER);
            skill.setFill(Color.WHITE);
            skill.setEffect(new DropShadow());
            skill.setFont(new Font("Poppins", 36));


            competency.setFill(Color.WHITE);
            competency.setEffect(new DropShadow());
            competency.setFont(new Font("Poppins", 25));

            green.setEffect(new DropShadow());
            orange.setEffect(new DropShadow());
            red.setEffect(new DropShadow());

            welcomePane.setTop(skill);
            welcomePane.setCenter(allRectangles);
            welcomePane.setBottom(competency);
            welcomePane.setAlignment(skill, Pos.CENTER);
            welcomePane.setAlignment(competency, Pos.CENTER);
           // welcomePane.setAlignment(, Pos.CENTER);
            welcomePane.setMargin(competency, new Insets(10.0, 0.0, 20, 0.0));
            welcomePane.setMargin(skill, new Insets(15, 0.0, 30.0, 0.0));
            background.getChildren().add(welcomePane);
        });
    }
}
