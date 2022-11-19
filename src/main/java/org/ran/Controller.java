package org.ran;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class Controller {

    public Text ready1;
    public Text ready2;
    public Text ready3;
    public Text ready4;
    public ProgressBar t1p1;
    public ProgressBar t2p1;
    public ProgressBar t3p1;
    public ProgressBar t4p1;
    public ProgressBar t1p2;
    public ProgressBar t2p2;
    public ProgressBar t3p2;
    public ProgressBar t4p2;
    public Button buttonStart;
    public Button buttonReset;

    public void start(ActionEvent actionEvent) {
        buttonStart.setDisable(true);
        buttonReset.setDisable(false);
        Task ta1 = new T1Task(t1p1, t1p2, ready1, 10);
        Task ta2 = new T2Task(t2p1, t2p2, ready2, 20);
        Task ta3 = new T3Task(t3p1, t3p2, ready3, 5);
        Task ta4 = new T4Task(t4p1, t4p2, ready4, 14);

        Thread t1 = new Thread(ta1);
        Thread t2 = new Thread(ta2);
        Thread t3 = new Thread(ta3);
        Thread t4 = new Thread(ta4);
        ta1.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (Global3.ready1 == 0) {
                    t1p1.setProgress(t1.doubleValue());
                }else if(Global3.ready1 == 1 && Global3.running1 == 0){
                    ready1.setVisible(true);
                }else {
                    ready1.setVisible(false);
                    t1p2.setProgress(t1.doubleValue());
                }
            }
        });
        ta2.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (Global3.ready2 == 0) {
                    t2p1.setProgress(t1.doubleValue());
                }else if(Global3.ready2 == 1 && Global3.running2 == 0){
                    ready2.setVisible(true);
                }else {
                    ready2.setVisible(false);
                    t2p2.setProgress(t1.doubleValue());
                }
            }
        });
        ta3.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (Global3.ready3 == 0) {
                    t3p1.setProgress(t1.doubleValue());
                }else if(Global3.ready3 == 1 && Global3.running3 == 0){
                    ready3.setVisible(true);
                }else {
                    ready3.setVisible(false);
                    t3p2.setProgress(t1.doubleValue());
                }
            }
        });
        ta4.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (Global3.ready4 == 0) {
                    t4p1.setProgress(t1.doubleValue());
                }else if(Global3.ready4 == 1 && Global3.running4 == 0){
                    ready4.setVisible(true);
                }else {
                    ready4.setVisible(false);
                    t4p2.setProgress(t1.doubleValue());
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public void reset(ActionEvent actionEvent) {
        buttonStart.setDisable(false);
        buttonReset.setDisable(true);
        Global3.busy = 0;
        Global3.running1 = 0;
        Global3.running2 = 0;
        Global3.running3 = 0;
        Global3.running4 = 0;

        Global3.ready1 = 0;
        Global3.ready2 = 0;
        Global3.ready3 = 0;
        Global3.ready4 = 0;

        Global3.readyQueue.clear();

        t1p1.setProgress(0);
        t1p2.setProgress(0);
        t2p1.setProgress(0);
        t2p2.setProgress(0);
        t3p1.setProgress(0);
        t3p2.setProgress(0);
        t4p1.setProgress(0);
        t4p2.setProgress(0);
    }
}

class T1Task extends Task {
    ProgressBar bar1;
    ProgressBar bar2;
    Text ready;
    int speed;

    public T1Task(ProgressBar bar1, ProgressBar bar2, Text ready, int speed) {
        this.bar1 = bar1;
        this.bar2 = bar2;
        this.ready = ready;
        this.speed = speed;
    }

    public T1Task() {
    }

    @Override
    protected void updateProgress(long l, long l1) {
        super.updateProgress(l, l1);
    }

    @Override
    protected Object call() throws Exception {
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(speed);
            this.updateProgress(i, 100);
        }
        Global3.readyQueue.add(bar2);
        Global3.ready1 = 1;
        this.updateProgress(0, 100);
        while (true) {
            if (Global3.busy == 0 && Global3.readyQueue.peek() == bar2) {
                Global3.running1 = 1;
                Global3.busy = 1;
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(15);
                    this.updateProgress(i, 100);
                }
                Global3.busy = 0;
                Global3.readyQueue.poll();
                break;
            }
            System.out.println("test1");
        }
        return null;
    }
}

class T2Task extends Task {
    ProgressBar bar1;
    ProgressBar bar2;
    Text ready;
    int speed;

    public T2Task(ProgressBar bar1, ProgressBar bar2, Text ready, int speed) {
        this.bar1 = bar1;
        this.bar2 = bar2;
        this.ready = ready;
        this.speed = speed;
    }

    public T2Task() {
    }

    @Override
    protected void updateProgress(long l, long l1) {
        super.updateProgress(l, l1);
    }

    @Override
    protected Object call() throws Exception {
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(speed);
            this.updateProgress(i, 100);
        }
        Global3.readyQueue.add(bar2);
        Global3.ready2 = 1;
        this.updateProgress(0, 100);
        while (true) {
            if (Global3.busy == 0 && Global3.readyQueue.peek() == bar2) {
                Global3.running2 = 1;
                Global3.busy = 1;
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(15);
                    this.updateProgress(i, 100);
                    System.out.println(i);
                }
                Global3.busy = 0;
                Global3.readyQueue.poll();
                break;
            }
            System.out.println("test2");
        }
        return null;
    }
}
class T3Task extends Task {
    ProgressBar bar1;
    ProgressBar bar2;
    Text ready;
    int speed;

    public T3Task(ProgressBar bar1, ProgressBar bar2, Text ready, int speed) {
        this.bar1 = bar1;
        this.bar2 = bar2;
        this.ready = ready;
        this.speed = speed;
    }

    public T3Task() {
    }

    @Override
    protected void updateProgress(long l, long l1) {
        super.updateProgress(l, l1);
    }

    @Override
    protected Object call() throws Exception {
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(speed);
            this.updateProgress(i, 100);
        }
        Global3.readyQueue.add(bar2);
        Global3.ready3 = 1;
        this.updateProgress(0, 100);
        while (true) {
            if (Global3.busy == 0 && Global3.readyQueue.peek() == bar2) {
                Global3.running3 = 1;
                Global3.busy = 1;
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(15);
                    this.updateProgress(i, 100);
                    System.out.println(i);
                }
                Global3.busy = 0;
                Global3.readyQueue.poll();
                break;
            }
            System.out.println("test3");
        }
        return null;
    }
}

class T4Task extends Task {
    ProgressBar bar1;
    ProgressBar bar2;
    Text ready;
    int speed;

    public T4Task(ProgressBar bar1, ProgressBar bar2, Text ready, int speed) {
        this.bar1 = bar1;
        this.bar2 = bar2;
        this.ready = ready;
        this.speed = speed;
    }

    public T4Task() {
    }

    @Override
    protected void updateProgress(long l, long l1) {
        super.updateProgress(l, l1);
    }

    @Override
    protected Object call() throws Exception {
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(speed);
            this.updateProgress(i, 100);
        }
        Global3.readyQueue.add(bar2);
        Global3.ready4 = 1;
        this.updateProgress(0, 100);
        while (true) {
            if (Global3.busy == 0 && Global3.readyQueue.peek() == bar2) {
                Global3.running4 = 1;
                Global3.busy = 1;
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(15);
                    this.updateProgress(i, 100);
                    System.out.println(i);
                }
                Global3.busy = 0;
                Global3.readyQueue.poll();
                break;
            }
            System.out.println("test4");
        }
        return null;
    }
}
class P2Task extends Task {
    @Override
    protected void updateProgress(long l, long l1) {
        super.updateProgress(l, l1);
    }

    @Override
    protected Object call() throws Exception {
        while (true) {
            if (!Global3.readyQueue.isEmpty()) {
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(30);
                    this.updateProgress(i, 100);
                }
                Global3.readyQueue.poll();
            }
        }
    }
}

