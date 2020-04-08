package edu.parser.code;

public class Program {
    private Block block;

    public Program(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void process(){
        block.process();
    }
}
