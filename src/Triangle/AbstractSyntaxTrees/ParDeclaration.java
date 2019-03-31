package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ParDeclaration extends Declaration {
	public ParDeclaration(Declaration dAST, SourcePosition thePosition) {
		super(thePosition);
		D = dAST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitParDeclaration(this, o);
	}

	Declaration D;
}
