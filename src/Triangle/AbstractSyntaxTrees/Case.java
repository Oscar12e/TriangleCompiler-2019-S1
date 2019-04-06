package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class Case extends Cases {

	public Case (Cases lAst, Command cAST, SourcePosition thePosition) {
		super (thePosition);
		CL = lAst;
		C = cAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCase(this, o);
	}
	public Cases CL;
	public Command C;
}
