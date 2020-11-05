
import java.util.*;

public class CountObjects {
    public static HashMap<String, Integer> objCount = new HashMap<>();

    public static void getSuperClass(Class c){
        String nameOfClass=c.getName();
        String nameOfSuperClass=c.getSuperclass().getName();
        objCount.computeIfPresent(nameOfClass, (k, v) -> v + 1);
        objCount.computeIfAbsent(nameOfClass,k->1 );
        if(nameOfSuperClass!="java.lang.Object") {
            getSuperClass(c.getSuperclass());
        }
    }

    public static LinkedHashMap<String, Integer> sortHashMapByValues(
            HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues,Collections.reverseOrder());
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
}
