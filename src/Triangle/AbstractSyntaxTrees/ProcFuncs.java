package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/*Modified by Daniel S�nchez*/
public abstract class ProcFuncs extends Declaration{
	public ProcFuncs (SourcePosition thePosition) {
		super (thePosition);
	}

	public abstract Object visitTwo(Visitor v, Object o);
}
