package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

import java.util.List;

public class CaseRange extends Cases {

	public CaseRange (List<CaseLiteral> lAsts, SourcePosition thePosition) {
		super (thePosition);
		L = lAsts;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCaseRange(this, o);
	}

	public List<CaseLiteral> L;
}