package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CompleteCaseRange extends CaseRange {

	public CompleteCaseRange(CaseLiteral lAST1, CaseLiteral lAST2, SourcePosition thePosition) {
		super (thePosition);
		L1 = lAST1;
		L2 = lAST2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCompleteCaseRange(this, o);
	}

	public CaseLiteral L1, L2;
}