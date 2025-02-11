package uk.ac.warwick.dcs.ui.panels;

import uk.ac.warwick.dcs.ui.interfaces.IReadablePanel;
import uk.ac.warwick.dcs.ui.util.IntegerTextField;

import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

public class GroupTimingsPanel extends CustomPanel implements IReadablePanel<List<Integer>> {
    private final List<IntegerTextField> timingFields;
    private int numGroups;

    public GroupTimingsPanel(Font headingFont, Font labelFont, int numGroups) {
        super(headingFont, labelFont);
        this.timingFields = new LinkedList<>();
        this.numGroups = numGroups;
        setUp();
    }
    @Override
    public List<Integer> getValue() {
        return timingFields.stream().map(IntegerTextField::getIntegerValue).toList();
    }

    @Override
    protected void setUp() {
        // TODO:
    }

    public void changeNumGroups(int newNumGroups) {
        // TODO:
    }
}
