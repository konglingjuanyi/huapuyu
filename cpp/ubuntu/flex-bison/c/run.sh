flex y.l
bison -d y.y
cc lex.yy.c y.tab.c main.c -o a.out
rm a
link a.out a
./a
