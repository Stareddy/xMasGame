package game;

import java.sql.*;
import java.util.Random;

class HauptKlasse {
    private GameJFrame jaraspijFrame;

    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static int countSolvedWords = 0;

    HauptKlasse(GameJFrame JaRasPiJFrame) {
        this.jaraspijFrame = JaRasPiJFrame;
    }

    void startGame() {
        Random ran = new Random();
        int randomNum = ran.nextInt(152) + 1;
        System.out.println(randomNum);
        try {
            connectToDB();

            resultSet = statement
                    .executeQuery("SELECT * FROM xmasgame.words");

            while (resultSet.next()) {
                int rang = resultSet.getInt("rang");
                int flag = resultSet.getInt("flag");
                String word = resultSet.getString("word");

                if (rang == randomNum) {
                    if (flag == 1) {
                        jaraspijFrame.jTextField.setText(word);

                        preparedStatement = conn.prepareStatement("update xmasgame.words "
                                + "set flag=? where rang=?");
                        preparedStatement.setInt(1, 2);
                        preparedStatement.setInt(2, rang);
                        preparedStatement.executeUpdate();
                        jaraspijFrame.currentTime.setText("Anzahl erratener Wörter");
                    }
                }
            }

        } catch (Exception e) {
            System.exit(0);
        }
    }

    private void connectToDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager
                .getConnection("jdbc:mysql://localhost/xmasgame?", "xmasplayer", "XMas");

        statement = conn.createStatement();
    }

    void nextWord() {
        Random ran = new Random();

        int nextWordToSearch = 1;

        try {
            connectToDB();
            int maxValue = 0;
            while (nextWordToSearch == 1 && maxValue < 2000) {
                System.out.println(maxValue);
                resultSet = statement
                        .executeQuery("SELECT * FROM xmasgame.words");

                int randomNum = ran.nextInt(152) + 1;

                while (resultSet.next()) {
                    int rang = resultSet.getInt("rang");
                    int flag = resultSet.getInt("flag");
                    String word = resultSet.getString("word");

                    if (rang == randomNum) {
                        if (flag == 1) {
                            jaraspijFrame.jTextField.setText(word);
                            preparedStatement = conn.prepareStatement("update xmasgame.words "
                                    + "set flag=? where rang=?");
                            preparedStatement.setInt(1, 2);
                            preparedStatement.setInt(2, rang);
                            preparedStatement.executeUpdate();
                            nextWordToSearch = 2;
                            countSolvedWords++;
                            jaraspijFrame.currentTime.setText("Anzahl erratener Wörter: " + countSolvedWords);
                        }
                    }
                }
                maxValue++;
                if (maxValue == 1999)
                    clearFlag();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    void clearFlag() {

        try {
            connectToDB();

            preparedStatement = conn.prepareStatement("update xmasgame.words "
                    + "set flag=?");
            preparedStatement.setInt(1, 1);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
