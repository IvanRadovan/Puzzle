package puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Graphics extends JFrame {

    private final static int ROWS = 4;
    private final static int COLUMNS = 4;

    private JPanel panel = new JPanel();
    private JButton[][] tiles = new JButton[ROWS][COLUMNS];

//    private enum direction {
//
//        CENTER(0, 0),
//        NORTH(-1, 0),
//        SOUND(1, 0),
//        EAST(0, 1),
//        WEST(1, 0);
//
//        private final int x;
//        private final int y;
//
//        direction(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public int getX() {
//            return x;
//        }
//
//        public int getY() {
//            return y;
//        }
//    }

    public Graphics() {
        add(panel);
        panel.setLayout(new GridLayout(ROWS, COLUMNS));
        addTiles();


        try {
            isClickable();
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

//    private JButton emptyTileFinder() {
//        for (JButton[] rows : tiles) {
//            for (JButton currentTile : rows) {
//                if (!currentTile.isEnabled())
//                    return currentTile;
//            }
//        }
//        return null;
//    }
//
//    private int[] emptyTileFinderPosition() {
//        for (int rows = 0; rows < COLUMNS; rows++) {
//            for (int columns = 0; columns < ROWS; columns++) {
//                JButton currentTile = tiles[rows][columns];
//                if (!currentTile.isEnabled()) {
//                    return new int[] {rows, columns};
//                }
//            }
//        }
//        return null;
//    }




    private void addTiles() {
        int counter = 1;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (counter <= 15) {
                    tiles[row][column] = new JButton(String.valueOf(counter++));
                    panel.add(tiles[row][column]);
                } else {
                    tiles[row][column] = new JButton();
                    tiles[row][column].setEnabled(false);
                    panel.add(tiles[row][column]);
                }
            }
        }
    }

//    public void swap(int x, int y) {
//        for (int row = 0; row < tiles.length; row++) {
//            for (int column = 0; column < tiles[row].length; column++) {
//                if (!tiles[row][column].isEnabled()) {
//                    var temp = tiles[row][column];
//                    tiles[row][column] = tiles[row + (x)][column + (y)];
//                    tiles[row + (x)][column + (y)] = temp;
//                    revalidate();
//                    repaint();
//                    return;
//                }
//            }
//        }
//    }

    private void isClickable() {
            for (int row = 0; row < tiles.length; row++) {
                for (int column = 0; column < tiles[row].length; column++) {
                    if (!tiles[row][column].isEnabled()) {
                        int finalRow = row;
                        int finalColumn = column;
                        if (finalColumn > 0) {
                            tiles[finalRow][finalColumn - 1].addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    // Swap the text of the buttons
                                    String temp = tiles[finalRow][finalColumn].getText();
                                    tiles[finalRow][finalColumn].setText(tiles[finalRow][finalColumn - 1].getText());
                                    tiles[finalRow][finalColumn - 1].setText(temp);

                                    // Swap enabled
                                    tiles[finalRow][finalColumn].setEnabled(true);
                                    tiles[finalRow][finalColumn - 1].setEnabled(false);

                                    // Swap the button references in the array
                                    JButton tempButton = tiles[finalRow][finalColumn];
                                    tiles[finalRow][finalColumn] = tiles[finalRow][finalColumn - 1];
                                    tiles[finalRow][finalColumn - 1] = tempButton;

                                    revalidate();
                                    repaint();
                                }
                            });
                            tiles[finalRow - 1][finalColumn].addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    // Swap the text of the buttons
                                    String temp = tiles[finalRow][finalColumn].getText();
                                    tiles[finalRow][finalColumn].setText(tiles[finalRow - 1][finalColumn].getText());
                                    tiles[finalRow - 1][finalColumn].setText(temp);

                                    // Swap enabled
                                    tiles[finalRow][finalColumn].setEnabled(true);
                                    tiles[finalRow - 1][finalColumn].setEnabled(false);

                                    // Swap the button references in the array
                                    JButton tempButton = tiles[finalRow][finalColumn];
                                    tiles[finalRow][finalColumn] = tiles[finalRow - 1][finalColumn];
                                    tiles[finalRow - 1][finalColumn] = tempButton;

                                    revalidate();
                                    repaint();

                                }
                            });
                            tiles[finalRow + 1][finalColumn].addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    // Swap the text of the buttons
                                    String temp = tiles[finalRow][finalColumn].getText();
                                    tiles[finalRow][finalColumn].setText(tiles[finalRow + 1][finalColumn].getText());
                                    tiles[finalRow + 1][finalColumn].setText(temp);

                                    // Swap enabled
                                    tiles[finalRow][finalColumn].setEnabled(true);
                                    tiles[finalRow + 1][finalColumn].setEnabled(false);

                                    // Swap the button references in the array
                                    JButton tempButton = tiles[finalRow][finalColumn];
                                    tiles[finalRow][finalColumn] = tiles[finalRow + 1][finalColumn];
                                    tiles[finalRow + 1][finalColumn] = tempButton;

                                    revalidate();
                                    repaint();
                                }
                            });
                            tiles[finalRow][finalColumn + 1].addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    // Swap the text of the buttons
                                    String temp = tiles[finalRow][finalColumn].getText();
                                    tiles[finalRow][finalColumn].setText(tiles[finalRow][finalColumn + 1].getText());
                                    tiles[finalRow][finalColumn + 1].setText(temp);

                                    // Swap enabled
                                    tiles[finalRow][finalColumn].setEnabled(true);
                                    tiles[finalRow][finalColumn + 1].setEnabled(false);

                                    // Swap the button references in the array
                                    JButton tempButton = tiles[finalRow][finalColumn];
                                    tiles[finalRow][finalColumn] = tiles[finalRow][finalColumn + 1];
                                    tiles[finalRow][finalColumn + 1] = tempButton;

                                    revalidate();
                                    repaint();
                                }
                            });
                        }

                    }

                }

            }
    }


    public static void main(String[] args) {
        Graphics graphics = new Graphics();

    }
}
