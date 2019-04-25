package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseRange extends Expression {

	public CaseRange(Expression lAST1, Expression lAST2, SourcePosition thePosition) {
		super (thePosition);
		L1 = lAST1;
		L2 = lAST2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCaseRange(this, o);
	}

	public Expression L1, L2;
}