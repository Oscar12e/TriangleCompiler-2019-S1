package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LongIdentifier extends Identifier {
	public LongIdentifier(Identifier pAST, String theSpelling, SourcePosition thePosition){
		super(theSpelling, thePosition);
		P = pAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitLongIdentifier(this, o); }

	public Identifier P;
}
