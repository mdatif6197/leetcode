import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];
        int sx = 0, sy = 0;
        int litter = 0;

        // Find S and assign an index to every L
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    id[i][j] = litter++;
                }
            }
        }

        if (litter == 0) return 0;

        int totalMasks = 1 << litter;

        // visited[row][col][energy][mask]
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        Queue<int[]> q = new LinkedList<>();

        // Initially all litter is uncollected
        int startMask = totalMasks - 1;

        q.offer(new int[]{sx, sy, energy, startMask});
        visited[sx][sy][energy][startMask] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();

                int x = cur[0];
                int y = cur[1];
                int e = cur[2];
                int mask = cur[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // Cannot move with zero energy
                if (e == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= m ||
                        ny < 0 || ny >= n ||
                        classroom[nx].charAt(ny) == 'X') {
                        continue;
                    }

                    char cell = classroom[nx].charAt(ny);

                    // Every movement costs 1 energy.
                    int newEnergy = e - 1;

                    // R restores energy to maximum.
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    int newMask = mask;

                    // Collect litter
                    if (cell == 'L') {
                        newMask &= ~(1 << id[nx][ny]);
                    }

                    if (!visited[nx][ny][newEnergy][newMask]) {
                        visited[nx][ny][newEnergy][newMask] = true;
                        q.offer(new int[]{
                            nx, ny, newEnergy, newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}