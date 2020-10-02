/**

 This class is a top-down, recursive-descent parser for the following syntactic categories:

 <assignment list> --> <assignment> | <assignment> <assignment list>
 <assignment> --> <id> = <E> ";"
 <E> --> <term> | <term> + <E> | <term> - <E>
 <term> --> <primary> | <primary> * <term> | <primary> / <term>
 <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

 The definitions of the tokens are given in the lexical analyzer class file "LexArithArray.java".

 The following variables/functions of "IO"/"LexArithArray" classes are used:

 static void display(String s)
 static void displayln(String s)
 static void setIO(String inFile, String outFile)
 static void closeIO()

 static void setLex()
 static String t // holds an extracted token
 static State state // the current state of the finite automaton
 static int getToken() // extracts the next token

 An explicit parse tree is constructed in the form of nested class objects.

 The main function will display the parse tree in linearly indented form.
 Each syntactic category name labeling a node is displayed on a separate line,
 prefixed with the integer i representing the node's depth and indented by i blanks.

 **/


public abstract class Parser extends LexArithArray
{
    static boolean errorFound = false;


    public static AssignmentList assignmentList()

    // <assignment list> --> <assignment> | <assignment> <assignment list>

    {
        Assignment assignment = assignment();
        if ( state == State.Id )
        {
            AssignmentList assignmentList = assignmentList();
            return new MultipleAssignment(assignment, assignmentList);
        }
        else
            return assignment;
    }

    public static Assignment assignment()

    // <assignment> --> <id> = <E> ";"

    {
        if ( state == State.Id )
        {
            String id = t;
            getToken();
            if ( state == State.Assign )
            {
                getToken();
                E e = E();
                if ( state == State.Semicolon )
                {
                    getToken();
                    return new Assignment(id, e);
                }
                else
                    errorMsg(4);
            }
            else
                errorMsg(3);
        }
        else
            errorMsg(5);
        return null;
    }

    public static E E()

    // <E> --> <term> | <term> + <E> | <term> - <E>

    {
        Term term = term();
        if ( state == State.Plus )
        {
            getToken();
            E e = E();
            return new AddE(term, e);
        }
        else if ( state == State.Minus )
        {
            getToken();
            E e = E();
            return new SubE(term, e);
        }
        else
            return new SingleTerm(term);
    }

    public static Term term()

    // <term> --> <primary> | <primary> * <term> | <primary> / <term>

    {
        Primary primary = primary();
        if ( state == State.Times )
        {
            getToken();
            Term term = term();
            return new MulTerm(primary, term);
        }
        else if ( state == State.Div )
        {
            getToken();
            Term term = term();
            return new DivTerm(primary, term);
        }
        else
            return new SinglePrimary(primary);
    }

    public static Primary primary()

    // <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

    {
        switch ( state )
        {
            case Id:

                Id id = new Id(t);
                getToken();
                return id;

            case Int:

                Int intElem = new Int(Integer.parseInt(t));
                getToken();
                return intElem;

            case Float: case FloatE:

            Floatp floatElem = new Floatp(Float.parseFloat(t));
            getToken();
            return floatElem;

            case LParen:

                getToken();
                E e = E();
                if ( state == State.RParen )
                {
                    getToken();
                    Parenthesized paren = new Parenthesized(e);
                    return paren;
                }
                else
                {
                    errorMsg(1);
                    return null;
                }

            default:

                errorMsg(2);
                return null;
        }
    }

    public static void errorMsg(int i)
    {
        errorFound = true;

        display(t + " : Syntax Error, unexpected symbol where");

        switch( i )
        {
            case 1:	displayln(" arith op or ) expected"); return;
            case 2: displayln(" id, int, float, or ( expected"); return;
            case 3:	displayln(" = expected"); return;
            case 4:	displayln(" ; expected"); return;
            case 5:	displayln(" id expected"); return;
        }
    }

    public static void main(String argv[])
    {
        // argv[0]: input file containing an assignment list
        // argv[1]: output file displaying the parse tree

        setIO( argv[0], argv[1] );
        setLex();

        getToken();

        AssignmentList assignmentList = assignmentList(); // build a parse tree
        if ( ! t.isEmpty() )
            errorMsg(5);
        else if ( ! errorFound )
            assignmentList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

        closeIO();
    }
}