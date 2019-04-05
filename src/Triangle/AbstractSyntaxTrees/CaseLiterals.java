package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseLiterals extends Cases {

	public CaseLiterals(CaseRange rAST, SourcePosition thePosition) {
		super (thePosition);
		R = rAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCaseLiterals(this, o);
	}
	public CaseRange R;
}
