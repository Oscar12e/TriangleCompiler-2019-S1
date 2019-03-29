package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ElseCase extends Cases {

	public ElseCase (Command cAST, SourcePosition thePosition) {
		super (thePosition);
		C = cAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitElseCase(this, o);
	}
	public Command C;
}