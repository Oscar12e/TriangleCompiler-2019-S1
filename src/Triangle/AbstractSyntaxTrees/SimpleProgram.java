package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SimpleProgram extends Program {

	public SimpleProgram (Command cAST, SourcePosition thePosition) {
		super (thePosition);
		C = cAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitSimpleProgram(this, o);
	}

	public Command C;
}