package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialPackages extends Package {
	public SequentialPackages(Package pAST1, Package pAST2, SourcePosition thePosition){
		super(thePosition);
		P1 = pAST1;
		P2 = pAST2;
	}

	public Object visit(Visitor v, Object o) { return v.visitSequentialPackages(this, o); }

	Package P1, P2;
}
