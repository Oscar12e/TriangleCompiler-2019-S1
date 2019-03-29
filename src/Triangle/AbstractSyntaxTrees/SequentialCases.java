package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialCases extends Cases {

	public SequentialCases (Cases cAST1, Cases cAST2, SourcePosition thePosition) {
		super (thePosition);
		C1 = cAST1;
		C2 = cAST2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitSequentialCases(this, o);
	}
	public Cases C1;
	public Cases C2;
}
