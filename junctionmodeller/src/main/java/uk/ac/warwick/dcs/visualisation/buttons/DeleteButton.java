package uk.ac.warwick.dcs.visualisation.buttons;

public class DeleteButton extends UiButton {
    public DeleteButton() {
        super("/delete.png");

        setOnAction(e -> {
            System.out.println("DELETED");
            // TODO: something when model is deleted
        });
    }
}
