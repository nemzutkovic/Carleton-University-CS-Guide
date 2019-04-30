isZero(X)  :- X == 0.
isDigit(X) :- X > 0, X < 10.

modulo(X,Y,R) :- X >= Y, N is X - Y, modulo(N,Y,R).
modulo(X,Y,R) :- X < Y, R is X.

countZerofree([], 0).
countZerofree([H|T], X) :- H == 0, countZerofree(T, X).
countZerofree([H|T], X) :- H < 0, V is H * -1, countZerofree([V|T], X).
countZerofree([H|T], X) :- H > 0, H < 10, countZerofree(T, Y), X is Y + 1.
countZerofree([H|T], X) :- H >= 10, modulo(H,10,R), isZero(R), countZerofree(T, X).
countZerofree([H|T], X) :- H >= 10, modulo(H,10,R), isDigit(R), V is H // 10, countZerofree([V|T], X).