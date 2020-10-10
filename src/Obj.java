
import java.util.*; // import the HashMap class


abstract class Obj
{

    Obj()

    // This constructor will be invoked every time the constructor of any descendant class is invoked.

    {
        CountObjects.getSuperClass(getClass());
    }

}