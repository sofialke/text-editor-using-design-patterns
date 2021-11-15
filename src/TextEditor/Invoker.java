package TextEditor;

public class Invoker {
    //map with an id for every command, only one instance per command. there will be a parameter that will hold the text
    //that will be inserted. the map will have keys- I for Insert, D for Delete itp

    private String textToBeInserted;

    public String getTextToBeInserted(){
        return this.textToBeInserted;
    }
}
