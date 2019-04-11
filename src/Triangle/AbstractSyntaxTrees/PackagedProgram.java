package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class PackagedProgram extends Program {
	public PackagedProgram(Package pAST, Command cAST, SourcePosition thePosition){
		super(thePosition);
		C = cAST;
		P = pAST;

	}

	@Override
	public Object visit(Visitor v, Object o) { return v.visitPackagedProgram(this, o); }

	public Command C;
	public Package P;
}
