package com.seaofnodes.simple.node.cpus.riscv;

import com.seaofnodes.simple.*;
import com.seaofnodes.simple.codegen.*;
import com.seaofnodes.simple.node.*;


public class CondAndRISC  extends MachConcreteNode implements MachNode {
    CondAndRISC( Node and ) { super(and); }
    @Override public String op() { return "CondAnd"; }
    @Override public RegMask regmap(int i) {
         return riscv.RMASK;
    }
    @Override public RegMask outregmap() { return riscv.WMASK; }

    @Override public void encoding( Encoding enc ) {

    }

    @Override public void asm(CodeGen code, SB sb) {
        sb.p(code.reg(this)).p(" = ").p(code.reg(in(1))).p(" && ").p(code.reg(in(2)));
    }

}
