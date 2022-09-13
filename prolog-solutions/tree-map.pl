
map_build([], null) :- !.
map_build([H], node(H, null, null)) :- !.
map_build(List, node(R, Left, Right)) :- 	length(List, Listsize), Middle is div((Listsize - 1),2),
																						rec_counter(List, Middle, ListLeft, [R | ListRight]),
																						map_build(ListLeft, Left),
																						map_build(ListRight, Right).																																											 


rec_counter(L, 0, [], L).
rec_counter([H | T], I, [H | T1], T2) :- 
									In is I - 1,
									rec_counter(T, In, T1, T2). 

map_get(node((Key, Value), Left, Right), Key, Value) :- !.
map_get(node((K, V), Left, Right), Key, Value) :- Key < K, !, map_get(Left, Key, Value).
map_get(node((K, V), Left, Right), Key, Value) :- Key > K, map_get(Right, Key, Value).		


map_keys(null, []).
map_keys(node((K, V), Left, Right), Keys) :- map_keys(Left, Keys_left), append(Keys_left, [K], Keys1), map_keys(Right, Keys_right), append(Keys1, Keys_right, Keys). 

map_values(null, []).
map_values(node((K, V), Left, Right), Values) :-  map_values(Left, Values_left), append(Values_left, [V], Values1), map_values(Right, Values_right), append(Values1, Values_right, Values). 