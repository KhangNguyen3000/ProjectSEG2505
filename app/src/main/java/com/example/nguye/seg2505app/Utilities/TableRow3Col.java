package com.example.nguye.seg2505app.Utilities;

/**
 * From https://stackoverflow.com/questions/2670982/using-pairs-or-2-tuples-in-java
 * Used to store ANY set of 3 types in a single object
 * @param <Col1>
 * @param <Col2>
 * @param <Col3>
 */
public class TableRow3Col<Col1, Col2, Col3> {

    private Col1 col1;
    private Col2 col2;
    private Col3 col3;

    public TableRow3Col(Col1 col1, Col2 col2, Col3 col3) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
    }


}
