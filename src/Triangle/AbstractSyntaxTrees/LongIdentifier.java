package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LongIdentifier extends Package {
	public LongIdentifier(PackageIdentifier pAST, Identifier iAST, SourcePosition thePosition){
		super(thePosition);
		P = pAST;
		I = iAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitLongIdentifier(this, o); }

	PackageIdentifier P;
	Identifier I;
}
