package com.example.tasktodo8d.controllers;

/**
 * The Modeable interface represents an object that can change its mode.
 * It provides methods to set the mode to light or dark.
 */
public interface Modeable {

    /**
     * Changes the mode of the object.
     */
    void changeMode();

    /**
     * Sets the mode to light.
     */
    void setLightMode();

    /**
     * Sets the mode to dark.
     */
    void setDarkMode();

}
