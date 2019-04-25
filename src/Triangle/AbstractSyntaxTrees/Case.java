package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class Case extends Cases {

	public Case (Expression lAst, Command cAST, SourcePosition thePosition) {
		super (thePosition);
		CL = lAst;
		C = cAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCase(this, o);
	}
	public Expression CL;
	public Command C;
}
