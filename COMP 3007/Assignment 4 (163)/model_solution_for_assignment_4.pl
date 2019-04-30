% Question 1

% n.b., the "cut" operations are not strictly required, but there is no reason for me not to include them on the model solution

countZeroFree([], 0).
countZeroFree([H|T], X) :- countZeroFree(T, X1), ( (containsZero(H), X is X1 + 1) ; (X is X1) ), !.

containsZero(0) :- !.
containsZero(X) :- X >= 10, ( myMod(X, 10, 0) ; ( myDiv(X, 10, Q), containsZero(Q) ) ), !.

myDiv(X, Y, 0) :- X < Y, !.
myDiv(X, Y, Q) :- X >= Y, X1 is X - Y, myDiv(X1, Y, Q1), Q is Q1 + 1, !.

myMod(X, Y, X) :- X < Y, !.
myMod(X, Y, M) :- X >= Y, X1 is X - Y, myMod(X1, Y, M), !.



% Question 3

% the 3 arguments are the number of Humans, Neanderthals, and Tigers, respectively
passengers(1, 0, 0).
passengers(0, 1, 0).
passengers(2, 0, 0).
passengers(1, 1, 0).
passengers(1, 0, 1).
passengers(0, 1, 1).

% this checks to ensure that the number of tigers on a side does not exceed the number of humans, unless there are no humans on that side
safe(HW, _, TW, HE, _, TE) :- ( HW == 0 ; HW >= TW ), ( HE == 0 ; HE >= TE ), !.

% these are the transitions, responsible for defining, in general terms, the relationship between the number of entities on each side of the river when a boat moves
transition( state(HWB, NWB, TWB, HEB, NEB, TEB, west), cross(H, N, T), state(HWA, NWA, TWA, HEA, NEA, TEA, east) ) :-
  passengers(H, N, T), HWA is HWB - H, HWA > -1, NWA is NWB - N, NWA > -1, TWA is TWB - T, TWA > -1, HEA is HEB + H, NEA is NEB + N, TEA is TEB + T, safe(HWA, NWA, TWA, HEA, NEA, TEA).
  
transition( state(HWB, NWB, TWB, HEB, NEB, TEB, east), cross(H, N, T), state(HWA, NWA, TWA, HEA, NEA, TEA, west) ) :-
  passengers(H, N, T), HEA is HEB - H, HEA > -1, NEA is NEB - N, NEA > -1, TEA is TEB - T, TEA > -1, HWA is HWB + H, NWA is NWB + N, TWA is TWB + T, safe(HWA, NWA, TWA, HEA, NEA, TEA).

% this is the code for finding a path in a graph
find(S, S, []).
find(S, E, [X | Y]) :- S \= E, transition(S, X, I), find(I, E, Y).

% this provides a valid length between 1 and 15 (inclusive)
valid(X) :- within(1, 15, X).

within(L, U, L) :- L =< U.
within(L, U, X) :- L1 is L + 1, within(L1, U, X).

% or something like this would also work...
% valid(15).
% valid(14).
% valid(13).
% valid(12).
% valid(11).
% valid(10).
% valid(9).
% valid(8).
% valid(7).
% valid(6).
% valid(5).
% valid(4).
% valid(3).
% valid(2).
% valid(1).

% this actually finds every possible solution - of which there are WAY too many :)
solve() :- valid(L), length(X, L), write(L), find(state(3, 1, 2, 0, 0, 0, west), state(0, 0, 0, 3, 1, 2, east), X), write(X).

