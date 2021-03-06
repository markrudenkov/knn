import java.util.ArrayList;
import java.util.Arrays;

public class Model implements Comparable{
    public int iClass;
    public double[] vector;
    public double distance;
    public ArrayList<Integer> excludedIndexes = new ArrayList<>();



    public Model(int iClass, double[] vector) {
        this.iClass = iClass;
        this.vector = vector;
        for (int i=0; i<vector.length;i++){
            this.excludedIndexes.add(i);
        }

    }

    @Override
    public int compareTo(Object o) {
        Model m = (Model) o;
        return m.distance > this.distance ? 1 : 0;
    }

    public Model clone() {
        Model m = new Model(this.iClass, this.vector.clone());
        return m;
    }

    @Override
    public String toString() {
        return "Model{" +
                "iClass=" + iClass +
                ", vector=" + Arrays.toString(vector) +
                ", distance=" + distance +
                ", excludedIndexes=" + excludedIndexes +
                '}';
    }
}


