package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class RecursiveFunc extends ProcFuncs {

	public RecursiveFunc(Identifier iAST, FormalParameterSequence fAST, TypeDenoter tAST, Expression eAST, SourcePosition thePosition) {
		super(thePosition);
		I = iAST;
		F = fAST;
		T = tAST;
		E = eAST;

	}

	public Object visit(Visitor v, Object o) { return v.visitRecursiveFunc(this, o); }
	public Object visitTwo(Visitor v, Object o) { return v.visitRecursiveFuncTwo(this, o); }

	public Identifier I;
	public FormalParameterSequence F;
	public TypeDenoter T;
	public Expression E;
}
