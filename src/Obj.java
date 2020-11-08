import java.util.*;

abstract class Obj {
    boolean visited = false; // indicates if this object has been visited by traverse() function
    List<Obj> neighbours;
    static int counter = 0;

    Obj()
    // This constructor will be invoked every time the constructor of any descendant class is invoked.
    {
        CountObjects.getSuperClass(getClass());
    }


    public void addneighbours(Obj neighbourNode) {
        this.neighbours.add(neighbourNode);
    }


    public List<Obj> getNeighbours() {
        return neighbours;
    }


    public void setNeighbours(List<Obj> neighbours) {
        this.neighbours = neighbours;
    }

    public void traverse()  {
        counter=0;
        IO.display2("Object-graph traversal has started...");
        DFT.dfs(this);
        IO.display2(counter +" objects have been visited so far.");
        IO.display2("---------------------------------");
    }



}




//    public Field[] getFields(Obj o) {
//        return o.getClass().getDeclaredFields();
//    }
//
//
//
//    public boolean isInstanceOfObj(Object o) {
//        return o instanceof Obj;
//    }


//    public List<Obj> getRelevantFieldObjects(Object o){
//        List<Obj> finalFields=new ArrayList<>();
//        String[] allNodeTypes={"IntVal","FloatVal","Assignment","MultipleAssignment","SingleTerm","AddE","SubE","SinglePrimary","MulTerm","DivTerm","Id","Int","Floatp","Parenthesized","Primary","Term","E","AssignmentList","Val","Obj"};
//        Set<String> allNodes = new HashSet<String>();
//        allNodes.addAll(Arrays.asList(allNodeTypes));
//        try{
//           Field[] f= o.getClass().getDeclaredFields();
//           for(Field field:f){
//              if(allNodes.contains(field.getType().getName())){
//                  finalFields.add((Obj) field.get(obj));
//              };
//           }
//        }
//        catch(Exception e){
//
//        }
//        return finalFields;
//    }


//    public void traverseAllFields(Object o){
//        if(!isInstanceOfObj(o)){
//            System.out.println("returning");
//            return;
//        }
//        var field= o.getClass().getDeclaredFields();
//         for(Field f:field){
//             try {
//                 traverseAllFields(f.get(obj));
//             } catch (IllegalAccessException e) {
//                 e.printStackTrace();
//             }
//         }
//
//
//    }

//    public void getClassField(Object o){
//        if(!isInstanceOfObj(o)){
//            return;
//        }
//       var field= o.getClass().getDeclaredFields();
//        for(Field f:field){
//            System.out.println(f);
//            try {
//                getClassField(f.get(obj));
//
//            } catch (Exception e) {
//              return;
//            }
//        }
//    }

//
//    public void addToArrayList(ArrayList<Object> a,Field f){
//        Object o=convertToObj(f);
//        if(o!=null) {
//            a.add(o);
//        }
//    }
//
//    public Object convertToObj(Field f){
//        try {
//            return f.get(obj);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//

//
//    public void traverseClassField(Object o){
//    Field [] fields=o.getClass().getDeclaredFields();
//    ArrayList<Object> fieldObjects=new ArrayList<Object>();
//        for(Field f:fields){
//            addToArrayList(fieldObjects,f);
//        }
//        for(Object obj:fieldObjects){
//            traverseClassField(obj);
//        }
//        return;
//    }
//
//
//    public void traverseToSuperClass(Class c) throws Exception{
//         System.out.println(c.getName());
//        if(c.getSuperclass().getName()=="java.lang.Object"){
//            return;
//        }
//        Field[] field=  c.getSuperclass().getDeclaredFields();
//        for(Field f:field){
//            System.out.println(f.get(obj).getClass().getName());
//        }
//    }
//
//    public ArrayList<Field> getAllFields(){
//        ArrayList<Field> fields = new ArrayList<Field>();
//        Collections.addAll(fields,this.getClass().getDeclaredFields());
//        getSuperFields(this.getClass(),fields);
//       return fields;
//    }
//
//    public void getSuperFields(Class className,ArrayList<Field> fields){
//        if(className.getSuperclass().getName()=="java.lang.Object"){
//            return;
//        }
//        Collections.addAll(fields,className.getSuperclass().getDeclaredFields());
//        getSuperFields(className.getSuperclass(),fields);
//    }
