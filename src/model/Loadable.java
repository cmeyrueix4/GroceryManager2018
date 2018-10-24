package model;

import model.exceptions.CategoryException;

import java.io.IOException;

public interface Loadable {
    void loadBuy() throws IOException, CategoryException;
}
