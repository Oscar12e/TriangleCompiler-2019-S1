package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForCommand extends Command {

	public ForCommand (Identifier iAst, Expression eAST1, Expression eAST2, Command cAST, SourcePosition thePosition) {
		super (thePosition);
		I = iAst;
		E1 = eAST1;
		E2 = eAST2;
		C = cAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForCommand(this, o);
	}

	public Identifier I;
	public Expression E1, E2;
	public Command C;
}