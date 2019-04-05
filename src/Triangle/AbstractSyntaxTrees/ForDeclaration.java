package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForDeclaration extends Declaration {

    public ForDeclaration(Identifier iAST, Expression eAST1, Expression eAST2, SourcePosition pos){
        super(pos);
        I = iAST;
        E1 = eAST1;
        E2 = eAST2;
    }

    public Object visit(Visitor v, Object o) {
        return v.visitForDeclaration(this, o);
    }

    public Identifier I;
    public Expression E1, E2;
}
