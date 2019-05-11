package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialProcFuncs extends ProcFuncs {
	public SequentialProcFuncs(ProcFuncs rAST1, ProcFuncs rAST2, SourcePosition thePosition) {
		super(thePosition);
		R1 = rAST1;
		R2 = rAST2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitSequentialProcFuncs(this, o);
	}
	public ProcFuncs R1;
	public ProcFuncs R2;

	@Override
	public Object visitTwo(Visitor v, Object o) {
		return v.visitSequentialProcFuncsTwo(this, o);
	}
}