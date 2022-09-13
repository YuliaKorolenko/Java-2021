concat([], B, B).
concat([H | T], B, [H | R]) :- concat(T, B, R).

prime(N) :- F is div(N,2), rec_prime(N,F).
rec_prime(N,1).
rec_prime(N,F) :-
	F>=2,
	not( 0 is mod(N,F)),
	Fn is F -1,
	rec_prime(N, Fn).

composite(N) :- N > 1, not prime(N).

prime_divisors(1, []) :- !.
prime_divisors(N, Divisors) :- rec_divisors(N, 2, Divisors).
rec_divisors(1, F, []).
rec_divisors(N, F, [F | T]) :- 
	0 is mod(N,F), Nn is div(N, F), !, rec_divisors(Nn, F, T).
rec_divisors(N, F, D) :-
	Fn is F + 1, Fn =< N, rec_divisors(N, Fn, D).

power_divisors(N, 0, []) :- !.
power_divisors(N, I, D) :- rec_power_divisors(N, 2, I, I, D).

rec_power_divisors(1, F ,ValI, I, []).
rec_power_divisors(N, F, ValI, 1, [F | T]) :-
	0 is mod(N, F), Nn is div(N, F), !, rec_power_divisors(Nn, F, ValI, ValI, T).
rec_power_divisors(N, F, ValI, I, [F | T]) :-
	0 is mod(N, F), In is I - 1, !, rec_power_divisors(N, F, ValI, In, T).
rec_power_divisors(N, F, ValI, ValI, D) :-
	Fn is F + 1, Fn =< N, rec_power_divisors(N, Fn, ValI, ValI, D).