package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {

    public static class State {
        private final int size = 3;
        private final Character[][] cells  = new Character[size][size];
        private String phase = "RUNNING";
        private boolean crossesMove = true;
        private int free = size * size;

        public int getSize() {
            return size;
        }

        public Character[][] getCells() {
            return cells;
        }

        public String getPhase() {
            return phase;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        private void findWinner(boolean swap) {
            int diag0 = 0;
            int diagX = 0;
            for (int i = 0; i < size; ++i) {
                int sumX = 0;
                int sum0 = 0;
                for (int j = 0; j < size; ++j) {
                    int realX = swap? j : i;
                    int realY = swap? i : j;
                    if (cells[realX][realY] != null) {
                        if (cells[realX][realY] == 'X') {
                            sumX++;
                            if (!swap && i == j) diagX++;
                            if (swap && realX == size - realY - 1) diagX++;
                        }
                        if (cells[realX][realY] == '0') {
                            sum0++;
                            if (!swap && i == j) diag0++;
                            if (swap && realX == size - realY - 1) diag0++;
                        }
                    }
                }
                if (sum0 == 3 || diag0 == 3) {
                    phase = "WON_O";
                    return;
                }
                if (sumX == 3 || diagX == 3) {
                    phase = "WON_X";
                    return;
                }
            }
        }

        private void makeMove(int x, int y) {
            if (!phase.equals("RUNNING") || cells[x][y] != null) {
                return;
            }

            if (crossesMove) {
                cells[x][y] = 'X';
            } else {
                cells[x][y] = '0';
            }
            free--;
            crossesMove = !crossesMove;
            findWinner(false);
            findWinner(true);

            if (phase.equals("RUNNING") && free == 0) {
                phase = "DRAW";
            }
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        if (session.getAttribute("state") == null) {
            State board = new State();
            session.setAttribute("state", board);
        }
        view.put("state", session.getAttribute("state"));
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        int x = -1;
        int y = -1;
        for (String attribute: request.getParameterMap().keySet()) {
            if (attribute.startsWith("cell_")) {
                x = attribute.charAt(attribute.length() - 2) - '0';
                y = attribute.charAt(attribute.length() - 1) - '0';
                break;
            }
        }

        HttpSession session = request.getSession();
        State board = (State) session.getAttribute("state");
        board.makeMove(x, y);
        session.setAttribute("state", board);
        view.put("state", board);
    }

    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().setAttribute("state", new State());
        action(request, view);
    }
}
