package uk.ac.warwick.dcs.ui.formdata;

public class AvailableDirections {
    private boolean n, e, s, w;

    public AvailableDirections() {
        // none of the directions are initially available,
        // following closed-world assumption
        n = e = s = w = false;
    }

    public boolean getN() { return n; }
    public boolean getE() { return e; }
    public boolean getS() { return s; }
    public boolean getW() { return w; }
    public void setN(boolean newN) { n = newN; }
    public void setE(boolean newE) { e = newE; }
    public void setS(boolean newS) { s = newS; }
    public void setW(boolean newW) { w = newW; }
}
