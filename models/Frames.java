package models;

import java.util.Stack;

public class Frames {
    private Stack<Frame> frames;

    public Frames() {
        frames = new Stack<Frame>();
        frames.push(new Frame());
    }

    public Frame currentFrame() {
        return frames.peek();
    }

    public void newFrame() {
        frames.push(new Frame(currentFrame().getVariables()));
    }

    public Frame dropFrame() {
        Frame dropped = frames.pop();
        currentFrame().extendVariables(dropped);
        return dropped;
    }
}