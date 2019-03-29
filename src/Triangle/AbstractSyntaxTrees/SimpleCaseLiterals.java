package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SimpleCaseLiterals extends CaseLiterals {

	public SimpleCaseLiterals(CaseRange rAST, SourcePosition thePosition) {
		super (thePosition);
		R = rAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitSimpleCaseLiterals(this, o);
	}
	public CaseRange R;
}
