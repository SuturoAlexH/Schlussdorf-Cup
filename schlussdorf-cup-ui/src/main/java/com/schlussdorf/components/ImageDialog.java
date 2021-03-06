package com.schlussdorf.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

/**
 * A dialog with an image as content and a close button.
 */
public class ImageDialog {

    private Alert alert;

    private ImageView imageView ;

    /**
     * Default constructor.
     */
    public ImageDialog(final String id){
        initialize(id);
    }

    private void initialize(final String id){
        alert = new Alert(Alert.AlertType.NONE, null, ButtonType.CLOSE);
        alert.getDialogPane().setId(id);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().setMaxSize(300, 300);

        Button closeButton = (Button)alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.setId("closeButton");

        imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitHeight(300);
        imageView.preserveRatioProperty().set(true);

        alert.setOnHidden(e -> {
            imageView.setImage(null);
            System.gc();
        });

        alert.setGraphic(imageView);
    }

    /**
     * Shows the dialog with the given image as content.
     *
     * @param image the image that should be shown
     */
    public void show(final Image image){
        imageView.setImage(image);
        alert.showAndWait();
    }
}
