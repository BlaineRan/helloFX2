package org.ran;

import javafx.scene.control.ProgressBar;

import java.util.ArrayDeque;
import java.util.Deque;

public class Global3 {
    public static int ready1 = 0;
    public static int running1 = 0;
    public static int ready2 = 0;
    public static int running2 = 0;
    public static int ready3 = 0;
    public static int running3 = 0;
    public static int ready4 = 0;
    public static int running4 = 0;

    public static int busy = 0;
    public static Deque<ProgressBar> readyQueue = new ArrayDeque<>();

}
