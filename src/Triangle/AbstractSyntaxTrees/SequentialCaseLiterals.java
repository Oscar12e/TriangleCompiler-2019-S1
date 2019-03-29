package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialCaseLiterals extends CaseLiterals {

	public SequentialCaseLiterals (CaseLiterals lAst1, CaseLiterals lAst2, SourcePosition thePosition) {
		super (thePosition);
		L1 = lAst1;
		L2 = lAst2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitSequentialCaseLiterals(this, o);
	}
	public CaseLiterals L1, L2;

}