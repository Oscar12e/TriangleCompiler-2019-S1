package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForCommand extends Command {

	public ForCommand (ForDeclaration fAst, Expression eAST, Command cAST, SourcePosition thePosition) {
		super (thePosition);
		F = fAst;
		C = cAST;
		E = eAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForCommand(this, o);
	}

	public ForDeclaration F;
	public Expression E;
	public Command C;
}