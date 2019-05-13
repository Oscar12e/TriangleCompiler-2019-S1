package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class RecursiveProc extends ProcFuncs {

	public RecursiveProc(Identifier iAST, FormalParameterSequence fAST, Command cAST, SourcePosition thePosition) {
		super(thePosition);
		C = cAST;
		I = iAST;
		F = fAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitRecursiveProc(this, o); }
	public Object visitTwo(Visitor v, Object o) { return v.visitRecursiveProcTwo(this, o); }

	public Identifier I;
	public FormalParameterSequence F;
	public Command C;

}
