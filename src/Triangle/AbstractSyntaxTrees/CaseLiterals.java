package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

import java.util.List;


public class CaseLiterals extends Cases {

	public CaseLiterals (List<CaseRange> rAsts, SourcePosition thePosition) {
		super (thePosition);
		R = rAsts;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCaseLiterals(this, o);
	}
	public List<CaseRange> R;
}
