package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class PackageIdentifier extends Identifier {
	public PackageIdentifier(Identifier iAST, SourcePosition thePosition){
		super(iAST.spelling,thePosition);
		I = iAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitPackageIdentifier(this, o); }

	public Identifier I;
}
