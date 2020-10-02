import java.util.*;

class Floatp extends Primary
{
    float val;

    Floatp(float f)
    {
        val = f;
    }

    void printParseTree(String indent)
    {
        String indent1 = indent+" ";

        super.printParseTree(indent);
        IO.displayln(indent1 + indent1.length() + " " + val);
    }

    Val Eval(HashMap<String,Val> state)
    {
        return new FloatVal(val);
    }
}