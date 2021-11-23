package TextEditor;

import java.util.Optional;

public interface Originator {
	
	Optional<Memento> getMemento();
	void setMemento(Memento memento);

}
