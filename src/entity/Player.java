package entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static entity.Entity.Direction.DOWN;

public final class Player extends Entity {
    private KeyHandler keyHandler;

    public class KeyHandler implements KeyListener {
        private boolean isUpPressed;
        private boolean isDownPressed;
        private boolean isLeftPressed;
        private boolean isRightPressed;

        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            int code = keyEvent.getKeyCode();

            switch (code) {
                case KeyEvent.VK_W:
                    isUpPressed = true;
                    break;
                case KeyEvent.VK_S:
                    isDownPressed = true;
                    break;
                case KeyEvent.VK_A:
                    isLeftPressed = true;
                    break;
                case KeyEvent.VK_D:
                    isRightPressed = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            int code = keyEvent.getKeyCode();

            switch (code) {
                case KeyEvent.VK_W:
                    isUpPressed = false;
                    break;
                case KeyEvent.VK_S:
                    isDownPressed = false;
                    break;
                case KeyEvent.VK_A:
                    isLeftPressed = false;
                    break;
                case KeyEvent.VK_D:
                    isRightPressed = false;
                    break;
            }
        }

        public boolean isUpPressed() {
            return isUpPressed;
        }

        public boolean isDownPressed() {
            return isDownPressed;
        }

        public boolean isLeftPressed() {
            return isLeftPressed;
        }

        public boolean isRightPressed() {
            return isRightPressed;
        }
    }

    public Player(int horizontalCoordinate, int verticalCoordinate, int height, int width) {
        super(horizontalCoordinate,
                verticalCoordinate,
                height,
                width,
                4,
                20,
                20,
                DOWN,
                "/player/boy_up_1.png",
                "/player/boy_up_2.png",
                "/player/boy_down_1.png",
                "/player/boy_down_2.png",
                "/player/boy_left_1.png",
                "/player/boy_left_2.png",
                "/player/boy_right_1.png",
                "/player/boy_right_2.png");
        keyHandler = new KeyHandler();
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}
