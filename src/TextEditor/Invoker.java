package TextEditor;

public interface Invoker {
    String getTextToBeInserted();
    Integer getBeginIndex();
    Integer getEndIndex();
    void setTextToBeInserted(String text);
    void setBeginIndex(Integer index);
    void setEndIndex(Integer index);
    void execute(String keyName);
}
