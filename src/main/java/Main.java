
import asciiPanel.AsciiPanel;

import java.awt.Container;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.util.Scanner;
import java.util.Random;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


import javax.swing.JFrame;

import asciiPanel.AsciiPanel;

class ApplicationMain extends JFrame {
    private static final long serialVersionUID = 1060623638149583738L;

    private AsciiPanel terminal;


    public ApplicationMain() {
        super();
        terminal = new AsciiPanel();
      /* Tut Leider Nicht
        terminal.write("┌──────────────────────────────────────────────────────┐", 1, 1);
        terminal.write("│                                                      │", 1, 12);
        terminal.write("└──────────────────────────────────────────────────────┘", 1, 13);
        */
        terminal.write("####################################################", 1, 1);
        terminal.write("#                                                  #  Speed:", 1, 2);
        terminal.write("#                                                  #", 1, 3);
        terminal.write("#                                                  #  Score:", 1, 4);
        terminal.write("#                                                  #  High Score:", 1, 5);
        terminal.write("#                                                  #", 1, 6);
        terminal.write("#                                                  #", 1, 7);
        terminal.write("#                                                  #", 1, 8);
        terminal.write("#                                                  #", 1, 9);
        terminal.write("#                                                  #", 1, 10);
        terminal.write("#                                                  #", 1, 11);
        terminal.write("#                                                  #", 1, 12);
        terminal.write("#                                                  #", 1, 13);
        terminal.write("#                                                  #", 1, 14);
        terminal.write("#                                                  #", 1, 15);
        terminal.write("#                                                  #", 1, 16);
        terminal.write("#                                                  #", 1, 17);
        terminal.write("#                                                  #", 1, 18);
        terminal.write("#                                                  #", 1, 19);
        terminal.write("#                                                  #", 1, 20);
        terminal.write("#                                                  #", 1, 21);
        terminal.write("####################################################", 1, 22);


        add(terminal);
        pack();
    }

    public void doSonething(int iText) {
        terminal.write("Hallo" + iText, 20, 15);
    }
    public void doSonethingScore(int Score) {
        String text = "";
        text += Score +"      ";
        terminal.write( text, 62, 4);
    }

    public void doSonethingHighScore(int Score) {
        String text = "";
        text += Score +"      ";
        terminal.write( text, 67, 5);
    }

    public void doSonethingSpeed(int Speed) {
        String text = "";
        text += Speed +"  ";;
        terminal.write( text, 62, 2);
    }

    public void doSonething(int iType, int iX, int iY) {
        switch (iType) {
            case 0:
                terminal.write(" ", iX + 2, iY + 2);
                break;
            case 1:
                terminal.write("@", iX + 2, iY + 2);
                break;
            case 2:
                terminal.write("O", iX + 2, iY + 2);
                break;
            case 3:
                terminal.write("0", iX + 2, iY + 2);
                break;
            case 11:
                terminal.write("X", iX + 2, iY + 2);
                break;
            case 10:
                terminal.write("+", iX + 2, iY + 2);
                break;

        }


    }
}


public class Main {

    static int iMX = 50;
    static int iMY = 20;
    static int[][] iMatrix = new int[iMX][iMY];
    static int iDotX = 25;
    static int iDotY = 10;

    static int iScore = 0;
    static int iHighScore = 0;
    static int iCrash = 0;
    static int iTail = 0;
    static int iTimer = 100;
    static int iTimerCounter = 0;

    static int iEggCounter = 10;

    public static Random rand = new Random();

    public static void doTailCHeck(int iX, int iY, int iTailIndex) {
        if ((iX < 0) || (iX >= iMX) || (iY < 0) || (iY >= iMY))
            return;
        if ((iMatrix[iX][iY] < iTailIndex)||(iMatrix[iX][iY] > 500))
            return;

        doTailCHeck(iX + 1, iY, iMatrix[iX][iY]);
        doTailCHeck(iX - 1, iY, iMatrix[iX][iY]);
        doTailCHeck(iX, iY + 1, iMatrix[iX][iY]);
        doTailCHeck(iX, iY - 1, iMatrix[iX][iY]);

        iMatrix[iX][iY]++;
        if (iMatrix[iX][iY] > iTail + 2)
            iMatrix[iX][iY] = -1;
    }

    public static void main(String[] args) {

        final int[] iKeyCode = {0};
        int iLastKeyCommand = 0;
        for (int iX = 0; iX < iMX; iX++)
            for (int iY = 0; iY < iMY; iY++)
                iMatrix[iX][iY] = 0;


        JFrame frame = new JFrame("Key Listener");
        Container contentPane = frame.getContentPane();
        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                printEventInfo("Key Pressed", event);
            }

            @Override
            public void keyReleased(KeyEvent event) {
                printEventInfo("Key Released", event);
                iKeyCode[0] = event.getKeyCode();// getID();// getKeyLocation();// .getKeyCode();
            }

            @Override
            public void keyTyped(KeyEvent event) {
                printEventInfo("Key Typed", event);
            }

            private void printEventInfo(String str, KeyEvent e) {
                System.out.println(str);
                int code = e.getKeyCode();
                System.out.println("   Code: " + KeyEvent.getKeyText(code));
                System.out.println("   Char: " + e.getKeyChar());
                int mods = e.getModifiersEx();
                System.out.println("    Mods: "
                        + KeyEvent.getModifiersExText(mods));
                System.out.println("    Location: "
                        + keyboardLocation(e.getKeyLocation()));
                System.out.println("    Action? " + e.isActionKey());
            }

            private String keyboardLocation(int keybrd) {
                switch (keybrd) {
                    case KeyEvent.KEY_LOCATION_RIGHT:
                        return "Right";
                    case KeyEvent.KEY_LOCATION_LEFT:
                        return "Left";
                    case KeyEvent.KEY_LOCATION_NUMPAD:
                        return "NumPad";
                    case KeyEvent.KEY_LOCATION_STANDARD:
                        return "Standard";
                    case KeyEvent.KEY_LOCATION_UNKNOWN:
                    default:
                        return "Unknown";
                }
            }
        };

        System.out.println("Diif");
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.addKeyListener(listener);


        app.setVisible(true);
        app.doSonething(1, 25, 10);
        while (true) {
            int iCode = iKeyCode[0];
            if (iKeyCode[0] != 0) {
                iLastKeyCommand = iKeyCode[0];

                if (iKeyCode[0] == 27) {
                    app.setVisible(false);
                    System.exit(0);
                    return;
                }
                iKeyCode[0] = 0;
            }
            if (iCrash == 0) {
                if( iLastKeyCommand != 0 ) {
                    iEggCounter--;
                    if (iEggCounter == 0) {
                        int iRandX;
                        int iRandY;
                        do {
                            do {
                                iRandX = rand.nextInt(0, iMX - 1);
                            } while (Math.abs(iRandX - iDotX) < 3);
                            do {
                                iRandY = rand.nextInt(0, iMY - 1);
                            } while (Math.abs(iRandY - iDotY) < 3);
                        } while (iMatrix[iRandX][iRandY] != 0);
                        iMatrix[iRandX][iRandY] = 502;
                        iEggCounter = 25;
                    }

                    if ((iLastKeyCommand >= 37) && (iLastKeyCommand <= 40)) {
                        int OldX = iDotX;
                        int OldY = iDotY;
                        iMatrix[iDotX][iDotY] = -1;
                        switch (iLastKeyCommand) {
                            case 37:
                                iDotX--;
                                break;
                            case 39:
                                iDotX++;
                                break;
                            case 40:
                                iDotY++;
                                break;
                            case 38:
                                iDotY--;
                                break;
                        }

                        if ((iDotX < 0) || (iDotX >= iMX) || (iDotY < 0) || (iDotY >= iMY)) {
                            iCrash = 1;

                            iMatrix[OldX][OldY] = 501;
                        } else if ((iMatrix[iDotX][iDotY] > 1) && (iMatrix[iDotX][iDotY] < 500)) {
                            iCrash = 1;

                            iMatrix[OldX][OldY] = 501;
                        } else {
                            // loot Egg ?
                            if (iMatrix[iDotX][iDotY] == 502) {
                                iTail++;
                                iScore += 100;

                                iTimer += 10;
                                if( iTimer > 100 )
                                    iTimer = 100;

                            }
                            if (iTail > 0) {
                                iMatrix[OldX][OldY] = 2;


                                   doTailCHeck(OldX, OldY, 2);
                            }
                            iMatrix[iDotX][iDotY] = 1;

                            //Update Timer
                            iTimerCounter--;
                            if (iTimerCounter <= 0)
                            {
                                iScore += iTail+1;

                                iTimerCounter = 100 - iTimer;
                                if (iTimer > 2)
                                    iTimer--;
                            }

                        }
                    }
                }
            } else if (iCrash > 0) {
                iCrash++;
                for (int iX = 0; iX < iMX; iX++)
                    for (int iY = 0; iY < iMY; iY++) {
                        if (iMatrix[iX][iY] == 0) {
                            iMatrix[iX][iY] = 500;
                        } else if (iMatrix[iX][iY] == 500) {
                            iMatrix[iX][iY] = -1;
                        }
                    }
                if (iCrash > 25) {
                    for (int iX = 0; iX < iMX; iX++)
                        for (int iY = 0; iY < iMY; iY++) {
                            if (iMatrix[iX][iY] != 0) {
                                iMatrix[iX][iY] = -1;
                            }
                        }
                    iDotX = 25;
                    iDotY = 10;
                    iMatrix[iDotX][iDotY] = 1;
                    iLastKeyCommand = 0;
                    iTail = 0;
                    iCrash = 0;
                    iEggCounter = 10;
                    iTimer = 100;
                    iTimerCounter = 0;

                    if(iHighScore < iScore)
                        iHighScore = iScore;
                    iScore = 0;
                }
            }


            app.doSonethingHighScore(iHighScore);
            app.doSonethingScore(iScore);
            app.doSonethingSpeed( 101 - iTimer );
            for (int iX = 0; iX < iMX; iX++)
                for (int iY = 0; iY < iMY; iY++) {
                    if (iMatrix[iX][iY] != 0) {
                        switch (iMatrix[iX][iY]) {
                            case -1:
                                app.doSonething(0, iX, iY);
                                iMatrix[iX][iY] = 0;
                                break;
                            case 1://@
                                app.doSonething(1, iX, iY);
                                break;
                            case 501: //x
                                app.doSonething(11, iX, iY);
                                break;
                            case 500://+
                                app.doSonething(10, iX, iY);
                                break;
                            case 502://Ei
                                app.doSonething(3, iX, iY);
                                break;

                            default:
                                // Do Tail check
                              //  iMatrix[iX][iY]++;
                              //  if (iMatrix[iX][iY] > iTail + 2)
                              //      iMatrix[iX][iY] = -1;

                                //Daw
                                app.doSonething(2, iX, iY);
                                break;
                        }

                    }
                }
            app.repaint();// .update();
            try {
                Thread.sleep(iTimer);
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
}
