package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForUntilCommand extends Command {

	public ForUntilCommand (ForDeclaration fAST, UntilCommand uAST, SourcePosition thePosition) {
		super (thePosition);
		F = fAST;
		U = uAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForUntilCommand(this, o);
	}

	public ForDeclaration F;
	public UntilCommand U;
}