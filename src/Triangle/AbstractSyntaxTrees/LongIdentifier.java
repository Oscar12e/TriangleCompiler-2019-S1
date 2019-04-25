package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LongIdentifier extends Identifier {
	public LongIdentifier(Identifier pAST, Identifier iAST, SourcePosition thePosition){
		super(pAST.spelling + "$" + iAST.spelling, thePosition);
		P = pAST;
		I = iAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitLongIdentifier(this, o); }

	public Identifier P;
	public Identifier I;
}
