//import java.util.Comparator;

public class VertexWithWeight implements VertexWithWeightFunctions {
    private final Integer vertex;
    private  Double weight;

    public VertexWithWeight(int v, double w) {
       vertex = v;
       weight = w;
    }

    public int getVertex() {
        return vertex.intValue();
    }
    
    public double getWeight() {
        return weight;
    }

    public void setWeight(double w) {
        weight = w;
    }

    public String toString() {
        return "(" + vertex + "," + weight + ")";
    }
    
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (getClass() == o.getClass()) {
            if (vertex.intValue() == ((VertexWithWeight) o).getVertex()) {
                return true;
            }
        }
        return false;
    }
}
