package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class Case extends Cases {

	public Case (CaseLiterals lAst, Command cAST, SourcePosition thePosition) {
		super (thePosition);
		L = lAst;
		C = cAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCase(this, o);
	}
	public CaseLiterals L;
	public Command C;
}