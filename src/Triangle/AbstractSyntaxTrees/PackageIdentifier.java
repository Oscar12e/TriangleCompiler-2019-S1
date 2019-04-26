package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class PackageIdentifier extends Identifier {
	public PackageIdentifier(String theSpelling, SourcePosition thePosition){
		super(theSpelling,thePosition);
	}

	public Object visit(Visitor v, Object o) { return v.visitPackageIdentifier(this, o); }
}
