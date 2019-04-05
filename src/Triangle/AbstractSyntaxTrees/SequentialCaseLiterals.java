package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialCaseLiterals extends Cases {

	public SequentialCaseLiterals (Cases lAst1, Cases lAst2, SourcePosition thePosition) {
		super (thePosition);
		L1 = lAst1;
		L2 = lAst2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitSequentialCaseLiterals(this, o);
	}
	public Cases L1, L2;

}