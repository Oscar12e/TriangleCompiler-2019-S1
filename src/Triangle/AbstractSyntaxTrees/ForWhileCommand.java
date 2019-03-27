package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForWhileCommand extends Command {

	public ForWhileCommand (Identifier iAst, Expression eAST1, Expression eAST2, Expression eAST3, Command cAST, SourcePosition thePosition) {
		super (thePosition);
		I = iAst;
		E1 = eAST1;
		E2 = eAST2;
		E3 = eAST3;
		C = cAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForWhileCommand(this, o);
	}

	public Identifier I;
	public Expression E1, E2, E3;
	public Command C;
}

