#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define MIN(a, b) (((a)<(b))?(a):(b))
//#define MAX(a, b) (((a)>(b))?(a):(b))
static void update(int, int, int, int, int);

static int to_add[300][3];
static int ans_list[300][300][3];

int main() {
    int i, j, s, n, m, sum = 0, temp = (int)(INFINITY);
    while (scanf("%d %d", &n, &m) != EOF) {
        if (n == 0 && m == 0)
            break;
        for (i = 1; i <= n; i++) {
            scanf("%d %d", &to_add[i][0], &to_add[i][1]);
            sum += to_add[i][0] + to_add[i][1];
        }
        if (m == 0) {
            printf("%d\n", sum);
            continue;
        }
        memset(ans_list, -1, sizeof(ans_list));
        ans_list[0][0][0] = 0;
        ans_list[0][0][1] = 0;
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= m; j++) {
                update(i, j, 0, ans_list[i - 1][j - 1][0], to_add[i][0]);
                update(i, j, 1, ans_list[i - 1][j - 1][1], to_add[i][1]);

                for (s = 0; s < i - 1; s++) {
                    update(i, j, 0, ans_list[s][j - 1][0], to_add[i][0]);
                    update(i, j, 0, ans_list[s][j - 1][1], to_add[i][0]);
                    update(i, j, 1, ans_list[s][j - 1][0], to_add[i][1]);
                    update(i, j, 1, ans_list[s][j - 1][1], to_add[i][1]);
                }
            }
            if (ans_list[i][m][0] != -1)
                //printf("here");
                temp = MIN(temp, ans_list[i][m][0]);
            if (ans_list[i][m][1] != -1)
                temp = MIN(temp, ans_list[i][m][1]);
        }
       // printf("%d\n", sum);
      //  printf("%d\n",  temp);
        printf("%d\n", sum - temp);
    }
    return 0;
}

static void update(int i, int j, int k, int val, int plus) {
    if (val == -1)
        return;
    if (ans_list[i][j][k] == -1)
        ans_list[i][j][k] = val + plus;
    else ans_list[i][j][k] = MIN(ans_list[i][j][k], val + plus);
}
