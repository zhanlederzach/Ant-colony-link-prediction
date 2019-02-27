package kbtu.app;

import java.util.Vector;

public class Vertex {
    private int number;
    private boolean isAttribute;
    private Vector<Integer> attributesVector;

    public Vertex(int number, boolean isAttribute) {
        this.number = number;
        this.isAttribute = isAttribute;
    }

    public Vertex(int number, Vector<Integer> attributesVector) {
        this.number = number;
        this.attributesVector = attributesVector;
    }

    public Vertex(int number, boolean isAttribute, Vector<Integer> attributesVector) {
        this.number = number;
        this.isAttribute = isAttribute;
        this.attributesVector = attributesVector;
    }

    public boolean isAttribute() {
        return isAttribute;
    }

    public void setAttribute(boolean attribute) {
        isAttribute = attribute;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Vector<Integer> getAttributesVector() {
        return attributesVector;
    }

    public void setAttributesVector(Vector<Integer> attributesVector) {
        this.attributesVector = attributesVector;
    }
}
