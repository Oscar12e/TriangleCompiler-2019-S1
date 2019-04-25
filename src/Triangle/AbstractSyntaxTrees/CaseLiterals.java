package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseLiterals extends Expression {

	public CaseLiterals(Expression rAST, SourcePosition thePosition) {
		super (thePosition);
		R = rAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCaseLiterals(this, o);
	}
	public Expression R;
}
