import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class STEAMCards {
    public static ArrayList<ImageView> steam = new ArrayList<ImageView>(Arrays.asList(
            new ImageView(new Image("./CropSTEAM/agriculture.png")),
            new ImageView(new Image("./CropSTEAM/architecture.png")),
            new ImageView(new Image("./CropSTEAM/arts.png")),
            new ImageView(new Image("./CropSTEAM/education.png")),
            new ImageView(new Image("./CropSTEAM/film.png")),
            new ImageView(new Image("./CropSTEAM/finance.png")),
            new ImageView(new Image("./CropSTEAM/government.png")),
            new ImageView(new Image("./CropSTEAM/green.png"))));
}
