import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFT {


    public static void dfs(Obj node) {
        IO.display2(node.getClass().getName() + " ");
        node.setNeighbours(getRelevantFieldObjects(node));
        List<Obj> neighbours = node.getNeighbours();
        node.visited = true;
        Obj.counter++;
        for (Obj neighbour : neighbours) {
            if (neighbour != null && !neighbour.visited) {
                dfs(neighbour);
            }
        }
    }

    public static List<Obj> getRelevantFieldObjects(Obj o) {
        List<Obj> finalObjects = new ArrayList<>();
        List<Field> fields = getAllFieldsAlsoSuperFields(o);
        for (Field f : fields) {
            try {
                finalObjects.add((Obj) f.get(o));
            } catch (Exception e) {
                //do nothing
            }
        }
        return finalObjects;
    }

    public static List<Field> getAllFieldsAlsoSuperFields(Obj o) {
        List<Field> collector = new ArrayList<>();
        Class c = o.getClass();
        while (c.getSuperclass().getName() != "java.lang.Object") {
            Collections.addAll(collector, c.getDeclaredFields());
            c = c.getSuperclass();
        }
        return collector;
    }


}
