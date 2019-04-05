package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForCommand extends Command {

	public ForCommand (ForDeclaration fAst, Command cAST, SourcePosition thePosition) {
		super (thePosition);
		F = fAst;
		C = cAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForCommand(this, o);
	}

	public ForDeclaration F;
	public Command C;
}