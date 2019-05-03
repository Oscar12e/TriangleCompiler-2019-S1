package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForWhileCommand extends Command {

	public ForWhileCommand (ForDeclaration fAST, Expression eAST, WhileCommand wAST, SourcePosition thePosition) {
		super (thePosition);
		F = fAST;
		E = eAST;
		W = wAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForWhileCommand(this, o);
	}
	public ForDeclaration F;
	public Expression E;
	public WhileCommand W;
}

