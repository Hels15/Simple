package com.seaofnodes.simple.node.cpus.arm;

import com.seaofnodes.simple.*;
import com.seaofnodes.simple.codegen.*;
import com.seaofnodes.simple.node.*;


public class CondAndARM extends MachConcreteNode implements MachNode {
    CondAndARM(Node or ) { super(or); }
    @Override public String op() { return "CondAnd"; }
    @Override public RegMask regmap(int i) {
        return arm.RMASK;
    }
    @Override public RegMask outregmap() { return arm.WMASK; }

    @Override public void encoding( Encoding enc ) {

    }

    @Override public void asm(CodeGen code, SB sb) {
        sb.p(code.reg(this)).p(" = ").p(code.reg(in(1))).p(" && ").p(code.reg(in(2)));
    }
}
