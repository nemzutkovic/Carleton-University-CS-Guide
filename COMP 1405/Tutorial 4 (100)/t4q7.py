## TIC-TAC-TOE GAME (PERSON VS AI)
## THIS MOST LIKELY HAS REDUNDANT CODE IN IT.

import random
import time

def checkWinner(list): 
    # Checks Rows/Columns
    for i in range(0,len(list)):
        if (list[i][0] == 'X' and list[i][1] == 'X' and list[i][2] == 'X') or (list[0][i] == 'X' and list[1][i] == 'X' and list[2][i] == 'X'):
            return 'X'
        if (list[i][0] == 'O' and list[i][1] == 'O' and list[i][2] == 'O') or (list[0][i] == 'O' and list[1][i] == 'O' and list[2][i] == 'O'):
            return 'O'

    # Checks Diagonals
    if list[0][0] == 'X' and list[1][1] == 'X' and list[2][2] == 'X':
        return 'X'
    if list[0][0] == 'O' and list[1][1] == 'O' and list[2][2] == 'O':
        return 'O'
    if list[0][2] == 'X' and list[1][1] == 'X' and list[2][0] == 'X':
        return 'X'
    if list[0][2] == 'O' and list[1][1] == 'O' and list[2][0] == 'O':
        return 'O'

    # No Winner Yet
    for i in range(0,len(list)):
        for j in range(0,len(list)):
            if list[i][j] == ' ':
                return ''

    # Otherwise Tie        
    return 'T'

def getWinningMove(list,str):
    for i in range(len(list)):
        if list[i][0] == str and list[i][1] == str and list[i][2] == ' ':
            return (i,2)
        elif list[i][1] == str and list[i][2] == str and list[i][0] == ' ':
            return (i,0)
        elif list[i][0] == str and list[i][2] == str and list[i][1] == ' ':
            return (i,1)
        elif list[0][i] == str and list[1][i] == str and list[2][i] == ' ':
            return (2,i)
        elif list[1][i] == str and list[2][i] == str and list[0][i] == ' ':
            return (0,i)
        elif list[0][i] == str and list[2][i] == str and list[1][i] == ' ':
            return (1,i)

    if list[0][0] == str and list[1][1] == str and list[2][2] == ' ':
        return (2,2)
    if list[0][2] == str and list[1][1] == str and list[2][0] == ' ':
        return (2,0)
    if list[2][0] == str and list[1][1] == str and list[0][2] == ' ':
        return (0,2)
    if list[2][2] == str and list[1][1] == str and list[0][0] == ' ':
        return (0,0)
    if (list[0][2] == str and list[2][0] == str and list[1][1] == ' ') or (list[0][0] == str and list[2][2] == str and list[1][1] == ' '):
        return (1,1)

    return (-1,-1)

### STUPID AI
def makeMove(list,str):
    xwinningmove = (getWinningMove(list,'X'))
    (row,col) = getWinningMove(list,str)
    if xwinningmove == (-1,-1):
        if list[1][1] == ' ':
            list[1][1] = str
        else:
            while True:
                row = random.randint(0,2)
                col = random.randint(0,2)
                if list[row][col] == ' ':
                    list[row][col] = str
                    break
    elif xwinningmove != (-1,-1) and list[xwinningmove[0]][xwinningmove[1]] == ' ':
        list[xwinningmove[0]][xwinningmove[1]] = str
    else:
        list[row][col] = str

def showBoard(list):
    for row in list:
        print(row)
    print()


def main():
    board = [[" " for i in range(3)] for j in range (3)]
    showBoard(board)
    while True:
        while True:
            row,column = input("Enter Row / Column: ").split()
            row = int(row) - 1
            column = int(column) - 1
            if row < 0 or column < 0 or row >= len(board) or column >= len(board) or board[row][column] != ' ':
                print("Move not possible.")
            else:
                break
        board[row][column] = 'X'
        showBoard(board)
        if checkWinner(board) == 'X':
            print("X Won!")
            break
        if checkWinner(board) == 'T':
            print("The game is a tie!")
            break
        print("AI making move...")
        time.sleep(3)
        makeMove(board,'O')
        showBoard(board)
        checkWinner(board)
        if checkWinner(board) == 'O':
            print("O Won!")
            break
        if checkWinner(board) == 'T':
            print("The game is a tie!")
            break

main()