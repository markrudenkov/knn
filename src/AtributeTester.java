import java.util.ArrayList;

/**
 * Created by kosciuszko on 16.10.21.
 */
public class AtributeTester {
    private Statistics statistics = new Statistics();

    public ArrayList<Integer> getBestCharateristics(ArrayList<Model> initSet, int k, int numberOfCharacteristics) {
        ArrayList<Integer> indexesOfBestcharacteristics = new ArrayList<>();
        ArrayList<Model> setWithExcludedCharacteristicsget = getOptimizedSet(initSet, k, numberOfCharacteristics);
        Model model = setWithExcludedCharacteristicsget.get(0);
        for (int i = 0; i < model.vector.length; i++) {
            if (model.vector[i] != 0) {
                indexesOfBestcharacteristics.add(i);
            }
        }
        return indexesOfBestcharacteristics;
    }

    public ArrayList<Model> getOptimizedSet(ArrayList<Model> initSet, int k, int numberOfCharacteristics) {

        int excludedCharacteristics = initSet.get(0).vector.length - numberOfCharacteristics;
        ArrayList<Model> optimizedSet = new ArrayList<>();
        for (Model model : initSet) {
            optimizedSet.add(model.clone());
        }
        for (int i = 0; i < excludedCharacteristics; i++) {

            optimizedSet = getBestSet(createArraySet(initSet), k);
        }
        return optimizedSet;
    }

    public ArrayList<ArrayList<Model>> createArraySet(ArrayList<Model> args) {
        ArrayList<ArrayList<Model>> arraySet = new ArrayList<>();
        ArrayList<Model> list;
        for (int i = 0; i < args.get(0).vector.length; i++) {
            list = setCharacteristicToZero(args, i);
            if (!list.isEmpty()) {
                arraySet.add(list);
            }
        }
        return arraySet;
    }

    public ArrayList<Model> setCharacteristicToZero(ArrayList<Model> args, int charcteristic) {
        ArrayList<Model> arrayList = new ArrayList<>();
        if (args.get(0).vector[charcteristic] != 0) {
            for (Model model : args) {
                arrayList.add(model.clone());
            }
            for (Model model : arrayList) {
                model.vector[charcteristic] = 0;
            }
        }
        return arrayList;
    }

    public ArrayList<Model> getBestSet(ArrayList<ArrayList<Model>> setOfSets, int k) {
        double bestPercent = 0;
        int bestSetIndex = 0;
        double perc;
        for (int i = 0; i < setOfSets.size(); i++) {
            perc = statistics.calcPercentage(k, setOfSets.get(i));

            if (perc > bestPercent) {
                bestPercent = perc;
                bestSetIndex = i;
            }
        }
        return setOfSets.get(bestSetIndex);
    }
}
