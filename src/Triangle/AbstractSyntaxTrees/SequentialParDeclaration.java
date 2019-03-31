package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialParDeclaration extends Declaration {
	public SequentialParDeclaration(Declaration dAST1, Declaration dAST2, SourcePosition thePosition) {
		super(thePosition);
		D1 = dAST1;
		D2 = dAST2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitSequentialParDeclaration(this, o);
	}
	Declaration D1;
	Declaration D2;
}
