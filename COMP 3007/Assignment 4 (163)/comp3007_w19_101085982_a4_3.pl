party(1, 0, 0).
party(2, 0, 0).
party(1, 1, 0).
party(1, 0, 1).
party(0, 1, 0).
party(0, 1, 1).

transition( state(HEB, NEB, SEB, HWB, NWB, SWB, east), sail(H, N, S), state(HEA, NEA, SEA, HWA, NWA, SWA, west) ) :- party(H, N, S), HEA is HEB-H, HEA >= 0, NEA is NEB-N, NEA >= 0, SEA is SEB-S, SEA >= 0, HWA is HWB+H, NWA is NWB+N, SWA is SWB+S, safe(HEA, SEA, HWA, SWA).
transition( state(HEB, NEB, SEB, HWB, NWB, SWB, west), sail(H, N, S), state(HEA, NEA, SEA, HWA, NWA, SWA, east) ) :- party(H, N, S), HEA is HEB+H, NEA is NEB+N, SEA is SEB+S, HWA is HWB-H, HWA >= 0, NWA is NWB-N, NWA >= 0, SWA is SWB-S, SWA >= 0, safe(HEA, SEA, HWA, SWA).

safe(A, B, C, D) :- (A >= B ; B == 0), (C >= D; D == 0).

crossSafely(S, S, []).
crossSafely(S, E, [X | Y]) :- S \= E, transition(S, X, I), crossSafely(I, E, Y).

find_solution() :- length(L, 15), crossSafely(state(3, 1, 2, 0, 0, 0, east), state(0, 0, 0, 3, 1, 2, west), L), write(L).