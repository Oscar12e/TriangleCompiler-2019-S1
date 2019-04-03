package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LongIdentifier extends Identifier {
	public LongIdentifier(Package pAST, Identifier iAST, SourcePosition thePosition){
		super(iAST.spelling, thePosition);
		P = pAST;
		I = iAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitLongIdentifier(this, o); }

	public Package P;
	public Identifier I;
}
