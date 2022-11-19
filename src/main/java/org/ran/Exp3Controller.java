package org.ran;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Exp3Controller {
    public Text t1p1;
    public Text t1p2;
    public Text t2p1;
    public Text t3p1;
    public Text t4p1;
    public Text t2p2;
    public Text t3p2;
    public Text t4p2;
    public Button buttonStart;
    public Button buttonReset;
    @FXML
    Text ready1;
    @FXML
    Text ready2;
    @FXML
    Text ready3;



    public void start(ActionEvent actionEvent) {
        buttonStart.setDisable(true);
        buttonReset.setDisable(false);
        Task ta1 = new TestTask(t1p1,t1p2,ready1,20,1);
        Task ta2 = new TestTask(t2p1,t2p2,ready2,30,2);
        Task ta3 = new TestTask(t3p1,t3p2,ready3,40,3);
        Thread t1 = new Thread(ta1);
        Thread t2 = new Thread(ta2);
        Thread t3 = new Thread(ta3);

        t1.start();
        t2.start();
        t3.start();
        ta1.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                t1p1.setText(String.valueOf(t1.intValue()*10));
//                System.out.println((int)(t1.doubleValue()*100));
                if(Global3.ready1 == 0){
                    t1p1.setText(String.valueOf((int)(t1.doubleValue()*100)+1));
                }else{
                    t1p2.setText(String.valueOf((int)(t1.doubleValue()*100)));
                }

            }
        });
        ta2.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(Global3.ready2 == 0){
                    t2p1.setText(String.valueOf((int)(t1.doubleValue()*100)+1));
                }else{
                    System.out.println((int)(t1.doubleValue()*100));
                    t2p2.setText(String.valueOf((int)(t1.doubleValue()*100)));

                }
            }
        });
        ta3.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(Global3.ready3 == 0){
                    t3p1.setText(String.valueOf((int)(t1.doubleValue()*100)+1));
                }else{
                    t3p2.setText(String.valueOf((int)(t1.doubleValue()*100)));
                }
            }
        });
    }

    public void reset(ActionEvent actionEvent) {
        buttonStart.setDisable(false);
        buttonReset.setDisable(true);
    }
}

class TestThread implements Runnable{
    Text pro1;
    Text pro2;
    Text ready;
    int speed;
    int flag;

    public TestThread(Text pro1, Text pro2, Text ready, int speed, int flag) {
        this.pro1 = pro1;
        this.pro2 = pro2;
        this.ready = ready;
        this.speed = speed;
        this.flag = flag;
    }

    public TestThread() {
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pro1.setText(String.valueOf(i));
        }
    }
}

class TestTask extends Task {
    Text pro1;
    Text pro2;
    Text ready;
    int speed;
    int flag;

    public TestTask(Text pro1, Text pro2, Text ready, int speed, int flag) {
        this.pro1 = pro1;
        this.pro2 = pro2;
        this.ready = ready;
        this.speed = speed;
        this.flag = flag;
    }

    public TestTask() {
    }

    @Override
    protected void updateProgress(long l, long l1) {
        super.updateProgress(l, l1);
    }

    @Override
    protected Object call() throws Exception {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep((long) (speed));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.updateProgress(i,100);
//            pro1.setText(String.valueOf(i));
        }
//        Global3.readyQueue.addLast(pro2);
        ready.setVisible(true);
        if(flag == 1){
            Global3.ready1 = 1;
        }else if(flag == 2){
            Global3.ready2 = 1;
        }else if(flag == 3){
            Global3.ready3 = 1;
        }
//        while(true){
//            if(Global3.busy == 0 && Global3.readyQueue.peekLast() == pro2){
//                ready.setVisible(false);
//                Global3.busy = 1;
//                Global3.readyQueue.pollLast();
//                for (int i = 1; i <= 100; i++) {
//                    try {
//                        Thread.sleep(speed);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
////                    pro2.setText(String.valueOf(i));
//                    this.updateProgress(i,100);
//                }
//                Global3.busy = 0;
//                break;
//            }
//        }
        return null;
    }


}

