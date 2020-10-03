
import java.util.*; // import the HashMap class


abstract class Obj
{
   public static HashMap<String, Integer> objCount = new HashMap<>();

    Obj()

    // This constructor will be invoked every time the constructor of any descendant class is invoked.

    {
        getSuperClass(getClass());
    }


    public void getSuperClass(Class c){
        String nameOfClass=c.getName();
        String nameOfSuperClass=c.getSuperclass().getName();
        objCount.computeIfPresent(nameOfClass, (k, v) -> v + 1);
        objCount.computeIfAbsent(nameOfClass,k->1 );
        if(nameOfSuperClass!="java.lang.Object") {
                getSuperClass(c.getSuperclass());
            }
    }

}