package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LongVname extends Vname {

	public LongVname(Package pAST, Vname vAST, SourcePosition thePosition) {
		super (thePosition);
		V = vAST;
		P = pAST;
	}

	public Object visit (Visitor v, Object o) {
		return v.visitLongVName(this, o);
		//return null;
	}

	public Package P;

	public Vname V;
}