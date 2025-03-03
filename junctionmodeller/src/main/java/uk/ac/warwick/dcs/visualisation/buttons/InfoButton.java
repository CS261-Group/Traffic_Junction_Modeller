package uk.ac.warwick.dcs.visualisation.buttons;

public class InfoButton extends UiButton {
    public InfoButton() {
        super("/question.png");

        setOnAction(e -> {
            System.out.println("HELP");
            // TODO: implement floating key popup
        });
    }
}
