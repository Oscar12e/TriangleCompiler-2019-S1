package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/*Modified by Daniel Sánchez*/
public abstract class ProcFuncs extends Declaration{
	public ProcFuncs (SourcePosition thePosition) {
		super (thePosition);
	}

	public abstract Object visitTwo(Visitor v, Object o);
}
