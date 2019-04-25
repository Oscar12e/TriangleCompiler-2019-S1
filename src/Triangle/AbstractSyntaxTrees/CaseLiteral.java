package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseLiteral extends Expression {

	public CaseLiteral (Terminal lAst, SourcePosition thePosition) {
		super (thePosition);
		L = lAst;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCaseLiteral(this, o);
	}

	public Terminal L; //Can be a Integer Literal as a Char Literal
}