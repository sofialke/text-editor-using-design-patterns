package TextEditor;
/**
 * Class that implements paste command by implementing the Command interface.
 */

public class InsertCommand implements Command{
    EngineImpl engine;
	private String s;

    public InsertCommand(EngineImpl engine, String s){
        this.engine = engine;
        this.s = s;
    }

    public void execute(){
        engine.insert(s);
    }

}
