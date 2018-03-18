package com.infotech.cms.domain;

/**
 * sort expression for general purposes.
 *
 * @author Mohamamd Reza Alagheband
 *
 */
public class SortExpression {

    public enum Direction {
        ASC,
        DESC
    }

    private String field;
    private Direction direction;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }    

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
