import java.util.Comparator;

public class VertexWithWeightComparater implements Comparator <VertexWithWeight> {
    public int compare(VertexWithWeight o1, VertexWithWeight o2) {
        if(o1.getWeight() < o2.getWeight()) {
            return -1;
        }
        if(o1.getWeight() > o2.getWeight()) {
            return 1;
        }
        if(o1.getWeight() == o2.getWeight() && o1.getVertex() < o2.getVertex()) {
            return -1;
        }
        if(o1.getWeight() == o2.getWeight() && o1.getVertex() > o2.getVertex()) {
            return 1;
        }
        if(o1.getWeight() == o2.getWeight() && o1.getVertex() == o2.getVertex()) {
            return 0;
        }
        return 0;
    }
}
