package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

import java.util.List;

public class Case extends Cases {

	public Case (Case cAst1, Case cAst2, SourcePosition thePosition) {
		super (thePosition);
		C1 = cAst1;
		C2 = cAst2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCaseLiterals(this, o);
	}
	public Case C1;
	public Case C2;
}
