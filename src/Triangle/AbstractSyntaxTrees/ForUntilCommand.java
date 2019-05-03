package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForUntilCommand extends Command {

	public ForUntilCommand (ForDeclaration fAST, Expression eAST, UntilCommand uAST, SourcePosition thePosition) {
		super (thePosition);
		F = fAST;
		E = eAST;
		U = uAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForUntilCommand(this, o);
	}

	public ForDeclaration F;
	public Expression E;
	public UntilCommand U;
}