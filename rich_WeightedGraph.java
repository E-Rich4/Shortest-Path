import java.util.ArrayList;

public class rich_WeightedGraph implements WeightedGraphFunctions {
    
    private final java.util.ArrayList<Integer> vertices;
    private final java.util.ArrayList<EdgeWithWeight> edges;
    public double cost;

    public rich_WeightedGraph() {
        vertices = new java.util.ArrayList<>();
        edges = new java.util.ArrayList<>();
    }

    public boolean hasPath(int fromVertex, int toVertex) {
        boolean booleanValue = (Boolean) getPath(fromVertex, toVertex, WeightedGraphReturnType.HAS_PATH);
        return booleanValue;
    }

	public double getMinimumWeight(int fromVertex, int toVertex) {
        return (Double) getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_MINIMUM_WEIGHT);
    }

	public EdgeWithWeight[] getPath(int fromVertex, int toVertex) {
        return (EdgeWithWeight[]) getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_PATH);
    }

    public int getIndex(Integer v) {
        int i = vertices.indexOf(v);
        return i;
    }

	private Object getPath(int fromVertex, int toVertex, WeightedGraphReturnType typeOfInfo)
	{
        java.util.PriorityQueue<VertexWithWeight> minPriorityQueueByWeight = new java.util.PriorityQueue<>(vertices.size(), new VertexWithWeightComparater());
        VertexWithWeight[] verticeCost = new VertexWithWeight[vertices.size()];
        int[] parent = new int[vertices.size()];

        for( int i = 0; i < vertices.size(); i++ )
        {
            parent[i] = -1;
            verticeCost[i] = new VertexWithWeight(vertices.get(i), Double.POSITIVE_INFINITY);
        }

        parent[getIndex(fromVertex)] = fromVertex;
        verticeCost[getIndex(fromVertex)] = new VertexWithWeight(vertices.get(getIndex(fromVertex)), 0.0);

        for ( int i = 0; i < vertices.size(); i ++ ) {
            minPriorityQueueByWeight.add(verticeCost[i]);
        }

        while( minPriorityQueueByWeight.size() > 0 ) {
            VertexWithWeight vww = minPriorityQueueByWeight.poll();

            int v = vww.getVertex();

            
            if (parent[getIndex(v)] == -1) {
                //Exit the while loop and return the appropriate data
                //System.out.println("v no parent. Broke");
                break;
            }
            
            if (v == toVertex) {
                //System.out.println("v was destination");
                break;
            }
            if (vww.getWeight() == Double.POSITIVE_INFINITY) {
                //System.out.println("weoght was pos inf. Broke");
                break;
            }

            for( EdgeWithWeight e : edges ) {
                if( e.from() == v) {
                    //int u = e.from();
                    cost = vww.getWeight() + e.weight();
                    if (cost < verticeCost[getIndex(e.to())].getWeight()) {
                        verticeCost[getIndex(e.to())].setWeight(cost);
                        parent[getIndex(e.to())] = e.from();
                        
                    }
                }
                
            }

        }
        if(parent[getIndex(toVertex)] == -1 || verticeCost[getIndex(toVertex)].getWeight() == Double.POSITIVE_INFINITY) {
            if(typeOfInfo == WeightedGraphReturnType.HAS_PATH) {
                return false;
            }
            if(typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT) {
                return Double.NaN;
            }
            if(typeOfInfo == WeightedGraphReturnType.GET_PATH) {
                return new EdgeWithWeight[0];
            }
        }
        else {
            if(typeOfInfo == WeightedGraphReturnType.HAS_PATH) {
                return true;
            }
            if(typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT) {
                return verticeCost[getIndex(toVertex)].getWeight();
            }
            if(typeOfInfo == WeightedGraphReturnType.GET_PATH) {

                java.util.ArrayList<Integer> revint = new ArrayList<>();
                int p = toVertex;
                revint.add(p);
                
                while (p != fromVertex) {
                    p = parent[getIndex(p)];
                    revint.add(p);
                }

                java.util.ArrayList<Integer> ForwardList = new ArrayList<>();

                for (int i = revint.size() - 1; i >= 0; i--) {
                    ForwardList.add(revint.get(i));
                }

                EdgeWithWeight[] eww = new EdgeWithWeight[ForwardList.size()-1];

                for (int i = 0; i < ForwardList.size()-1; i++) {
                    int from = ForwardList.get(i);
                    int to = ForwardList.get(i+1);
                    for ( EdgeWithWeight e : edges) {
                        if(e.from() == from && e.to() == to) {
                            eww[i] = e;
                        }
                    }
                }

                return eww;
            }
        }
		return null;
	}

	public boolean addVertex(int v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            return true;
        }
        else {
            return false;
        }
        
    }

	public boolean addWeightedEdge(int from, int to, double weight) {
        EdgeWithWeight curEdge = new EdgeWithWeight(from, to, weight);
        if (!edges.contains(curEdge)) {
            edges.add(curEdge);
            return true;
        }
        else {
            return false;
        }
        
    }

	public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("G = (V, E)\n");
        string.append("V = {");
        for (Integer v : vertices) {
            //System.out.print(v + " ");
            string.append(v);
            string.append(",");   
        }
        string.delete(string.length() -1 , string.length());
        string.append("}" + '\n');
        
        string.append("E = {");
        for (EdgeWithWeight ed : edges) {
            string.append(ed);
            string.append(",");           
        }
        string.delete(string.length() -1 , string.length());
        string.append("}");
        return string.toString();
    }

}