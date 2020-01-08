package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrameHandler implements ActionListener {
    private GameJFrame jFrame;
    private String befehlvomFrame = "";

    GameFrameHandler(GameJFrame gameJFrame) {
        this.jFrame = gameJFrame;
    }

    public void actionPerformed(ActionEvent ae) {
        HauptKlasse startJob = new HauptKlasse(jFrame);

        befehlvomFrame = ae.getActionCommand();

        switch (befehlvomFrame) {
            case "Spiel starten":
                startGame(startJob, jFrame);
                break;
            case "Nächstes Wort":
                getNextWordFromDB(startJob, jFrame);
                break;
            case "Spiel beenden":
                stopGame(startJob, jFrame);
                break;
        }
    }

    private void startGame(HauptKlasse startJob, GameJFrame jFrame) {
        startJob.clearFlag();
        startJob.startGame();
        jFrame.nextWord.grabFocus();
    }

    private void getNextWordFromDB(HauptKlasse startJob, GameJFrame jFrame) {
        startJob.nextWord();
        jFrame.nextWord.setBackground(Color.GREEN);
        jFrame.nextWord.grabFocus();
    }

    private void stopGame(HauptKlasse startJob, GameJFrame jFrame) {
        startJob.clearFlag();
        jFrame.startStopGame.setText("Spiel starten");
        jFrame.startStopGame.setBackground(null);
        jFrame.nextWord.setBackground(null);
        jFrame.nextWord.setEnabled(false);
        jFrame.currentTime.setText("");
        jFrame.jTextField.setText("");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
