package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class PackageDeclaration extends Package {
	public PackageDeclaration(Identifier pAST, Declaration dAST, SourcePosition thePosition){
		super(thePosition);
		P = pAST;
		D = dAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitPackageDeclaration(this, o); }

	public Identifier P;
	public Declaration D;
}