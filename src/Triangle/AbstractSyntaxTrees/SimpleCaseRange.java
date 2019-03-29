package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SimpleCaseRange extends CaseRange {

	public SimpleCaseRange(CaseLiteral lAST, SourcePosition thePosition) {
		super (thePosition);
		L = lAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitSimpleCaseRange(this, o);
	}

	public CaseLiteral L;
}