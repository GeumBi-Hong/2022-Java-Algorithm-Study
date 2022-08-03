import copy
import itertools

MAX = 100000

N, M, D = map(int, input().split())
arr = []
enemy = []
for i in range(N):
    arr.append(list(map(int, input().split())))
    for j in range(M):
        if arr[i][j] == 1:
            enemy.append([i, j])

enemy.sort(key=lambda x: x[1])
arch = []
target = []
result = 0

comb = list(itertools.combinations(range(M),3))


def archPositon(count, k):
    for a,b,c in comb:
        arch.append((N,a))
        arch.append((N,b))
        arch.append((N,c))
        temp = copy.deepcopy(arr)
        tenemy = copy.deepcopy(enemy)
        start(temp, tenemy)
        arch.clear()

def start(arr, enemy):
    global result
    temp = 0
    for _ in range(N):
        getTarget(arr, enemy)
        temp += killTarget(arr)
        move(arr, enemy)
    result = max(result, temp)


def getTarget(arr, enemy):
    for x, y in arch:
        temp = MAX
        target_x = -1
        target_y = -1
        for a, b in enemy:
            if arr[a][b] == 1:
                len = abs(M-a)+abs(y-b)
                if temp > len and len <= D:
                    target_x = a
                    target_y = b
                    temp = len
        if target_x != -1 and target_y != -1:
            target.append((target_x, target_y))


def killTarget(arr):
    temp = 0
    while target:
        x, y = target.pop()
        if arr[x][y] == 1:
            temp += 1
            arr[x][y] = 0
    return temp


def move(arr, enemy):
    zero = []
    one = []
    for k in range(len(enemy)):
        x = enemy[k][0]
        y = enemy[k][1]
        if arr[x][y] == 1:
            zero.append((x, y))
            if x + 1 < N:
                enemy[k][0] += 1
                one.append((x+1, y))
    while zero:
        x, y = zero.pop()
        arr[x][y] = 0
    while one:
        x, y = one.pop()
        arr[x][y] = 1


archPositon(0, 0)
print(result)