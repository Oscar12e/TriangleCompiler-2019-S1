package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForUntilCommand extends Command {

	public ForUntilCommand (Identifier iAst, Expression eAST1, Expression eAST2, UntilCommand uAST, SourcePosition thePosition) {
		super (thePosition);
		I = iAst;
		E1 = eAST1;
		E2 = eAST2;
		U = uAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForUntilCommand(this, o);
	}

	public Identifier I;
	public Expression E1, E2;
	public UntilCommand U;
}