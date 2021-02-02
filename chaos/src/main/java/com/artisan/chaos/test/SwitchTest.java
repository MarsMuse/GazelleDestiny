package com.artisan.chaos.test;

import java.util.Objects;

import static com.artisan.chaos.test.SwitchTest.Action.*;


public class SwitchTest {


    public static void main(String[] args) {
        Action[] actionArray = Action.values();
        for (Action action : actionArray) {
            switch (action) {
                case CREATE:
                case UNCHANGED:
                case DELETE:
                    System.out.println("DELETE-" + action.name());
                    break;
                case UPDATE:
                    System.out.println("UPDATE-" + action.name());
                default:
                    System.out.println("DEFAULT-" + action.name());
                    break;
            }
        }
    }

    public static enum Action {
        CREATE, UPDATE, DELETE, UNCHANGED
    }
}
