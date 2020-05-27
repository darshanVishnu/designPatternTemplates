package visitorPattern.util;

import visitorPattern.visitor.Visitor;

public interface Element {
    void accept(Visitor visitor);
}
