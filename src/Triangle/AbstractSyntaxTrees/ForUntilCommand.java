package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForUntilCommand extends Command {

	public ForUntilCommand (ForDeclaration fAST, Expression eAST1, Expression eAST2, Command cAst, SourcePosition thePosition) {
		super (thePosition);
		F = fAST;
		E1 = eAST1;
		E2 = eAST2;
		C = cAst;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitForUntilCommand(this, o);
	}

	public ForDeclaration F;
	public Expression E1, E2;
	public Command C;
}